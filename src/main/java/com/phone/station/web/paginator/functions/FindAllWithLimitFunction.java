package com.phone.station.web.paginator.functions;

import java.util.List;

@FunctionalInterface
public interface FindAllWithLimitFunction<T> {

	List<T> findAll(int offset, int limit);
}
