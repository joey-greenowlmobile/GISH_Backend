package com.greenowl.gish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greenowl.gish.domain.BusArrivalPrediction;

public interface BusArrivalPredictionRepository extends JpaRepository<BusArrivalPrediction,Integer>{

	@SuppressWarnings("unchecked")
	public BusArrivalPrediction save(BusArrivalPrediction prediction);
	
	public BusArrivalPrediction findByStopTagAndAgencyAndTransitModeAndZone(String stopTag, String agency, String transitMode, String zone);
	
	public void deleteAllInBatch();

	@Query("delete from BusArrivalPrediction where stopTag = ?1 and agency = ?2")
	public void deleteByStopTagAndAgency(String stopTag, String agency);
	
}
