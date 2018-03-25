package com.tapclicks.integration.yashi.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the zz__yashi_cgn database table.
 * 
 */
@Entity
@Table(name="zz__yashi_cgn")
public class ZzYashiCgn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="campaign_id", unique=true, nullable=false)
	private int campaignId;

	@Column(name="advertiser_name", length=100)
	private String advertiserName;

	@Column(length=255)
	private String name;

	@Column(name="yashi_advertiser_id")
	private int yashiAdvertiserId;

	@Column(name="yashi_campaign_id")
	private int yashiCampaignId;

	//bi-directional many-to-one association to ZzYashiCgnData
	@OneToMany(mappedBy="zzYashiCgn")
	private List<ZzYashiCgnData> zzYashiCgnData;

	//bi-directional many-to-one association to ZzYashiOrder
	@OneToMany(mappedBy="zzYashiCgn")
	private List<ZzYashiOrder> zzYashiOrders;

	public ZzYashiCgn() {
	}

	public int getCampaignId() {
		return this.campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public String getAdvertiserName() {
		return this.advertiserName;
	}

	public void setAdvertiserName(String advertiserName) {
		this.advertiserName = advertiserName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYashiAdvertiserId() {
		return this.yashiAdvertiserId;
	}

	public void setYashiAdvertiserId(int yashiAdvertiserId) {
		this.yashiAdvertiserId = yashiAdvertiserId;
	}

	public int getYashiCampaignId() {
		return this.yashiCampaignId;
	}

	public void setYashiCampaignId(int yashiCampaignId) {
		this.yashiCampaignId = yashiCampaignId;
	}

	public List<ZzYashiCgnData> getZzYashiCgnData() {
		return this.zzYashiCgnData;
	}

	public void setZzYashiCgnData(List<ZzYashiCgnData> zzYashiCgnData) {
		this.zzYashiCgnData = zzYashiCgnData;
	}

	public ZzYashiCgnData addZzYashiCgnData(ZzYashiCgnData zzYashiCgnData) {
		getZzYashiCgnData().add(zzYashiCgnData);
		zzYashiCgnData.setZzYashiCgn(this);

		return zzYashiCgnData;
	}

	public ZzYashiCgnData removeZzYashiCgnData(ZzYashiCgnData zzYashiCgnData) {
		getZzYashiCgnData().remove(zzYashiCgnData);
		zzYashiCgnData.setZzYashiCgn(null);

		return zzYashiCgnData;
	}

	public List<ZzYashiOrder> getZzYashiOrders() {
		return this.zzYashiOrders;
	}

	public void setZzYashiOrders(List<ZzYashiOrder> zzYashiOrders) {
		this.zzYashiOrders = zzYashiOrders;
	}

	public ZzYashiOrder addZzYashiOrder(ZzYashiOrder zzYashiOrder) {
		getZzYashiOrders().add(zzYashiOrder);
		zzYashiOrder.setZzYashiCgn(this);

		return zzYashiOrder;
	}

	public ZzYashiOrder removeZzYashiOrder(ZzYashiOrder zzYashiOrder) {
		getZzYashiOrders().remove(zzYashiOrder);
		zzYashiOrder.setZzYashiCgn(null);

		return zzYashiOrder;
	}

}