package org.punnoose.spdemo.procedure;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

import org.punnoose.spdemo.domain.Actor;
import org.springframework.data.jdbc.support.oracle.SqlArrayValue;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/** SP to add a List of Actors to the DB **/
public class AddActorArrayProcedure extends StoredProcedure {

	private static final String SP_NAME = "add_actor_array";
	private static final String SP_INPUT_PARAMETER_TYPE = "ACTOR_ARRAY_TYPE";
	private static final String SP_INPUT_PARAMETER_NAME = "in_actor_array";

	public AddActorArrayProcedure(DataSource dataSource) {
		super(dataSource, SP_NAME);
		declareParameter(new SqlParameter(SP_INPUT_PARAMETER_NAME,
				OracleTypes.ARRAY, SP_INPUT_PARAMETER_TYPE));
	}

	public void execute(List<Actor> actors) {
		Map<String, SqlArrayValue<Actor>> in = Collections.singletonMap(
				SP_INPUT_PARAMETER_NAME,
				new SqlArrayValue<Actor>(
						actors.toArray(new Actor[actors.size()])));
		this.execute(in);
	}
}