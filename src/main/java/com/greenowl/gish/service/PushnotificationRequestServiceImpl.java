package com.greenowl.gish.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.greenowl.gish.domain.PushnotificationRequest;
import com.greenowl.gish.repository.PushnotificationRequestRepository;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class PushnotificationRequestServiceImpl implements PushnotificationRequestService{

	@Inject
	private PushnotificationRequestRepository pushnotificationRequestRepository;
	
	public List<PushnotificationRequest> findBetweenCreateTime(Date time1,Date time2){
		return pushnotificationRequestRepository.findBetweenCreateTime(time1, time2);
	}
	
	public PushnotificationRequest save(PushnotificationRequest pushnotificationRequest){
		return pushnotificationRequestRepository.save(pushnotificationRequest);
	}
	
}
