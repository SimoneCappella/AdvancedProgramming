package it.univpm.hhc.model.entities;

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
@Table(name="album")
public class Prova implements Serializable {

	private Long id;
	private String title;
	private int version;
	
	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	@Version
	@Column(name = "VERSION")
	public int getVersion() {
		return version;
	}

	@Column
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
