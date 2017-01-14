package com.phone.station.dao.interfaces;

import java.util.List;

import com.phone.station.entities.ServiceBid;
import com.phone.station.entities.enums.ServiceBidStatus;

public interface ServiceBidDao extends GenericDao<ServiceBid, Long> {

	List<ServiceBid> findByServiceId(Long serviceId);
	List<ServiceBid> findBySubscriberId(Long subscriberId);
	List<ServiceBid> findByStatus(ServiceBidStatus status);

}
