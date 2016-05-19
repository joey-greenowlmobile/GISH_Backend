package com.greenowl.gish.web.rest;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenowl.gish.domain.BusArrivalPrediction;
import com.greenowl.gish.repository.BusArrivalPredictionRepository;

@RestController
@RequestMapping("/api")
public class BusArrivalPredictionController {

	@Inject
	private BusArrivalPredictionRepository busArrivalPredictionReposity;
	
	@RequestMapping("/test2")
	public String test(@RequestParam(value="transit_stop_tag",required=true) String stopTag, @RequestParam(value="transit_route_tag",required=true) int routeId, @RequestParam(value="agency_id",required=true) String agency, @RequestParam(value="transit_mode",required=true) String transitMode, @RequestParam(value="zone",required=true) String zone){  	
	    BusArrivalPrediction prediction = busArrivalPredictionReposity.findByStopTagAndRouteIdAndAgencyAndTransitModeAndZone(stopTag,routeId,agency,transitMode,zone);
	    return prediction.getPredictionJson();	
	}
	
}
