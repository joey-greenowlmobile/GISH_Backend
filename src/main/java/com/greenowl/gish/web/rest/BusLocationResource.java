package com.greenowl.gish.web.rest;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenowl.gish.domain.BusLocation;
import com.greenowl.gish.service.BusLocationService;

@RestController
@RequestMapping("/api/transit")
public class BusLocationResource {
	private static final Logger LOG = LoggerFactory.getLogger(AdminResource.class);
    @Inject
    private BusLocationService busLocationService;
	@RequestMapping(value = "/locations",
	            method = RequestMethod.GET, // <-- The HTTP Verb
	            produces = MediaType.APPLICATION_JSON_VALUE) // <-- The return type
	 
	public String getLocation(@RequestParam("agency_id") String agencyId,@RequestParam("transit_mode") String transitMode,
	    		@RequestParam("zone") String zone,@RequestParam("transit_route_tag") int routeId) {
		LOG.debug(agencyId+" "+transitMode+" "+zone+" "+routeId);
		BusLocation buslocation= busLocationService.findByRouteIdAndAgencyAndTransitModeAndZone(routeId, agencyId, transitMode, zone);
		if (buslocation==null){
			return null;
		}
		return buslocation.getLocationJson();
	}
}
