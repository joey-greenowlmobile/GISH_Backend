package com.greenowl.gish.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_BUS_ROUTE")
public class BusRoute implements Serializable{

	private static final long serialVersionUID = 924329L;
	
	@Id
	private int routeId;	
	
	private String routeTag;	
	
	private String routeType;
	
	private String agency;
	
	private String title;
	
	private String transitMode;
	
	private String zone;
	
	
	public BusRoute(){}


	public int getRouteId() {
		return routeId;
	}


	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}


	public String getRouteTag() {
		return routeTag;
	}


	public void setRouteTag(String routeTag) {
		this.routeTag = routeTag;
	}
	

	public String getRouteType() {
		return routeType;
	}


	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}


	public String getAgency() {
		return agency;
	}


	public void setAgency(String agency) {
		this.agency = agency;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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
