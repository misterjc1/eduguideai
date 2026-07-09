package com.wouti.zoom_academia.vo;

import java.util.Date;

public class DeleteVo {

	private String [] codes;
	private Date deleteDate;
	private String deleterCode;

	public String[] getCodes() {
		return codes;
	}
	public void setCodes(String[] codes) {
		this.codes = codes;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	public String getDeleterCode() {
		return deleterCode;
	}
	public void setDeleterCode(String deleterCode) {
		this.deleterCode = deleterCode;
	}



}
