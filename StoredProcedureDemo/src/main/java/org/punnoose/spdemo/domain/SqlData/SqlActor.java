package org.punnoose.spdemo.domain.SqlData;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Arrays;

import javax.sql.DataSource;

import org.punnoose.spdemo.domain.Actor;
import org.punnoose.spdemo.domain.Movie;

/**
 * Wraps Actor in SQLData
 * 
 * @author jacobp
 *
 */
public class SqlActor extends Actor implements SQLData {

	private static final String DB_OBJECT_TYPE_NAME_MOVIE_ARRAY = "MOVIE_ARRAY_TYPE";
	private static final String DB_OBJECT_TYPE_NAME_ACTOR = "ACTOR_TYPE";

	private DataSource dataSource;

	public SqlActor(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getSQLTypeName() throws SQLException {
		return DB_OBJECT_TYPE_NAME_ACTOR;
	}

	public void readSQL(SQLInput sqlInput, String typeName) throws SQLException {
		setId(Long.valueOf(sqlInput.readLong()));
		setName(sqlInput.readString());
		setAge(sqlInput.readInt());
		setMovies(Arrays.asList((Movie[]) sqlInput.readArray().getArray()));
	}

	public void writeSQL(SQLOutput sqlOutput) throws SQLException {

		sqlOutput.writeLong(getId().longValue());
		sqlOutput.writeString(getName());
		sqlOutput.writeInt(getAge());
		sqlOutput.writeArray(((oracle.jdbc.OracleConnection) dataSource
				.getConnection()).createARRAY(DB_OBJECT_TYPE_NAME_MOVIE_ARRAY,
				getMovies().toArray()));

	}
}