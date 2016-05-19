package com.greenowl.gish.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PUSHNOTIFICATION_SEND")
public class PushnotificationSend implements Serializable{
	
	private static final long serialVersionUID = 32144325432L;
	
	@Id
	private Long pushRequestId;
	
	private Date sendTime;

	public Long getPushRequestId() {
		return pushRequestId;
	}

	public void setPushRequestId(Long pushRequestId) {
		this.pushRequestId = pushRequestId;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}	
	
}
