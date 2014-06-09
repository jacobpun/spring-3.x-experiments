package org.punnoose.spdemo.main;

import java.util.ArrayList;
import java.util.List;

import org.punnoose.spdemo.domain.Actor;
import org.punnoose.spdemo.domain.SqlData.SqlActor;
import org.punnoose.spdemo.procedure.AddSqlActorArrayProcedure;
import org.punnoose.spdemo.procedure.AddSqlActorProcedure;
import org.punnoose.spdemo.procedure.DeleteActorsByNameProc;
import org.punnoose.spdemo.procedure.DeleteActorsProc;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * main program to test deleting list of actors to the DB
 * @author jacobp
 *
 */
public class DeleteActorsByNameDemo {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-cotext.xml");
		DeleteActorsByNameProc proc = (DeleteActorsByNameProc) context
				.getBean("deleteActorsByNameSp");

		final String NAME_TO_DELETE_1 = "Actor-0";
		final String NAME_TO_DELETE_2 = "Actor-3";
		String[] namesForDeletion = new String[]{NAME_TO_DELETE_1,NAME_TO_DELETE_2};
		
		proc.execute(namesForDeletion);
	}
}