package com.phone.station.web.paginator.records;

import java.util.List;

import com.phone.station.entities.Service;
import com.phone.station.service.interfaces.ServicesService;
import com.phone.station.web.paginator.RecordsCollection;

public class ServicesRecordsCollection implements RecordsCollection<Service> {

	private ServicesService servicesService;

	public ServicesRecordsCollection(ServicesService servicesService) {
		this.servicesService = servicesService;
	}

	@Override
	public List<Service> getRecords(int offset, int limit) {
		return servicesService.findAll(offset, limit);
	}

	@Override
	public int getNumOfRecords() {
		return servicesService.getNumOfRecords();
	}


}
