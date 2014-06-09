package org.punnoose.spdemo.main;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.punnoose.spdemo.domain.Actor;
import org.punnoose.spdemo.domain.SqlData.SqlActor;
import org.punnoose.spdemo.procedure.AddSqlActorArrayProcedure;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * main program to test adding list of actors to the DB
 * @author jacobp
 *
 */
public class AddActorArrayDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-cotext.xml");
		AddSqlActorArrayProcedure proc = (AddSqlActorArrayProcedure) context
				.getBean("addActorArraySp");
		DataSource dataSource = (DataSource) context.getBean("dataSource");

		final int FAKE_ACTORS_COUNT = 10;
		List<Actor> actorsToAdd = getFakeActors(FAKE_ACTORS_COUNT, dataSource);
		
		proc.execute(actorsToAdd);
	}

	private static List<Actor> getFakeActors(int count, DataSource dataSource) {
		List<Actor> actors = new ArrayList<Actor>();
		for (int i = 0; i < count; i++) {
			actors.add(createFakeActor(dataSource, i));
		}
		return actors;
	}

	private static Actor createFakeActor(DataSource dataSource, int index) {
		Actor actor = new SqlActor(dataSource);
		actor.setAge(10 + index);
		actor.setId((long) index);
		actor.setName("Actor-" + index);
		return actor;
	}
}