package com.greenowl.gish.web.rest;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenowl.gish.domain.BusArrivalPrediction;
import com.greenowl.gish.service.BusArrivalPredictionService;

@RestController
@RequestMapping("/api/transit")
public class BusArrivalPredictionResource {
	private static final Logger LOG = LoggerFactory.getLogger(AdminResource.class);
    @Inject
    private BusArrivalPredictionService busArrivalPredictionService;
    @RequestMapping(value = "/predictions",
            method = RequestMethod.GET, // <-- The HTTP Verb
            produces = MediaType.APPLICATION_JSON_VALUE) // <-- The return type
    public String getPrediciton(@RequestParam("agency_id") String agencyId,@RequestParam("transit_mode") String transitMode,
    		@RequestParam("zone") String zone,@RequestParam("transit_route_tag") int routeId,@RequestParam("transit_stop_tag") String stopId)
    {
    	 LOG.debug(agencyId+" "+transitMode+" "+zone+" "+routeId+" "+stopId);
    	 BusArrivalPrediction busArrivalPredicition=busArrivalPredictionService.findByStopTagAndRouteIdAndAgencyAndTransitModeAndZone(stopId, routeId, agencyId, transitMode, zone);
    	 if (busArrivalPredicition==null)
    		 return null;
    	 else return busArrivalPredicition.getPredictionJson();
    }
}
