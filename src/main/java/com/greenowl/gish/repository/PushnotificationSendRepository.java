package com.greenowl.gish.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greenowl.gish.domain.PushnotificationSend;

public interface PushnotificationSendRepository extends JpaRepository<PushnotificationSend,Long>{

	public PushnotificationSend findByPushRequestId(Long requestId);
	
	@Query("select P from PushnotificationSend P where P.sendTime>?1 and P.sendTime<?2")
	public List<PushnotificationSend> findBetweenSendTime(Date time1, Date time2);	
	
}
