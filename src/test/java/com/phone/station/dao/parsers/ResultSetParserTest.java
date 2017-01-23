package com.phone.station.dao.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.phone.station.entities.Service;

public class ResultSetParserTest {

	private static final double DELTA = 1e-15;

	private static final int COLUMN_COUNT = 4;

	private static final Integer ID = new Integer(1);
	private static final String TITLE = "Service title";
	private static final String DESCRIPTION = "Description";
	private static final Double COST = new Double(100.5);

	ResultSetParser resultSetParser;
	Map<Integer, Object> resultSetObjects = new HashMap<Integer, Object>(){{
		put(1, ID);
		put(2, TITLE);
		put(3, DESCRIPTION);
		put(4, COST);
	}};

	@Before
	public void setUp(){
		ResultSet rs = mock(ResultSet.class);
		ResultSetMetaData rsmd = mock(ResultSetMetaData.class);
		try {

			when(rsmd.getColumnCount()).thenReturn(COLUMN_COUNT);
			when(rsmd.getTableName(anyInt())).thenReturn("services");
			when(rsmd.getColumnName(1)).thenReturn("id");
			when(rsmd.getColumnName(2)).thenReturn("title");
			when(rsmd.getColumnName(3)).thenReturn("description");
			when(rsmd.getColumnName(4)).thenReturn("cost");

			when(rs.getMetaData()).thenReturn(rsmd);

			for (Entry<Integer, Object> e : resultSetObjects.entrySet()) {
				when(rs.getObject(e.getKey())).thenReturn(e.getValue());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		resultSetParser = new ResultSetParser(rs);


	}

	@Test
	public void parseResultSetToObject() {
		Service expectedService = new Service();
		expectedService.setId(new Long(ID));
		expectedService.setTitle(TITLE);
		expectedService.setDescription(DESCRIPTION);
		expectedService.setCost(COST);

		Service actualService = resultSetParser.parseResultSetToObject(Service.class);

		assertEquals(expectedService.getId(), actualService.getId());
		assertEquals(expectedService.getTitle(), actualService.getTitle());
		assertEquals(expectedService.getDescription(), actualService.getDescription());
		assertEquals(expectedService.getCost(), actualService.getCost(), DELTA);

	}

}
