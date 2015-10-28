package com.greenowl.gish.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.greenowl.gish.domain.BusLocation;
import com.greenowl.gish.repository.BusLocationRepository;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class BusLocationServiceImpl implements BusLocationService{

	@Inject
	private BusLocationRepository busLocationRepository;
	
	public BusLocation findByRouteIdAndAgencyAndTransitModeAndZone(int routeId, String agency, String transitMode, String zone){
		return busLocationRepository.findByRouteIdAndAgencyAndTransitModeAndZone(routeId, agency, transitMode, zone);	
	}
	
}
