package com.greenowl.gish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.greenowl.gish.domain.BusLocation;

public interface BusLocationRepository extends JpaRepository<BusLocation,Integer>{

	
	@SuppressWarnings("unchecked")
	public BusLocation save(BusLocation busLocation);
	
	public BusLocation findByRouteIdAndAgencyAndTransitModeAndZone(int routeId, String agency, String transitMode, String zone);
	
	public void deleteAllInBatch();
	
	@Modifying
	@Query("delete from BusLocation where routeId = ?1 and agency = ?2")
	public void deleteByRouteIdAndAgency(int routeId, String agency);
	
}
