package com.progresssoft.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "VALIDDEALS")
public class ValidDeals {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "validDealId", unique = true)
	private int validDealId;
	@Column
	private String fromCurrency;
	@Column
	private String toCurrency;
	@Column
	private Double amount;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp")
	private Date timestamp;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fileId")
	private Files file;

	public Files getFile() {
		return file;
	}

	public void setFile(Files file) {
		this.file = file;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getValidDealId() {
		return validDealId;
	}

	public void setValidDealId(int validDealId) {
		this.validDealId = validDealId;
	}

}
