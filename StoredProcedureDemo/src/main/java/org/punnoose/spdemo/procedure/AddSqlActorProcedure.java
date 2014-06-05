package org.punnoose.spdemo.procedure;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

import org.punnoose.spdemo.domain.Actor;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/** SP to add an Actor to the DB **/
public class AddSqlActorProcedure extends StoredProcedure {

	private static final String SP_NAME = "add_actor";
	private static final String SP_INPUT_PARAMETER_TYPE = "ACTOR_TYPE";
	private static final String SP_INPUT_PARAMETER_NAME = "in_actor";

	public AddSqlActorProcedure(DataSource dataSource) {
		super(dataSource, SP_NAME);
		declareParameter(new SqlParameter(SP_INPUT_PARAMETER_NAME,
				OracleTypes.STRUCT, SP_INPUT_PARAMETER_TYPE));
	}

	public void execute(Actor actor) {
		Map<String, Actor> in = Collections.singletonMap(
				SP_INPUT_PARAMETER_NAME, actor);
		this.execute(in);
	}
}