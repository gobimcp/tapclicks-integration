package com.tapclicks.integration.yashi.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the zz__yashi_order database table.
 * 
 */
@Entity
@Table(name="zz__yashi_order")
public class ZzYashiOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_id", unique=true, nullable=false)
	private int orderId;

	@Column(length=200)
	private String name;

	@Column(name="yashi_order_id")
	private int yashiOrderId;

	//bi-directional many-to-one association to ZzYashiCreative
	@OneToMany(mappedBy="zzYashiOrder")
	private List<ZzYashiCreative> zzYashiCreatives;

	//bi-directional many-to-one association to ZzYashiCgn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="campaign_id")
	private ZzYashiCgn zzYashiCgn;

	//bi-directional many-to-one association to ZzYashiOrderData
	@OneToMany(mappedBy="zzYashiOrder")
	private List<ZzYashiOrderData> zzYashiOrderData;

	public ZzYashiOrder() {
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYashiOrderId() {
		return this.yashiOrderId;
	}

	public void setYashiOrderId(int yashiOrderId) {
		this.yashiOrderId = yashiOrderId;
	}

	public List<ZzYashiCreative> getZzYashiCreatives() {
		return this.zzYashiCreatives;
	}

	public void setZzYashiCreatives(List<ZzYashiCreative> zzYashiCreatives) {
		this.zzYashiCreatives = zzYashiCreatives;
	}

	public ZzYashiCreative addZzYashiCreative(ZzYashiCreative zzYashiCreative) {
		getZzYashiCreatives().add(zzYashiCreative);
		zzYashiCreative.setZzYashiOrder(this);

		return zzYashiCreative;
	}

	public ZzYashiCreative removeZzYashiCreative(ZzYashiCreative zzYashiCreative) {
		getZzYashiCreatives().remove(zzYashiCreative);
		zzYashiCreative.setZzYashiOrder(null);

		return zzYashiCreative;
	}

	public ZzYashiCgn getZzYashiCgn() {
		return this.zzYashiCgn;
	}

	public void setZzYashiCgn(ZzYashiCgn zzYashiCgn) {
		this.zzYashiCgn = zzYashiCgn;
	}

	public List<ZzYashiOrderData> getZzYashiOrderData() {
		return this.zzYashiOrderData;
	}

	public void setZzYashiOrderData(List<ZzYashiOrderData> zzYashiOrderData) {
		this.zzYashiOrderData = zzYashiOrderData;
	}

	public ZzYashiOrderData addZzYashiOrderData(ZzYashiOrderData zzYashiOrderData) {
		getZzYashiOrderData().add(zzYashiOrderData);
		zzYashiOrderData.setZzYashiOrder(this);

		return zzYashiOrderData;
	}

	public ZzYashiOrderData removeZzYashiOrderData(ZzYashiOrderData zzYashiOrderData) {
		getZzYashiOrderData().remove(zzYashiOrderData);
		zzYashiOrderData.setZzYashiOrder(null);

		return zzYashiOrderData;
	}

}