package com.wouti.zoom_academia.entities;

import jakarta.persistence.*;


@Entity
public class Position extends AuditModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPosition;

	private String codePosition;

	@Column(name = "COORD_LON", nullable = false)
	private String longitude;

	@Column(name = "COORD_LAT", nullable = false)
	private String latitude;

	@Column(name = "CODE", nullable = false)
	private String code;

	public String getLongitude() {
		return longitude;
	}

	public long getIdPosition() {
		return idPosition;
	}

	public void setIdPosition(long idPosition) {
		this.idPosition = idPosition;
	}

	public String getCodePositon() {
		return codePosition;
	}

	public void setCodePositon(String codePosition) {
		this.codePosition = codePosition;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodePosition() {
		return codePosition;
	}

	public void setCodePosition(String codePosition) {
		this.codePosition = codePosition;
	}

	@Override
	public String toString() {
		return "Position [longitude=" + longitude + ", latitude=" + latitude + ", code=" + code + "]";
	}

}
