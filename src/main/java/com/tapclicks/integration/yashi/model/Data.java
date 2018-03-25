package com.tapclicks.integration.yashi.model;

public class Data {

	private int impression_count;
	private int click_count;
	private int viewed_count_25;
	private int viewed_count_50;
	private int viewed_count_75;
	private int viewed_count_100;

	public Data(int impression_count, int click_count, int viewed_count_25, int viewed_count_50, int viewed_count_75,
			int viewed_count_100) {
		this.impression_count = impression_count;
		this.click_count = click_count;
		this.viewed_count_25 = viewed_count_25;
		this.viewed_count_50 = viewed_count_50;
		this.viewed_count_75 = viewed_count_75;
		this.viewed_count_100 = viewed_count_100;
	}
	
	public Data(Data data) {
		this.impression_count = data.impression_count;
		this.click_count = data.click_count;
		this.viewed_count_25 = data.viewed_count_25;
		this.viewed_count_50 = data.viewed_count_50;
		this.viewed_count_75 = data.viewed_count_75;
		this.viewed_count_100 = data.viewed_count_100;
	}

	public int getImpression_count() {
		return impression_count;
	}

	public void setImpression_count(int impression_count) {
		this.impression_count = impression_count;
	}

	public int getClick_count() {
		return click_count;
	}

	public void setClick_count(int click_count) {
		this.click_count = click_count;
	}

	public int getViewed_count_25() {
		return viewed_count_25;
	}

	public void setViewed_count_25(int viewed_count_25) {
		this.viewed_count_25 = viewed_count_25;
	}

	public int getViewed_count_50() {
		return viewed_count_50;
	}

	public void setViewed_count_50(int viewed_count_50) {
		this.viewed_count_50 = viewed_count_50;
	}

	public int getViewed_count_75() {
		return viewed_count_75;
	}

	public void setViewed_count_75(int viewed_count_75) {
		this.viewed_count_75 = viewed_count_75;
	}

	public int getViewed_count_100() {
		return viewed_count_100;
	}

	public void setViewed_count_100(int viewed_count_100) {
		this.viewed_count_100 = viewed_count_100;
	}

}
