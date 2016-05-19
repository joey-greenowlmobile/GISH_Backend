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

import com.greenowl.gish.domain.BusRoute;
import com.greenowl.gish.service.BusRouteService;

@RestController
@RequestMapping("/api/transit")
public class BusRouteResource {

	private static final Logger LOG = LoggerFactory.getLogger(BusRouteResource.class);
	
	@Inject
	BusRouteService busRouteService;	
	
	@RequestMapping(value = "/routes",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public String getRoutes(@RequestParam("agency_id") String agencyId,@RequestParam("transit_mode") String transitMode,@RequestParam("zone") String zone){
		List<BusRoute>  routes = busRouteService.findByAgencyAndTransitModeAndZone(agencyId, transitMode, zone);
		LOG.info("routes size:"+(routes==null?0:routes.size()));
		if(routes!=null){
			StringBuilder jsonBuilder = new StringBuilder(); 
			jsonBuilder.append("{\n");
			jsonBuilder.append("\"body\":{\n");
			jsonBuilder.append("\"copyright\": \"All data copyright Toronto Transit Commission ");
			Calendar calendar = Calendar.getInstance();
			jsonBuilder.append(calendar.get(Calendar.YEAR));
			jsonBuilder.append(".\",\n");
			jsonBuilder.append("\"route\":[\n");
			for(int i=0;i<routes.size();i++){
				BusRoute route = routes.get(i);
				jsonBuilder.append("{");
				jsonBuilder.append("\"tag\":");
				jsonBuilder.append("\"");
				jsonBuilder.append(route.getRouteTag());
				jsonBuilder.append("\",");
				jsonBuilder.append("\"title\":");
				jsonBuilder.append("\"");
				jsonBuilder.append(route.getTitle());
				jsonBuilder.append("\"");
				jsonBuilder.append("}");
				if(i<(routes.size()-1)){
					jsonBuilder.append(",");
				}
				jsonBuilder.append("\n");
			}
			jsonBuilder.append("]\n");
			jsonBuilder.append("}\n");
			jsonBuilder.append("}\n");
			return jsonBuilder.toString();
		}		
		return null;
	}
	
	
	
}
