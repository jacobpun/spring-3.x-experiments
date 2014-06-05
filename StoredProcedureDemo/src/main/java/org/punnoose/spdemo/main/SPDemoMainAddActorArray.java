package org.punnoose.spdemo.main;

import java.util.ArrayList;
import java.util.List;

import org.punnoose.spdemo.domain.Actor;
import org.punnoose.spdemo.procedure.AddSqlActorArrayProcedure;
import org.punnoose.spdemo.procedure.AddSqlActorProcedure;
import org.punnoose.spdemo.procedure.SqlActor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * main program to test adding list of actors to the DB
 * @author jacobp
 *
 */
public class SPDemoMainAddActorArray {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-cotext.xml");
		AddSqlActorArrayProcedure proc = (AddSqlActorArrayProcedure) context
				.getBean("addActorArraySp");

		List<Actor> actors = getFakeActors(10);
		proc.execute(actors);
	}

	private static List<Actor> getFakeActors(int count) {
		List<Actor> actors = new ArrayList<Actor>();
		for (int i = 0; i < count; i++) {
			Actor actor = new SqlActor();
			actor.setAge(10 + i);
			actor.setId((long) i);
			actor.setName("Actor-" + i);
			actors.add(actor);
		}
		return actors;
	}
}