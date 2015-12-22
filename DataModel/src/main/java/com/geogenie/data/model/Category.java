package com.geogenie.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author ajoinwal
 *
 */
@Entity
@XmlRootElement(name="category")
@XmlAccessorType(XmlAccessType.NONE)
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@XmlTransient
	private Long id;
	@Column(nullable=false)
	@XmlElement
	@NotNull(message="error.name.mandatory")
	private String name;
	@Column(nullable=false)
	@XmlElement
	@NotNull(message="error.desc.mandatory")
	private String description;
	@Column(nullable=false)
	@XmlTransient
	private Date createDt;

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getCreateDt() {
		return createDt;
	}


	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}


	@Override
	public String toString() {
		return "Category : [ name = "+name+" , description = "+description + " ]";
	}
}
