package org.punnoose.spdemo.domain.SqlData;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import org.punnoose.spdemo.domain.Movie;


/**
 * Wraps Movie in SQLData
 * 
 * @author jacobp
 *
 */

public class SqlMovie extends Movie implements SQLData {

	private static final String ORACLE_OBJECT_TYPE_NAME = "MOVIE_TYPE";

	public String getSQLTypeName() throws SQLException {
		return ORACLE_OBJECT_TYPE_NAME;
	}

	public void readSQL(SQLInput sqlInput, String typeName) throws SQLException {
		setId(Long.valueOf(sqlInput.readLong()));
		setName(sqlInput.readString());
	}

	public void writeSQL(SQLOutput sqlOutput) throws SQLException {
		sqlOutput.writeLong(getId().longValue());
		sqlOutput.writeString(getName());
	}
}