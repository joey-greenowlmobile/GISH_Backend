package com.greenowl.gish.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PUSHNOTIFICATION_REQUEST")
public class PushnotificationRequest implements Serializable{
	
	private static final long serialVersionUID = 841515415234L;

	@Id
	private Long Id;
	
	private String appVersion;
	
	private String phoneId;
	
	private String agencyId;
	
	private String tansitMode;
	
	private String transitStopTag;
	
	private Date createTime;
	
	private int notifyingMinutes;
	
	private String routeId;	
	
	private int vehicleId;	

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getTansitMode() {
		return tansitMode;
	}

	public void setTansitMode(String tansitMode) {
		this.tansitMode = tansitMode;
	}

	public String getTransitStopTag() {
		return transitStopTag;
	}

	public void setTransitStopTag(String transitStopTag) {
		this.transitStopTag = transitStopTag;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getNotifyingMinutes() {
		return notifyingMinutes;
	}

	public void setNotifyingMinutes(int notifyingMinutes) {
		this.notifyingMinutes = notifyingMinutes;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}    
	
	
}
