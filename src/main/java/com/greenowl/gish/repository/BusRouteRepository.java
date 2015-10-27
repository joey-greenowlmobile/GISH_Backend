package com.greenowl.gish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.greenowl.gish.domain.BusRoute;


public interface BusRouteRepository extends JpaRepository<BusRoute, Integer>{

	@SuppressWarnings("unchecked")
	public BusRoute save(BusRoute busRoute);
	
	public BusRoute findByRouteId(int routeId);
	
	public List<BusRoute> findByAgencyAndTransitModeAndZone(String agency, String transitMode, String zone);
	
}
