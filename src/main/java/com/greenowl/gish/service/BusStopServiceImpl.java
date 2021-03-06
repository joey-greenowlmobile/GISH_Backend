package com.greenowl.gish.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.greenowl.gish.domain.BusStop;
import com.greenowl.gish.repository.BusStopRepository;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class BusStopServiceImpl implements BusStopService{

	@Inject
	private BusStopRepository busStopRepository;
	
    public BusStop findByStopTagAndRouteId(String stopTag, int routeId){
    	return busStopRepository.findByStopTagAndRouteId(stopTag, routeId);
    }
	
	public List<BusStop> findByRouteIdAndTransitModeAndZone(int routeId, String transitMode, String zone){
		return busStopRepository.findByRouteIdAndTransitModeAndZone(routeId, transitMode, zone);
	}
	
	
}
