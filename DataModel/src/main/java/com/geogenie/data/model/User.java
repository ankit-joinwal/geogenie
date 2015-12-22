package com.geogenie.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Ankit.Joinwal
 * 
 */
@Entity
@Table(name = "userDetails")
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.NONE)
@NamedQuery(name = "getUserByEmail", query = "from User where emailId like :emailId")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@XmlTransient
	private Long id;
	@XmlElement
	@Column(nullable = false)
	@NotNull(message="error.name.mandatory")
	private String name;
	@XmlElement
	@Column(nullable = false)
	@NotNull(message="error.email.mandatory")
	private String emailId;
	@XmlTransient
	@Column(nullable = false)
	private Date createDt;
	
	@XmlTransient
	@Column(nullable=false)
	private Integer dailyQuota;
	

	public Integer getDailyQuota() {
		return dailyQuota;
	}

	public void setDailyQuota(Integer dailyQuota) {
		this.dailyQuota = dailyQuota;
	}

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	@Override
	public String toString() {
		return "[ name = " + name + " , emailId = " + emailId;
	}

}
