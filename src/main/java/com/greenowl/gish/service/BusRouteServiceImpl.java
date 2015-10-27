package com.greenowl.gish.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.greenowl.gish.domain.BusRoute;
import com.greenowl.gish.repository.BusRouteRepository;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class BusRouteServiceImpl implements BusRouteService{

	@Inject
	private BusRouteRepository busRouteRepository;
	
	public BusRoute save(BusRoute busRoute){
		return busRouteRepository.save(busRoute);
	}
	
	public BusRoute findByRouteId(int routeId){
		return busRouteRepository.findByRouteId(routeId);
	}
	
	public List<BusRoute> findByAgencyAndTransitModeAndZone(String agency, String transitMode, String zone){
		return busRouteRepository.findByAgencyAndTransitModeAndZone(agency,transitMode,zone);
	}
	
	
}
