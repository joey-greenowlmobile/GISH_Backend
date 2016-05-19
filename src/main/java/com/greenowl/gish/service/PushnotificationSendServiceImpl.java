package com.greenowl.gish.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.greenowl.gish.domain.PushnotificationSend;
import com.greenowl.gish.repository.PushnotificationSendRepository;

public class PushnotificationSendServiceImpl implements PushnotificationSendService{
	
	@Inject
	private PushnotificationSendRepository pushNotificationSendRepository;
	
	public PushnotificationSend save(PushnotificationSend pushnotificationSend){
		return pushNotificationSendRepository.save(pushnotificationSend);
	}
	
	public List<PushnotificationSend> findBetweenSendTime(Date time1, Date time2){
		return pushNotificationSendRepository.findBetweenSendTime(time1, time2);
	}
	
}
