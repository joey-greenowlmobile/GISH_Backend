package com.greenowl.gish.service;

import java.util.Date;
import java.util.List;

import com.greenowl.gish.domain.PushnotificationRequest;

public interface PushnotificationRequestService {

	public List<PushnotificationRequest> findBetweenCreateTime(Date time1,Date time2);
	public PushnotificationRequest save(PushnotificationRequest pushnotificationRequest);
	
}
