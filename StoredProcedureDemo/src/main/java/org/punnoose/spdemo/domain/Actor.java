package org.punnoose.spdemo.domain;

import java.util.ArrayList;
import java.util.List;

public class Actor {

    private Long id;
    private String name;
    private int age;
    private List<Movie> movies = new ArrayList<Movie>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
    return "Actor: [" + id + "] " + name + " " + age;
    }

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	public void addMovie(Movie movie){
		this.movies.add(movie);
	}
}