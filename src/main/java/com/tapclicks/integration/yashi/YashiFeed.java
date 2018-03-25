package com.tapclicks.integration.yashi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapclicks.integration.yashi.model.Campaign;
import com.tapclicks.integration.yashi.model.Creative;
import com.tapclicks.integration.yashi.model.Data;
import com.tapclicks.integration.yashi.model.Order;

public class YashiFeed {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private HashMap<Integer, Campaign> campaignMap = new HashMap<>();

	public HashMap<Integer, Campaign> getCampaignMap() {
		return campaignMap;
	}

	public void setCampaignMap(HashMap<Integer, Campaign> campaignMap) {
		this.campaignMap = campaignMap;
	}

	public void processLineItem(String line) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			line = line.replaceAll("\"", "");
			String[] values = line.split(",");

			Date date = dateFormat.parse(values[0]);
			int advertiserId = Integer.parseInt(values[1]);
			String advertiserName = values[2];
			int campaignId = Integer.parseInt(values[3]);
			String campaignName = values[4];
			int orderId = Integer.parseInt(values[5]);
			String orderName = values[6];
			int creativeId = Integer.parseInt(values[7]);
			String creativeName = values[8];
			String creativeURL = values[9];
			int impressionCt = Integer.parseInt(values[10]);
			int clickCt = Integer.parseInt(values[11]);
			int viewed25Ct = Integer.parseInt(values[12]);
			int viewed50Ct = Integer.parseInt(values[13]);
			int viewed75Ct = Integer.parseInt(values[14]);
			int viewed100Ct = Integer.parseInt(values[15]);

			Data data = createData(impressionCt, clickCt, viewed25Ct, viewed50Ct, viewed75Ct, viewed100Ct);
			Creative creative = null;
			Order order = null;
			Campaign campaign = null;

			if (campaignMap.get(campaignId) != null) {
				if (campaignMap.get(campaignId).getOrderMap().get(orderId) != null) {
					if (campaignMap.get(campaignId).getOrderMap().get(orderId).getCreativeMap()
							.get(creativeId) != null) {
						updateData(campaignMap.get(campaignId), date, data);
						updateData(campaignMap.get(campaignId).getOrderMap().get(orderId), date, data);
						updateData(
								campaignMap.get(campaignId).getOrderMap().get(orderId).getCreativeMap().get(creativeId),
								date, data);
					} else {
						creative = createCreative(creativeId, creativeName, creativeURL, data, date);
						campaignMap.get(campaignId).getOrderMap().get(orderId).getCreativeMap().put(creativeId,
								creative);
						updateData(campaignMap.get(campaignId), date, data);
						updateData(campaignMap.get(campaignId).getOrderMap().get(orderId), date, data);
					}

				} else {
					creative = createCreative(creativeId, creativeName, creativeURL, data, date);
					order = createOrder(orderId, orderName, data, date, creative);
					campaignMap.get(campaignId).getOrderMap().put(order.getYashiOrderId(), order);
					updateData(campaignMap.get(campaignId), date, data);
				}

			} else {
				creative = createCreative(creativeId, creativeName, creativeURL, data, date);
				order = createOrder(orderId, orderName, data, date, creative);
				campaign = createCampaign(campaignId, campaignName, advertiserId, advertiserName, data, date, order);
				campaignMap.put(campaignId, campaign);
			}
		} catch (Exception e) {
			logger.error("Error while preparign the hashmap of the data feed : ", e);
			throw e;
		}
	}

	private Data createData(int impressionCt, int clickCt, int viewed25Ct, int viewed50Ct, int viewed75Ct,
			int viewed100Ct) throws Exception {
		return new Data(impressionCt, clickCt, viewed25Ct, viewed50Ct, viewed75Ct, viewed100Ct);
	}

	private Creative createCreative(int yashiCreativeId, String name, String previewURL, Data data, Date date)
			throws Exception {
		Creative creative = new Creative();

		creative.setYashiCreativeId(yashiCreativeId);
		creative.setName(name);
		creative.setPreviewURL(previewURL);
		creative.getDataMap().put(date, new Data(data));
		return creative;
	}

	private Order createOrder(int yashiOrderId, String name, Data data, Date date, Creative creative) throws Exception {
		Order order = new Order();

		order.setYashiOrderId(yashiOrderId);
		order.setName(name);
		order.getCreativeMap().put(creative.getYashiCreativeId(), creative);
		order.getDataMap().put(date, new Data(data));

		return order;
	}

	private Campaign createCampaign(int yashiCampaignId, String yashicampaignName, int advertiserId,
			String advertiserName, Data data, Date date, Order order) throws Exception {
		Campaign campaign = new Campaign();

		campaign.setYashiCampaignId(yashiCampaignId);
		campaign.setYashicampaignName(yashicampaignName);
		campaign.setAdvertiserId(advertiserId);
		campaign.setAdvertiserName(advertiserName);
		campaign.getDataMap().put(date, new Data(data));
		campaign.getOrderMap().put(order.getYashiOrderId(), order);

		return campaign;
	}

	private void updateData(Object obj, Date date, Data data) throws Exception {
		if (obj instanceof Campaign) {
			Campaign campaign = (Campaign) obj;
			if (campaign.getDataMap().get(date) != null) {
				updateData(campaign.getDataMap().get(date), data);
			} else {
				campaign.getDataMap().put(date, new Data(data));
			}
		} else if (obj instanceof Order) {
			Order order = (Order) obj;
			if (order.getDataMap().get(date) != null) {
				updateData(order.getDataMap().get(date), data);
			} else {
				order.getDataMap().put(date, new Data(data));
			}
		}
		if (obj instanceof Creative) {
			Creative creative = (Creative) obj;
			if (creative.getDataMap().get(date) != null) {
				updateData(creative.getDataMap().get(date), data);
			} else {
				creative.getDataMap().put(date, new Data(data));
			}
		}

	}

	private void updateData(Data data, Data newData) throws Exception {
		data.setImpression_count(data.getImpression_count() + newData.getImpression_count());
		data.setClick_count(data.getClick_count() + newData.getClick_count());
		data.setViewed_count_25(data.getViewed_count_25() + newData.getViewed_count_25());
		data.setViewed_count_50(data.getViewed_count_50() + newData.getViewed_count_50());
		data.setViewed_count_75(data.getViewed_count_75() + newData.getViewed_count_75());
		data.setViewed_count_100(data.getViewed_count_100() + newData.getViewed_count_100());
	}

}
