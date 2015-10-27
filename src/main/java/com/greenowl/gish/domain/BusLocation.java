package com.greenowl.gish.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_BUS_LOCATION")
public class BusLocation implements Serializable{

	private static final long serialVersionUID = 2143178L;
	
	@Id
	private int routeId;
	
	private String agency;
	
	@Column(name="locationXml",columnDefinition="TEXT")
	private String locationXml;
	
	@Column(name="locationJson",columnDefinition="TEXT")
	private String locationJson;
	
	@Column(name="updateTime",columnDefinition="DATETIME")
	private Date updateTime;
	
	private String transitMode;
	
	private String zone;
	

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getLocationXml() {
		return locationXml;
	}

	public void setLocationXml(String locationXml) {
		this.locationXml = locationXml;
	}

	public String getLocationJson() {
		return locationJson;
	}

	public void setLocationJson(String locationJson) {
		this.locationJson = locationJson;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getTransitMode() {
		return transitMode;
	}

	public void setTransitMode(String transitMode) {
		this.transitMode = transitMode;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}	
	
}
