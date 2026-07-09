package com.wouti.zoom_academia.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "ZONIER")
public class ZoneCouverture extends AuditModel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1577891551826564805L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;

	private String codeZoneCouverture;
	private String intitule;
	private String key;

	@JoinColumn()
	@ManyToOne
	private ZoneCouverture father;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodeZoneCouverture() {
		return codeZoneCouverture;
	}

	public void setCodeZoneCouverture(String codeZoneCouverture) {
		this.codeZoneCouverture = codeZoneCouverture;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public ZoneCouverture getFather() {
		return father;
	}

	public void setFather(ZoneCouverture father) {
		this.father = father;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ZoneCouverture(long id, String codeZoneCouverture, String intitule, String key, ZoneCouverture father) {
		super();
		this.id = id;
		this.codeZoneCouverture = codeZoneCouverture;
		this.intitule = intitule;
		this.key = key;
		this.father = father;
	}

	public ZoneCouverture() {
		super();
	}

	@Override
	public String toString() {
		return "ZoneCouverture [id=" + id + ", codeZoneCouverture=" + codeZoneCouverture + ", intitule=" + intitule
				+ ", key=" + key + ", father=" + father + "]";
	}

}
