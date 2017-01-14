package com.phone.station.entities;

import java.util.Date;

import com.phone.station.dao.annotations.Column;
import com.phone.station.dao.annotations.Id;
import com.phone.station.dao.annotations.ParseEnum;
import com.phone.station.dao.annotations.Table;
import com.phone.station.entities.enums.ServiceBidStatus;

@Table("service_bids")
public class ServiceBid implements Identified<Long> {

	@Id
	@Column("id")
	private Long id;

	@Column("service_id")
	private Long serviceId;

	@Column("subscriber_id")
	private Long subscriberId;

	@Column("service_bid_status")
	@ParseEnum
	private ServiceBidStatus status;

	@Column("admin_comment")
	private String adminComment;

	public ServiceBid() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
	}

	public ServiceBidStatus getStatus() {
		return status;
	}

	public void setStatus(ServiceBidStatus status) {
		this.status = status;
	}

	public String getAdminComment() {
		return adminComment;
	}

	public void setAdminComment(String adminComment) {
		this.adminComment = adminComment;
	}


}
