package com.greenowl.gish.service;

import java.util.List;

import com.greenowl.gish.domain.BusStop;

public interface BusStopService {

	public BusStop findByStopTagAndRouteId(String stopTag, int routeId);
	
	public List<BusStop> findByRouteIdAndTransitModeAndZone(int routeId, String transitMode, String zone);
}
