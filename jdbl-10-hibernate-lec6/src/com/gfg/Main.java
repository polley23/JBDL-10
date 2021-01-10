package com.gfg;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MovieRepository movieRepository = new MovieRepositoryImpl();
        Movie movie = new Movie();
        movie.setId(1l);
        movie.setName("avenger");
        movie.setGenre("Action");
        movie.setLanguage("Eng");
        movie.setRating(4.0f);
        movieRepository.create(movie);
    }
}
