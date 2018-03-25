package com.tapclicks.integration.yashi;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.tapclicks.integration.yashi.dao.YashiCgnDataRepository;
import com.tapclicks.integration.yashi.dao.YashiCgnRepository;
import com.tapclicks.integration.yashi.dao.YashiCreativeDataRepository;
import com.tapclicks.integration.yashi.dao.YashiCreativeRepository;
import com.tapclicks.integration.yashi.dao.YashiOrderDataRepository;
import com.tapclicks.integration.yashi.dao.YashiOrderRepository;
import com.tapclicks.integration.yashi.domain.ZzYashiCgn;
import com.tapclicks.integration.yashi.domain.ZzYashiCgnData;
import com.tapclicks.integration.yashi.domain.ZzYashiCreative;
import com.tapclicks.integration.yashi.domain.ZzYashiCreativeData;
import com.tapclicks.integration.yashi.domain.ZzYashiOrder;
import com.tapclicks.integration.yashi.domain.ZzYashiOrderData;
import com.tapclicks.integration.yashi.model.Campaign;
import com.tapclicks.integration.yashi.model.Creative;
import com.tapclicks.integration.yashi.model.Data;
import com.tapclicks.integration.yashi.model.Order;
import com.tapclicks.integration.yashi.util.FTPUtility;
import com.tapclicks.integration.yashi.util.FileReader;

@Service
public class TapClicksService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment env;

	@Autowired
	YashiCgnRepository yashiCgnRepository;

	@Autowired
	YashiCgnDataRepository yashiCgnDataRepository;

	@Autowired
	YashiOrderRepository yashiOrderRepository;

	@Autowired
	YashiOrderDataRepository yashiOrderDataRepository;

	@Autowired
	YashiCreativeRepository yashiCreativeRepository;

	@Autowired
	YashiCreativeDataRepository yashiCreativeDataRepository;

	public void downloadFileFromFTPServer() throws Exception {
		logger.debug("Starting downloding the files from FTP server");
		try {
			FTPUtility.downloadFileFromFTPServer(env.getProperty("tapclicks.ftp.server"),
					env.getProperty("tapclicks.ftp.port"), env.getProperty("tapclicks.ftp.user"),
					env.getProperty("tapclicks.ftp.pass"), env.getProperty("tapclicks.ftp.serverdirectory"),
					env.getProperty("tapclicks.ftp.localdirectory"), env.getProperty("tapclicks.ftp.fileformat"));
		} catch (Exception e) {
			logger.error("Exception while downloading files from SFTP Server :", e);
		}
		logger.debug("Finishing downloding the files from FTP server");
	}

	public void processDataFiles() throws Exception {
		logger.debug("Starting processing the files");
		YashiFeed yashiFeed = new YashiFeed();
		logger.debug("Starting reading the files");
		FileReader.readFiles(env.getProperty("tapclicks.ftp.localdirectory"), yashiFeed);
		logger.debug("Starting saving the data into the database.");
		saveData(yashiFeed);
		logger.debug("Starting to delete the files from local directory");
		FileReader.deleteFiles(env.getProperty("tapclicks.ftp.localdirectory"));
		logger.debug("Finishing processing the files");
	}

	private void saveData(YashiFeed yashiFeed) {
		try {
			for (Campaign campign : yashiFeed.getCampaignMap().values()) {
				List<ZzYashiCgn> listCmpaign = yashiCgnRepository.findByYashiCampaignId(campign.getYashiCampaignId());
				ZzYashiCgn zzYashiCgn = null;
				if (listCmpaign != null && !listCmpaign.isEmpty()) {
					zzYashiCgn = listCmpaign.get(0);
				} else if (listCmpaign == null || listCmpaign.isEmpty()) {
					zzYashiCgn = saveCampaign(campign);
				}
				saveCampaignData(campign.getDataMap(), zzYashiCgn);

				for (Order order : campign.getOrderMap().values()) {
					List<ZzYashiOrder> listOrder = yashiOrderRepository.findByYashiOrderId(order.getYashiOrderId());
					ZzYashiOrder zzYashiOrder = null;
					if (listOrder != null && !listOrder.isEmpty()) {
						zzYashiOrder = listOrder.get(0);
					} else if (listOrder == null || listOrder.isEmpty()) {
						zzYashiOrder = saveOrder(order, zzYashiCgn);
					}
					saveOrderData(order.getDataMap(), zzYashiOrder);

					for (Creative creative : order.getCreativeMap().values()) {
						List<ZzYashiCreative> listCreative = yashiCreativeRepository
								.findByYashiCreativeId(creative.getYashiCreativeId());
						ZzYashiCreative zzYashiCreative = null;
						if (listCreative != null && !listCreative.isEmpty()) {
							zzYashiCreative = listCreative.get(0);
						} else if (listCreative == null || listCreative.isEmpty()) {
							zzYashiCreative = saveCreative(creative, zzYashiOrder);
						}
						saveCreativeData(creative.getDataMap(), zzYashiCreative);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error while storing the data into DB : ", e);
		}

	}

	private ZzYashiCgn saveCampaign(Campaign campign) throws Exception {
		ZzYashiCgn zzYashiCgn = new ZzYashiCgn();
		zzYashiCgn.setYashiCampaignId(campign.getYashiCampaignId());
		zzYashiCgn.setName(campign.getYashicampaignName());
		zzYashiCgn.setYashiAdvertiserId(campign.getAdvertiserId());
		zzYashiCgn.setAdvertiserName(campign.getAdvertiserName());
		zzYashiCgn = yashiCgnRepository.save(zzYashiCgn);
		return zzYashiCgn;
	}

	private ZzYashiOrder saveOrder(Order order, ZzYashiCgn zzYashiCgn) throws Exception {
		ZzYashiOrder zzYashiOrder = new ZzYashiOrder();
		zzYashiOrder.setZzYashiCgn(zzYashiCgn);
		zzYashiOrder.setName(order.getName());
		zzYashiOrder.setYashiOrderId(order.getYashiOrderId());

		zzYashiOrder = yashiOrderRepository.save(zzYashiOrder);
		return zzYashiOrder;
	}

	private ZzYashiCreative saveCreative(Creative creative, ZzYashiOrder zzYashiOrder) throws Exception {
		ZzYashiCreative zzYashiCreative = new ZzYashiCreative();
		zzYashiCreative.setZzYashiOrder(zzYashiOrder);
		zzYashiCreative.setYashiCreativeId(creative.getYashiCreativeId());
		zzYashiCreative.setName(creative.getName());
		zzYashiCreative.setPreviewUrl(creative.getPreviewURL());

		zzYashiCreative = yashiCreativeRepository.save(zzYashiCreative);

		return zzYashiCreative;
	}

	private void saveCampaignData(HashMap<Date, Data> dataMap, ZzYashiCgn zzYashiCgn) throws Exception {
		for (Iterator<Date> dateIter = dataMap.keySet().iterator(); dateIter.hasNext();) {
			Date key = dateIter.next();
			Data data = dataMap.get(key);

			ZzYashiCgnData zzYashiCgnData = new ZzYashiCgnData();
			List<ZzYashiCgnData> listCampaignData = yashiCgnDataRepository.findByZzYashiCgnAndLogDate(zzYashiCgn, key);
			if (listCampaignData != null && !listCampaignData.isEmpty()) {
				zzYashiCgnData.setId(listCampaignData.get(0).getId());
			}
			zzYashiCgnData.setZzYashiCgn(zzYashiCgn);
			zzYashiCgnData.setLogDate(key);
			zzYashiCgnData.setImpressionCount(data.getImpression_count());
			zzYashiCgnData.setClickCount(data.getClick_count());
			zzYashiCgnData.set_25viewedCount(data.getViewed_count_25());
			zzYashiCgnData.set_50viewedCount(data.getViewed_count_50());
			zzYashiCgnData.set_75viewedCount(data.getViewed_count_75());
			zzYashiCgnData.set_100viewedCount(data.getViewed_count_100());

			yashiCgnDataRepository.save(zzYashiCgnData);
		}
	}

	private void saveOrderData(HashMap<Date, Data> dataMap, ZzYashiOrder zzYashiOrder) throws Exception {
		for (Iterator<Date> dateIter = dataMap.keySet().iterator(); dateIter.hasNext();) {
			Date key = dateIter.next();
			Data data = dataMap.get(key);

			ZzYashiOrderData zzYashiOrderData = new ZzYashiOrderData();

			List<ZzYashiOrderData> listOrderData = yashiOrderDataRepository.findByZzYashiOrderAndLogDate(zzYashiOrder,
					key);
			if (listOrderData != null && !listOrderData.isEmpty()) {
				zzYashiOrderData.setId(listOrderData.get(0).getId());
			}
			zzYashiOrderData.setZzYashiOrder(zzYashiOrder);
			zzYashiOrderData.setLogDate(key);
			zzYashiOrderData.setImpressionCount(data.getImpression_count());
			zzYashiOrderData.setClickCount(data.getClick_count());
			zzYashiOrderData.set_25viewedCount(data.getViewed_count_25());
			zzYashiOrderData.set_50viewedCount(data.getViewed_count_50());
			zzYashiOrderData.set_75viewedCount(data.getViewed_count_75());
			zzYashiOrderData.set_100viewedCount(data.getViewed_count_100());

			yashiOrderDataRepository.save(zzYashiOrderData);
		}
	}

	private void saveCreativeData(HashMap<Date, Data> dataMap, ZzYashiCreative zzYashiCreative) throws Exception {
		for (Iterator<Date> dateIter = dataMap.keySet().iterator(); dateIter.hasNext();) {
			Date key = dateIter.next();
			Data data = dataMap.get(key);

			ZzYashiCreativeData zzYashiCreativeData = new ZzYashiCreativeData();

			List<ZzYashiCreativeData> listCreativeData = yashiCreativeDataRepository
					.findByZzYashiCreativeAndLogDate(zzYashiCreative, key);
			if (listCreativeData != null && !listCreativeData.isEmpty()) {
				zzYashiCreativeData.setId(listCreativeData.get(0).getId());
			}
			zzYashiCreativeData.setZzYashiCreative(zzYashiCreative);
			zzYashiCreativeData.setLogDate(key);
			zzYashiCreativeData.setImpressionCount(data.getImpression_count());
			zzYashiCreativeData.setClickCount(data.getClick_count());
			zzYashiCreativeData.set_25viewedCount(data.getViewed_count_25());
			zzYashiCreativeData.set_50viewedCount(data.getViewed_count_50());
			zzYashiCreativeData.set_75viewedCount(data.getViewed_count_75());
			zzYashiCreativeData.set_100viewedCount(data.getViewed_count_100());

			zzYashiCreativeData = yashiCreativeDataRepository.save(zzYashiCreativeData);
		}

	}
}
