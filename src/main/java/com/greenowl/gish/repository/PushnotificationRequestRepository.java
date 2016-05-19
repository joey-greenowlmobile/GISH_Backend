package com.greenowl.gish.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greenowl.gish.domain.PushnotificationRequest;

public interface PushnotificationRequestRepository extends JpaRepository<PushnotificationRequest,Long>{

	@Query("select P from PushnotificationRequest P where P.createTime>?1 and P.createTime<?2")
	public List<PushnotificationRequest> findBetweenCreateTime(Date time1,Date time2);
	
	
}
