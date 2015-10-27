package com.greenowl.gish.service;

import java.util.List;

import com.greenowl.gish.domain.BusRoute;

public interface BusRouteService {

	public BusRoute findByRouteId(int routeId);
	
	public List<BusRoute> findByAgencyAndTransitModeAndZone(String agency, String transitMode, String zone);
	
}
