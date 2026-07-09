package com.wouti.zoom_academia.vo;

import java.util.Map;


public class NotificationVo {

	private String recipientToken;
	private String titre;
	private String contenue;
	private String image;
	private Map<String,String> data;
	public String getRecipientToken() {
		return recipientToken;
	}
	public void setRecipientToken(String recipientToken) {
		this.recipientToken = recipientToken;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getContenue() {
		return contenue;
	}
	public void setContenue(String contenue) {
		this.contenue = contenue;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "NotificationVo [recipientToken=" + recipientToken + ", titre=" + titre + ", contenue=" + contenue
				+ ", image=" + image + ", data=" + data + "]";
	}






}
