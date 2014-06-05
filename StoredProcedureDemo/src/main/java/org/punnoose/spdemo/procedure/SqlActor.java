package org.punnoose.spdemo.procedure;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import org.punnoose.spdemo.domain.Actor;


/**
 * Wraps Actor object in an SQLData
 * @author jacobp
 *
 */
public class SqlActor extends Actor implements SQLData {

	private static final String ORACLE_OBJECT_TYPE_NAME = "ACTOR_TYPE";

	public String getSQLTypeName() throws SQLException {
		return ORACLE_OBJECT_TYPE_NAME;
	}

	public void readSQL(SQLInput sqlInput, String typeName) throws SQLException {
		setId(Long.valueOf(sqlInput.readLong()));
        setName(sqlInput.readString());
        setAge(sqlInput.readInt());
	}

	public void writeSQL(SQLOutput sqlOutput) throws SQLException {
		sqlOutput.writeLong(getId().longValue());
        sqlOutput.writeString(getName());
        sqlOutput.writeInt(getAge());
	}
}