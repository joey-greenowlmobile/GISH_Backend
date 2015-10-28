package com.greenowl.gish.service;

import com.greenowl.gish.domain.BusArrivalPrediction;

public interface BusArrivalPredictionService {

	public BusArrivalPrediction findByStopTagAndRouteIdAndAgencyAndTransitModeAndZone(String stopTag, int routeId, String agency, String transitMode, String zone);
}
