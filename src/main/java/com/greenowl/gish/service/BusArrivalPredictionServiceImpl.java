package com.greenowl.gish.service;

import com.greenowl.gish.domain.BusArrivalPrediction;
import com.greenowl.gish.repository.BusArrivalPredictionRepository;

public class BusArrivalPredictionServiceImpl implements BusArrivalPredictionService{

	private BusArrivalPredictionRepository busArrivalPredictionRepository;
	
	public BusArrivalPrediction findByStopTagAndAgencyAndTransitModeAndZone(String stopTag, String agency, String transitMode, String zone){
		return busArrivalPredictionRepository.findByStopTagAndAgencyAndTransitModeAndZone(stopTag, agency, transitMode, zone);
	}
}
