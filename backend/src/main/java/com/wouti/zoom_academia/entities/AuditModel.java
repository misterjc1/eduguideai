package com.wouti.zoom_academia.entities;

import java.io.Serializable;
import java.util.Date;


import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditModel implements Serializable {

    private static final long serialVersionUID = 1L;

	/** {@link Date} de création de l'entité. */
	@Column(name = "HOR_CREAT", nullable = false , length = 6)
	@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
	private Date creationDate;

	/** Identifiant du créateur de l'entité */
	@Column(name = "ID_UTIL_CREAT", length = 50, nullable = false)
	private String creatorCode;

	/** {@link Date} de la suppression (logique) de l'entité */
	@Column(name = "HOR_SUPPR" , length = 6)
	@Temporal(TemporalType.TIMESTAMP)
	private Date deleteDate;

	/** Identifiant de l'utilisateur qui a supprimé (logiquement) l'entité. */
	@Column(name = "ID_UTIL_SUPPR", length = 50)
	private String deleterCode;

	/** {@link Date} de la dernière mise à jour de l'entité */
	@Column(name = "HOR_MISE_A_JOUR" , length = 6)
	@Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
	private Date updateDate;

	/** Identifiant de l'utilisateur qui a mis à jour l'entité pour la dernière fois. */
	@Column(name = "IDI_UTIL_MAJ", length = 50)
	private String updaterCode;

	/** {@link Boolean} indiquant si l'entité est "supprimée" (suppression logique). */
	@Column(name = "BOO_SUPPR", nullable = false)
	@Convert(converter = org.hibernate.type.YesNoConverter.class)
	private boolean isDeleted = false; 

	/** {@link Date} de la dernière synchronisation. */
	@Column(name = "HOR_SYNCHRO" , length = 6)
	@Temporal(TemporalType.TIMESTAMP)
	private Date synchronizationDate;


	public String getCreatorCode() {
		return creatorCode;
	}

	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
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

	public String getUpdaterCode() {
		return updaterCode;
	}

	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getSynchronizationDate() {
		return synchronizationDate;
	}

	public void setSynchronizationDate(Date synchronizationDate) {
		this.synchronizationDate = synchronizationDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}





    // Getters and Setters (Omitted for brevity)

}
