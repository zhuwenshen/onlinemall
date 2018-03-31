package com.zhuwenshen.model.custom;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UpdateConstant {
	private String idU;
	private String kindU;
	private String kindNameU;
	private String nameU;
	private String nameCnU;
	private String value1U;
	private Boolean usefulU;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTimeU;
	private String createNameU;
	private String remakeU;
	private Date updateTimeU;
	private String updateNameU;
	public String getIdU() {
		return idU;
	}
	public void setIdU(String idU) {
		this.idU = idU;
	}
	public String getKindU() {
		return kindU;
	}
	public void setKindU(String kindU) {
		this.kindU = kindU;
	}
	public String getKindNameU() {
		return kindNameU;
	}
	public void setKindNameU(String kindNameU) {
		this.kindNameU = kindNameU;
	}
	public String getNameU() {
		return nameU;
	}
	public void setNameU(String nameU) {
		this.nameU = nameU;
	}
	public String getNameCnU() {
		return nameCnU;
	}
	public void setNameCnU(String nameCnU) {
		this.nameCnU = nameCnU;
	}
	public String getValue1U() {
		return value1U;
	}
	public void setValue1U(String value1u) {
		value1U = value1u;
	}
	public Boolean getUsefulU() {
		return usefulU;
	}
	public void setUsefulU(Boolean usefulU) {
		this.usefulU = usefulU;
	}
	public Date getCreateTimeU() {
		return createTimeU;
	}
	public void setCreateTimeU(Date createTimeU) {
		this.createTimeU = createTimeU;
	}
	public String getCreateNameU() {
		return createNameU;
	}
	public void setCreateNameU(String createNameU) {
		this.createNameU = createNameU;
	}
	public String getRemakeU() {
		return remakeU;
	}
	public void setRemakeU(String remakeU) {
		this.remakeU = remakeU;
	}
	public Date getUpdateTimeU() {
		return updateTimeU;
	}
	public void setUpdateTimeU(Date updateTimeU) {
		this.updateTimeU = updateTimeU;
	}
	public String getUpdateNameU() {
		return updateNameU;
	}
	public void setUpdateNameU(String updateNameU) {
		this.updateNameU = updateNameU;
	}
	@Override
	public String toString() {
		return "UpdateConstant [idU=" + idU + ", kindU=" + kindU + ", kindNameU=" + kindNameU + ", nameU=" + nameU
				+ ", nameCnU=" + nameCnU + ", value1U=" + value1U + ", usefulU=" + usefulU + ", createTimeU="
				+ createTimeU + ", createNameU=" + createNameU + ", remakeU=" + remakeU + ", updateTimeU=" + updateTimeU
				+ ", updateNameU=" + updateNameU + "]";
	}
	
	
	

}
