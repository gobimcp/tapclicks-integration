package com.tapclicks.integration.yashi.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the zz__yashi_creative database table.
 * 
 */
@Entity
@Table(name="zz__yashi_creative")
public class ZzYashiCreative implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="creative_id", unique=true, nullable=false)
	private int creativeId;

	@Column(length=255)
	private String name;

	@Column(name="preview_url", length=255)
	private String previewUrl;

	@Column(name="yashi_creative_id")
	private int yashiCreativeId;

	//bi-directional many-to-one association to ZzYashiOrder
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id")
	private ZzYashiOrder zzYashiOrder;

	//bi-directional many-to-one association to ZzYashiCreativeData
	@OneToMany(mappedBy="zzYashiCreative")
	private List<ZzYashiCreativeData> zzYashiCreativeData;

	public ZzYashiCreative() {
	}

	public int getCreativeId() {
		return this.creativeId;
	}

	public void setCreativeId(int creativeId) {
		this.creativeId = creativeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreviewUrl() {
		return this.previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public int getYashiCreativeId() {
		return this.yashiCreativeId;
	}

	public void setYashiCreativeId(int yashiCreativeId) {
		this.yashiCreativeId = yashiCreativeId;
	}

	public ZzYashiOrder getZzYashiOrder() {
		return this.zzYashiOrder;
	}

	public void setZzYashiOrder(ZzYashiOrder zzYashiOrder) {
		this.zzYashiOrder = zzYashiOrder;
	}

	public List<ZzYashiCreativeData> getZzYashiCreativeData() {
		return this.zzYashiCreativeData;
	}

	public void setZzYashiCreativeData(List<ZzYashiCreativeData> zzYashiCreativeData) {
		this.zzYashiCreativeData = zzYashiCreativeData;
	}

	public ZzYashiCreativeData addZzYashiCreativeData(ZzYashiCreativeData zzYashiCreativeData) {
		getZzYashiCreativeData().add(zzYashiCreativeData);
		zzYashiCreativeData.setZzYashiCreative(this);

		return zzYashiCreativeData;
	}

	public ZzYashiCreativeData removeZzYashiCreativeData(ZzYashiCreativeData zzYashiCreativeData) {
		getZzYashiCreativeData().remove(zzYashiCreativeData);
		zzYashiCreativeData.setZzYashiCreative(null);

		return zzYashiCreativeData;
	}

}