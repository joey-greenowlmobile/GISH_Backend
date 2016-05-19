package com.greenowl.gish.service;

import java.util.Date;
import java.util.List;

import com.greenowl.gish.domain.PushnotificationSend;

public interface PushnotificationSendService {

	public PushnotificationSend save(PushnotificationSend pushnotificationSend);
	public List<PushnotificationSend> findBetweenSendTime(Date time1, Date time2);
	
}
