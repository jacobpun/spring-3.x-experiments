package org.punnoose.spdemo.main;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.punnoose.spdemo.domain.Actor;
import org.punnoose.spdemo.domain.Movie;
import org.punnoose.spdemo.domain.SqlData.SqlActor;
import org.punnoose.spdemo.domain.SqlData.SqlMovie;
import org.punnoose.spdemo.procedure.AddSqlActorProcedure;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * main program to test adding one actor to the DB
 * 
 * @author jacobp
 *
 */
public class AddActorDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-cotext.xml");
		AddSqlActorProcedure proc = (AddSqlActorProcedure) context
				.getBean("addActorSp");
		DataSource dataSource = (DataSource) context.getBean("dataSource");

		Actor actor = getFakeActor(dataSource);

		proc.execute(actor);

		System.out.println("Added Actor to the DB: " + actor);
	}

	private static Actor getFakeActor(DataSource dataSource) {
		
		final int DUMMT_AGE = 10;
		final long DUMMY_ID = (long) 1;
		final String DUMMY_NAME = "Actor-1";
		final int FAKE_MOVIES_COUNT = 2;

		Actor actor = new SqlActor(dataSource);
		actor.setAge(DUMMT_AGE);
		actor.setId(DUMMY_ID);
		actor.setName(DUMMY_NAME);
		actor.setMovies(getFakeMovies(FAKE_MOVIES_COUNT));
		
		return actor;
	}

	private static List<Movie> getFakeMovies(int count) {
		List<Movie> movies = new ArrayList<Movie>();
		for (int i = 0; i < count; i++) {
			movies.add(getFakeMovie(i));
		}
		return movies;
	}

	private static Movie getFakeMovie(int index) {
		Movie movie = new SqlMovie();
		movie.setId((long) index);
		movie.setName("Movie-" + index);
		return movie;
	}
}