package com.geogenie.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="smartDevice")
@XmlRootElement(name="smartDevice")
@XmlAccessorType(XmlAccessType.NONE)
public class SmartDevice implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@XmlTransient
	private Long id;
	@Column(nullable=false)
	@XmlElement
	@NotNull(message="error.uniqueid.mandatory")
	private String uniqueId;
	@Column(nullable=false)
	@XmlElement
	private String privateKey;
	@Column(nullable=false)
	@XmlElement
	@NotNull(message="error.build.version.mandatory")
	private String buildVersion;
	@Column(nullable=false)
	@XmlElement
	@NotNull(message="error.osversion.mandatory")
	private String osVersion;
	@Column(nullable=false)
	@XmlElement
	@NotNull(message="error.device.type.mandatory")
	private DeviceType deviceType;
	@Column(nullable=false)
	@XmlTransient
	private String isEnabled;
	@XmlTransient
	@Column(nullable=false)
	private Date createDt;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id",nullable=false)
	@XmlElement
	@NotNull
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public String getBuildVersion() {
		return buildVersion;
	}

	public void setBuildVersion(String buildVersion) {
		this.buildVersion = buildVersion;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	@Override
	public String toString() {
		return "SmartDevice : [ uniqueId = "  + uniqueId+ " , " +
				"privateKey = " + privateKey + " , " +
				"buildVersion = " + buildVersion+ " , " +
				"osVersion = "  + osVersion + " , " +
				"deviceType = " + deviceType.toString() + " , " +
				"isEnabled = " + isEnabled +" , " +
				"user = " + user.toString() +
				"]";
	}
	
	
}
