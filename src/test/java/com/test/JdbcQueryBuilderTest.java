package com.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.phone.station.dao.builder.DeleteQuery;
import com.phone.station.dao.builder.JdbcQueryBuilder;
import com.phone.station.dao.builder.SelectQuery;
import com.phone.station.dao.factories.DataSourceFactory;
import com.phone.station.entities.Service;

import junit.framework.Assert;

import static junit.framework.Assert.*;

public class JdbcQueryBuilderTest {

	static JdbcQueryBuilder builder;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataSource mockDataSource = Mockito.mock(DataSource.class);
		Connection mockConnection = Mockito.mock(Connection.class);

		when(mockDataSource.getConnection()).thenReturn(mockConnection);

		builder = new JdbcQueryBuilder(mockDataSource);
	}

	@Test
	public void testInsert() {
		String expectedQuery = "INSERT INTO services(cost, description, title) VALUES (?, ?, ?) ;";
		Service service = new Service();
		service.setTitle("Repair something");
		service.setDescription("Master will come and repair something for you");
		service.setCost(20.5);

		String query =  builder.insert().into("services")
							  	.setValue("title", service.getTitle())
							  	.setValue("description", service.getDescription())
							  	.setValue("cost", service.getCost())
							  	.toString();

		assertEquals(expectedQuery, query);
	}

	@Test
	public void testSelect(){
		String expectedQuery = "SELECT * FROM services;";

		String query =	builder.select("*").from("services").toString();

		Assert.assertEquals(expectedQuery, query);
	}
	@Test
	public void testSelectWhere(){
		String expectedQuery = "SELECT * FROM admins WHERE id >2.0;";

	    String query =	builder.select("*").from("admins")
	    		       						.where("id")
	    		       						.greater(2)
	    		       						.toString();

	    Assert.assertEquals(expectedQuery, query);
	}


	@Test
	public void testInnerQuery(){
		String expectedQuery = "DELETE FROM service_bids\n"+
		" WHERE service_id IN (SELECT max(id) FROM services);";

		SelectQuery sq = builder.select("max(id)").from("services");
		String query =  builder.delete().from("service_bids").where("service_id").in(sq).toString();

		assertEquals(expectedQuery, query);
	}

	@Test
	public void testUpdate(){
		String expectedQuery = " UPDATE services SET description =  ? title =  ?  WHERE id  = 2.0;";

		String query = builder.update("services").set("title", "Change number")
												 .set("description", "Number should be changed")
												 .where("id").isEquals(new Double(2))
												 .toString();
		assertEquals(expectedQuery, query);
	}

	@Test
	public void testDelete(){
		String expectedQuery = "DELETE FROM services\n" +
				" WHERE title  = 'Change number' AND description LIKE '%changed';";

		String query = builder.delete().from("services")
									   .where("title")
									   .isEquals("Change number")
									   .and("description")
									   .like("%changed")
									   .toString();

		assertEquals(expectedQuery, query);
	}
}
