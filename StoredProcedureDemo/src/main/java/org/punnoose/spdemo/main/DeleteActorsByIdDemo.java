package org.punnoose.spdemo.main;

import org.punnoose.spdemo.procedure.DeleteActorsProc;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * main program to test deleting list of actors to the DB
 * @author jacobp
 *
 */
public class DeleteActorsByIdDemo {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-cotext.xml");
		DeleteActorsProc proc = (DeleteActorsProc) context
				.getBean("deleteActorsSp");

		final long ID_TO_DELETE_1 = 1L;
		final long ID_TO_DELETE_2 = 2L;
		Long[] idsForDeletion = new Long[]{ID_TO_DELETE_1,ID_TO_DELETE_2};
		
		proc.execute(idsForDeletion);
		
	}
}