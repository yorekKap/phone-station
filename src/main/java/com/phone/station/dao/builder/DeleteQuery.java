package com.phone.station.dao.builder;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import com.phone.station.exceptions.dao.MySQLDeleteException;


/**
 *  Used for the {@code DELETE} query building
 *
 * @author yuri
 *
 */
public class DeleteQuery extends WhereQuery<DeleteQuery>{
	private final static Logger log = Logger.getLogger(DeleteQuery.class);

	private Connection connection;
	private String tableName;

	public DeleteQuery(Connection connection, String tableName) {
		super(DeleteQuery.class);
		this.connection = connection;
		this.tableName = tableName;
	}

	public DeleteQuery from(String tableName){
		this.tableName = tableName;
		return this;
	}

	public int execute(){
		try(Statement statement = connection.createStatement()){
			log.info(toString() + " query executing");

			return statement.executeUpdate(toString());
		}catch(SQLException e){
			log.error("SQlException in DeleteQuery : ", e);

			throw new MySQLDeleteException(e.getMessage());
		}
	}

	public String toString(){
		StringBuilder sqlBuilder = new StringBuilder("DELETE FROM ").append(tableName).append("\n")
				.append(predicates).append(";");
		return sqlBuilder.toString();

	}
}
