package org.punnoose.spdemo.main;

import org.punnoose.spdemo.domain.Actor;
import org.punnoose.spdemo.procedure.AddSqlActorProcedure;
import org.punnoose.spdemo.procedure.SqlActor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * main program to test adding one actor to the DB
 * @author jacobp
 *
 */
public class SPDemoMainAddActor {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-cotext.xml");
		AddSqlActorProcedure proc = (AddSqlActorProcedure) context.getBean("addActorSp");
		
		Actor actor = getFakeActor();
		
		proc.execute(actor);
		
		System.out.println("Added Actor to the DB: " + actor);
	}

	private static Actor getFakeActor() {
		Actor actor = new SqlActor();
		actor.setAge(10);
		actor.setId((long)1);
		actor.setName("Actor-1");
		return actor;
	}
}