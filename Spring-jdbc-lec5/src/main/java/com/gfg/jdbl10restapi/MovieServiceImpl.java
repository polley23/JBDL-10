package com.gfg.jdbl10restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void create(Movie movie) {
        movieRepository.create(movie);
    }

    @Override
    public List<Movie> get(String name) {
        List<Movie> movies = movieRepository.get(name);
        List<Cast> casts = new ArrayList<>();
        movies.forEach(movie -> {
                    casts.addAll(movie.getCastList()); });
        movies.get(0).setCastList(casts);
        return Arrays.asList(movies.get(0));
    }

    @Override
    public void update(Movie movie) {
        movieRepository.update(movie);
    }

    @Override
    public void delete(String name) {
        movieRepository.delete(name);
    }
}
