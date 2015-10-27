package com.greenowl.gish.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_BUS_ARRIVAL_PREDICTION")
public class BusArrivalPrediction implements Serializable{

	private static final long serialVersionUID = 4288999000L;
	
	@Id
	private String stopTag;
	
	@Id
	private int routeId;
	
	private String agency;
	
	private String zone;
	
	private String transitMode;
	
	@Column(name="predictionXml",columnDefinition="TEXT")
	private String predictionXml;
	
	@Column(name="predictionJson",columnDefinition="TEXT")
	private String predictionJson;
	
	@Column(name="updateTime",columnDefinition="DATETIME")
	private Date updateTime;

	
	
	public String getStopTag() {
		return stopTag;
	}

	public void setStopTag(String stopTag) {
		this.stopTag = stopTag;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}
	
	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getTransitMode() {
		return transitMode;
	}

	public void setTransitMode(String transitMode) {
		this.transitMode = transitMode;
	}

	public String getPredictionXml() {
		return predictionXml;
	}

	public void setPredictionXml(String predictionXml) {
		this.predictionXml = predictionXml;
	}

	public String getPredictionJson() {
		return predictionJson;
	}

	public void setPredictionJson(String predictionJson) {
		this.predictionJson = predictionJson;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
}
