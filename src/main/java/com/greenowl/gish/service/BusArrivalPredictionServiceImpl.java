package com.greenowl.gish.service;

import com.greenowl.gish.domain.BusArrivalPrediction;
import com.greenowl.gish.repository.BusArrivalPredictionRepository;

public class BusArrivalPredictionServiceImpl implements BusArrivalPredictionService{

	private BusArrivalPredictionRepository busArrivalPredictionRepository;
	
	public BusArrivalPrediction findByStopTagAndRouteIdAndAgencyAndTransitModeAndZone(String stopTag, int routeId, String agency, String transitMode, String zone){
		return busArrivalPredictionRepository.findByStopTagAndRouteIdAndAgencyAndTransitModeAndZone(stopTag, routeId, agency, transitMode, zone);
	}
}
