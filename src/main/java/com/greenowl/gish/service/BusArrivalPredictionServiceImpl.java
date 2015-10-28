package com.greenowl.gish.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.greenowl.gish.domain.BusArrivalPrediction;
import com.greenowl.gish.repository.BusArrivalPredictionRepository;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class BusArrivalPredictionServiceImpl implements BusArrivalPredictionService{

	@Inject
	private BusArrivalPredictionRepository busArrivalPredictionRepository;
	
	public BusArrivalPrediction findByStopTagAndRouteIdAndAgencyAndTransitModeAndZone(String stopTag, int routeId, String agency, String transitMode, String zone){
		return busArrivalPredictionRepository.findByStopTagAndRouteIdAndAgencyAndTransitModeAndZone(stopTag, routeId, agency, transitMode, zone);
	}
}
