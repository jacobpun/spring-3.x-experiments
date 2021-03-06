package org.punnoose.spdemo.procedure;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

import org.springframework.data.jdbc.support.oracle.SqlArrayValue;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class DeleteActorsProc extends StoredProcedure {
	private static final String SP_INPUT_PARAMETER_TYPE = "ACTOR_ID_ARRAY";
	private static final String SP_INPUT_PARAMETER_NAME  = "in_actor_ids";
	private static final String SP_NAME = "delete_actors";

	public DeleteActorsProc(DataSource dataSource) {
		super(dataSource, SP_NAME);
		declareParameter(new SqlParameter(SP_INPUT_PARAMETER_NAME , OracleTypes.ARRAY,
				SP_INPUT_PARAMETER_TYPE));
	}

	public void execute(Long[] ids) {
		Map in = Collections.singletonMap(SP_INPUT_PARAMETER_NAME ,
				new SqlArrayValue(ids));
		Map out = this.execute(in);
	}
}