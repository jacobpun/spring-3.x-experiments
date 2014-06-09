package org.punnoose.spdemo.procedure;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

import org.springframework.data.jdbc.support.oracle.SqlArrayValue;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class DeleteActorsByNameProc extends StoredProcedure {
	private static final String SP_INPUT_PARAMETER_TYPE = "ACTOR_NAME_ARRAY";
	private static final String SP_INPUT_PARAMETER_NAME  = "in_actor_names";
	private static final String SP_NAME = "delete_actors_by_name";

	public DeleteActorsByNameProc(DataSource dataSource) {
		super(dataSource, SP_NAME);
		declareParameter(new SqlParameter(SP_INPUT_PARAMETER_NAME , OracleTypes.ARRAY,
				SP_INPUT_PARAMETER_TYPE));
	}

	public void execute(String[] ids) {
		Map in = Collections.singletonMap(SP_INPUT_PARAMETER_NAME ,
				new SqlArrayValue(ids));
		Map out = this.execute(in);
	}
}