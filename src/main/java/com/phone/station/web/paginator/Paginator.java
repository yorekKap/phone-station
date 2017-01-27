package com.phone.station.web.paginator;

import java.util.List;

import org.apache.log4j.Logger;

import com.phone.station.web.paginator.functions.FindAllWithLimitFunction;


/**
 * Class for splitting large set of entities into pages
 *
 * @author yuri
 *
 * @param <T> - type of entities managed by corresponding DAO
 */
public class Paginator<T> {
	private static final Logger log = Logger.getLogger(Paginator.class);

	private static final int DEFAULT_NUM_OF_ROWS_PER_PAGE = 10;

	private int numOfRecordsPerPage = DEFAULT_NUM_OF_ROWS_PER_PAGE;

	public Paginator(int numOfRowsPerPage) {
		this.numOfRecordsPerPage = numOfRowsPerPage;
	}

	/**
	 * Find element from page with {@code pageIndex} index
	 *
	 *
	 * @param pageIndex - index of page
	 * @return {@link List} of entities.
	 */
	public List<T> findPage(int pageIndex, FindAllWithLimitFunction<T> findFunction){
		pageIndex = pageIndex - 1;
		int offset = pageIndex * numOfRecordsPerPage;
		List<T> result = findFunction.findAll(offset, numOfRecordsPerPage);
		if(result.isEmpty()){
			log.warn("No elements from offset: " + offset);
		}

		return result;
	}

	public int getNumOfPages(int numOfRecords){
		int numOfPages = (int)Math.ceil((double)numOfRecords / numOfRecordsPerPage);
		if(numOfPages == 0){
			return 1;
		}
		else{
			return numOfPages;
		}
	}

}
