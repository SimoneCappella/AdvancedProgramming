package it.univpm.hhc.model.entities;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="prova")
public class Prova implements Serializable {

	private String provaId;
	private String title;
	private String description;
	
	@Id
	@Column(name = "PROVA_ID")
	public String getProvaId() {
		return this.provaId;
	}
	public void setProvaId(String provaId){
		this.provaId= provaId;
	}

	@Column(name ="TITLE")
	public String getTitle() {
		return this.title;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
