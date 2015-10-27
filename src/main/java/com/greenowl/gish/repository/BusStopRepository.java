package com.greenowl.gish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenowl.gish.domain.BusStop;

public interface BusStopRepository extends JpaRepository<BusStop,Integer>{

	@SuppressWarnings("unchecked")
	public BusStop save(BusStop busStop);
	
	public BusStop findByStopTagAndRouteId(String stopTag, int routeId);
	
	public List<BusStop> findByRouteIdAndTransitModeAndZone(int routeId, String transitMode, String zone);
	
}
