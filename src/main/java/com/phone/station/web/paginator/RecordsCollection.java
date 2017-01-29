package com.phone.station.web.paginator;

import java.util.List;

public interface RecordsCollection<T> {

	List<T> getRecords(int offset, int limit);

	int getNumOfRecords();

}
