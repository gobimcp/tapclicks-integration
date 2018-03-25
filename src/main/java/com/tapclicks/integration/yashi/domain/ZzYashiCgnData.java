package com.tapclicks.integration.yashi.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the zz__yashi_cgn_data database table.
 * 
 */
@Entity
@Table(name="zz__yashi_cgn_data")
public class ZzYashiCgnData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="100viewed_count")
	private int _100viewedCount;

	@Column(name="25viewed_count")
	private int _25viewedCount;

	@Column(name="50viewed_count")
	private int _50viewedCount;

	@Column(name="75viewed_count")
	private int _75viewedCount;

	@Column(name="click_count")
	private int clickCount;

	@Column(name="impression_count")
	private int impressionCount;

	@Temporal(TemporalType.DATE)
	@Column(name="log_date")
	private Date logDate;

	//bi-directional many-to-one association to ZzYashiCgn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="campaign_id")
	private ZzYashiCgn zzYashiCgn;

	public ZzYashiCgnData() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int get_100viewedCount() {
		return this._100viewedCount;
	}

	public void set_100viewedCount(int _100viewedCount) {
		this._100viewedCount = _100viewedCount;
	}

	public int get_25viewedCount() {
		return this._25viewedCount;
	}

	public void set_25viewedCount(int _25viewedCount) {
		this._25viewedCount = _25viewedCount;
	}

	public int get_50viewedCount() {
		return this._50viewedCount;
	}

	public void set_50viewedCount(int _50viewedCount) {
		this._50viewedCount = _50viewedCount;
	}

	public int get_75viewedCount() {
		return this._75viewedCount;
	}

	public void set_75viewedCount(int _75viewedCount) {
		this._75viewedCount = _75viewedCount;
	}

	public int getClickCount() {
		return this.clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public int getImpressionCount() {
		return this.impressionCount;
	}

	public void setImpressionCount(int impressionCount) {
		this.impressionCount = impressionCount;
	}

	public Date getLogDate() {
		return this.logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public ZzYashiCgn getZzYashiCgn() {
		return this.zzYashiCgn;
	}

	public void setZzYashiCgn(ZzYashiCgn zzYashiCgn) {
		this.zzYashiCgn = zzYashiCgn;
	}

}