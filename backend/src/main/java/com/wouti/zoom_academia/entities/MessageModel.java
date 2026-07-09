package com.wouti.zoom_academia.entities;
import jakarta.persistence.*;

import com.wouti.zoom_academia.transverse.TypeMessage;

@Entity
public class MessageModel extends AuditModel {

	@Id
	@GeneratedValue(generator="messageModel_generator")
	@SequenceGenerator(
            name = "messageModel_generator",
            sequenceName = "messageModel_generator"
    )
    private Long id;

	private String codeMessageModel;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE")
	private TypeMessage type;

	private String contenue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeMessageModel() {
		return codeMessageModel;
	}

	public void setCodeMessageModel(String codeMessageModel) {
		this.codeMessageModel = codeMessageModel;
	}

	public TypeMessage getType() {
		return type;
	}

	public void setType(TypeMessage type) {
		this.type = type;
	}

	public String getContenue() {
		return contenue;
	}

	public void setContenue(String contenue) {
		this.contenue = contenue;
	}

}
