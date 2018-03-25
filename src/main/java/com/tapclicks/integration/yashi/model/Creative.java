package com.tapclicks.integration.yashi.model;

import java.util.Date;
import java.util.HashMap;

public class Creative {

	private int yashiCreativeId;
	private String name;
	private String previewURL;
	private HashMap<Date, Data> dataMap;

	public int getYashiCreativeId() {
		return yashiCreativeId;
	}

	public void setYashiCreativeId(int yashiCreativeId) {
		this.yashiCreativeId = yashiCreativeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<Date, Data> getDataMap() {
		if (this.dataMap == null)
			this.dataMap = new HashMap<>();
		return dataMap;
	}

	public void setDataMap(HashMap<Date, Data> dataMap) {
		this.dataMap = dataMap;
	}

	public String getPreviewURL() {
		return previewURL;
	}

	public void setPreviewURL(String previewURL) {
		this.previewURL = previewURL;
	}

}
