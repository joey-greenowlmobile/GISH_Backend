package com.greenowl.gish.web.rest;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenowl.gish.domain.PushnotificationRequest;
import com.greenowl.gish.exception.ErrorResponseFactory;
import com.greenowl.gish.service.PushnotificationRequestService;

@RestController
@RequestMapping("/api/pushnotification")
public class BusPushnotificationResource {

	@Inject
	PushnotificationRequestService pushnotificationRequestService;
	
	@RequestMapping(value = "/bus_arrival_alert",
            method = RequestMethod.POST, 
            produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<?> subscribePushnotification(@RequestParam("app_version") String appVersion, @RequestParam("phone_id") String phoneId, @RequestParam("agency_id") String agencyId,@RequestParam("transit_mode") String transitMode,
    		@RequestParam("transit_stop_tag") String stopId,@RequestParam("vehicle") int vehicleId, @RequestParam("route_id") String routeId, @RequestParam("notify_minutes") int notifyingMinutes) {
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.SECOND, -30);
		Calendar calendar2 = Calendar.getInstance();		
		List<PushnotificationRequest> pushRequests = pushnotificationRequestService.findBetweenCreateTime(calendar1.getTime(), calendar2.getTime());
		if(pushRequests!=null && pushRequests.size()>0){
		     return new ResponseEntity<>(ErrorResponseFactory.genericBadReq("Please only subscribe one push notification in 30 seconds","/bus_arrival_alert"),org.springframework.http.HttpStatus.BAD_REQUEST);
		}
		else{
			PushnotificationRequest pushRequest = new PushnotificationRequest();
			pushRequest.setAppVersion(appVersion);
			pushRequest.setPhoneId(phoneId);
			pushRequest.setAgencyId(agencyId);
			pushRequest.setTansitMode(transitMode);
			pushRequest.setTransitStopTag(stopId);
			pushRequest.setVehicleId(vehicleId);
			pushRequest.setRouteId(routeId);
			pushRequest.setNotifyingMinutes(notifyingMinutes);
			return new ResponseEntity<>(pushnotificationRequestService.save(pushRequest),org.springframework.http.HttpStatus.OK);
		}
		
	}
	
}
