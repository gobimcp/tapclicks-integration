package com.tapclicks.integration.yashi.model;

import java.util.Date;
import java.util.HashMap;

public class Campaign {
	private int yashiCampaignId;
	private String yashicampaignName;
	private int advertiserId;
	private String advertiserName;
	private HashMap<Date, Data> dataMap;
	private HashMap<Integer, Order> orderMap;

	public int getYashiCampaignId() {
		return yashiCampaignId;
	}

	public void setYashiCampaignId(int yashiCampaignId) {
		this.yashiCampaignId = yashiCampaignId;
	}

	public String getYashicampaignName() {
		return yashicampaignName;
	}

	public void setYashicampaignName(String yashicampaignName) {
		this.yashicampaignName = yashicampaignName;
	}

	public int getAdvertiserId() {
		return advertiserId;
	}

	public void setAdvertiserId(int advertiserId) {
		this.advertiserId = advertiserId;
	}

	public String getAdvertiserName() {
		return advertiserName;
	}

	public void setAdvertiserName(String advertiserName) {
		this.advertiserName = advertiserName;
	}

	public HashMap<Date, Data> getDataMap() {
		if (this.dataMap == null)
			this.dataMap = new HashMap<>();
		return dataMap;
	}

	public void setDataMap(HashMap<Date, Data> dataMap) {
		this.dataMap = dataMap;
	}

	public HashMap<Integer, Order> getOrderMap() {
		if(this.orderMap == null)
			this.orderMap = new HashMap<>();
		return orderMap;
	}

	public void setOrderMap(HashMap<Integer, Order> orderMap) {
		this.orderMap = orderMap;
	}
}
