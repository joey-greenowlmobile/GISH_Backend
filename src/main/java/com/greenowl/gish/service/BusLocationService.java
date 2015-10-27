package com.greenowl.gish.service;

import com.greenowl.gish.domain.BusLocation;

public interface BusLocationService {

	public BusLocation findByRouteIdAndAgencyAndTransitModeAndZone(int routeId, String agency, String transitMode, String zone);	
	
}
