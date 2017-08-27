package com.progresssoft.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FILES")
public class Files {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fileId", unique = true)
	private int fileId;
	@Column(unique = true)
	private String fileName;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "fileId", referencedColumnName = "fileId", insertable = false, updatable = false)
	private Set<ValidDeals> validDeals;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "fileId", referencedColumnName = "fileId", insertable = false, updatable = false)
	private Set<InValidDeals> inValidDeals;

	public Set<ValidDeals> getValidDeals() {
		return validDeals;
	}

	public void setValidDeals(Set<ValidDeals> validDeals) {
		this.validDeals = validDeals;
	}

	public Set<InValidDeals> getInValidDeals() {
		return inValidDeals;
	}

	public void setInValidDeals(Set<InValidDeals> inValidDeals) {
		this.inValidDeals = inValidDeals;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
