package com.greenowl.gish.domain;

import java.io.Serializable;

public class BusStopPK implements Serializable{

	private static final long serialVersionUID = 85345394L;
	
	private int routeId;
	private String stopTag;
	
	public BusStopPK(){}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getStopTag() {
		return stopTag;
	}

	public void setStopTag(String stopTag) {
		this.stopTag = stopTag;
	}
	
	@Override
	public int hashCode(){
		return routeId*stopTag.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		BusStopPK pk = (BusStopPK)obj;
		return this.routeId==pk.routeId && this.stopTag.equals(pk.getStopTag());
	}
	
	
	
}
