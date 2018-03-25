package com.tapclicks.integration.yashi.model;

import java.util.Date;
import java.util.HashMap;

public class Order {
	private int yashiOrderId;
	private String name;
	private HashMap<Date, Data> dataMap;
	private HashMap<Integer, Creative> creativeMap;

	public int getYashiOrderId() {
		return yashiOrderId;
	}

	public void setYashiOrderId(int yashiOrderId) {
		this.yashiOrderId = yashiOrderId;
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

	public HashMap<Integer, Creative> getCreativeMap() {
		if(this.creativeMap == null)
			this.creativeMap = new HashMap<>();
		return creativeMap;
	}

	public void setCreativeMap(HashMap<Integer, Creative> creativeMap) {
		this.creativeMap = creativeMap;
	}

}
