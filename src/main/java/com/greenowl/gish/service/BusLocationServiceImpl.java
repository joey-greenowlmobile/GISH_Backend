package com.greenowl.gish.service;

import com.greenowl.gish.domain.BusLocation;
import com.greenowl.gish.repository.BusLocationRepository;

public class BusLocationServiceImpl implements BusLocationService{

	
	private BusLocationRepository busLocationRepository;
	
	public BusLocation findByRouteIdAndAgencyAndTransitModeAndZone(int routeId, String agency, String transitMode, String zone){
		return busLocationRepository.findByRouteIdAndAgencyAndTransitModeAndZone(routeId, agency, transitMode, zone);	
	}
	
}
