package com.greenowl.gish.service;

import com.greenowl.gish.domain.BusArrivalPrediction;

public interface BusArrivalPredictionService {

	public BusArrivalPrediction findByStopTagAndAgencyAndTransitModeAndZone(String stopTag, String agency, String transitMode, String zone);
}
