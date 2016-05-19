package com.greenowl.gish.web.rest;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenowl.gish.domain.BusStop;
import com.greenowl.gish.service.BusStopService;

@RestController
@RequestMapping("/api/transit")
public class BusStopResource {

	private static final Logger LOG = LoggerFactory.getLogger(BusStopResource.class);
	
	@Inject
	BusStopService busStopService;
	
	@RequestMapping(value = "/stops",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public String getStops(@RequestParam("agency_id") String agencyId,@RequestParam("transit_mode") String transitMode,@RequestParam("zone") String zone,@RequestParam("transit_route_tag") int routeId){
		List<BusStop> stops = busStopService.findByRouteIdAndTransitModeAndZone(routeId, transitMode, zone);
		LOG.info("stops size:"+(stops==null?0:stops.size()));
		if(stops!=null){
			StringBuilder jsonBuilder = new StringBuilder();
			jsonBuilder.append("{\n");
			jsonBuilder.append("\"body\":{\n");
			jsonBuilder.append("\"copyright\": \"All data copyright Toronto Transit Commission ");
			Calendar calendar = Calendar.getInstance();
			jsonBuilder.append(calendar.get(Calendar.YEAR));
			jsonBuilder.append(".\",\n");
			jsonBuilder.append("\"route\" {\n");
			jsonBuilder.append("\"tag\":\"");
			jsonBuilder.append(routeId);
			jsonBuilder.append("\",\n");
			jsonBuilder.append("\"stop\":[\n");
			for(int i=0;i<stops.size();i++){
				BusStop busStop = stops.get(i);
				jsonBuilder.append("{\n");
				jsonBuilder.append("\"tag=\":\"");
				jsonBuilder.append(busStop.getStopTag());
				jsonBuilder.append("\",\n");
				jsonBuilder.append("\"title=\":\"");
				jsonBuilder.append(busStop.getTitle());
				jsonBuilder.append("\",\n");
				jsonBuilder.append("\"lat=\":\"");
				jsonBuilder.append(busStop.getLat());
				jsonBuilder.append("\",\n");
				jsonBuilder.append("\"lon=\":\"");
				jsonBuilder.append(busStop.getLon());
				jsonBuilder.append("\",\n");
				jsonBuilder.append("\"stopId=\":\"");
				jsonBuilder.append(busStop.getStopId());
				jsonBuilder.append("\"\n");
				jsonBuilder.append("}");
				if(i<(stops.size()-1)){
				   jsonBuilder.append(",");	
				}
				jsonBuilder.append("\n");
			}
			jsonBuilder.append("]\n");
			jsonBuilder.append("}\n");
			jsonBuilder.append("}\n");
			jsonBuilder.append("}\n");
			return jsonBuilder.toString();
		}		
		return null;
	}	
}
