package com.gfg.jdbl10restapi;

import java.util.List;

public interface MovieRepository {
    void create(Movie movie);
    List<Movie> get(String name);
    void update(Movie movie);
    void delete(String name);
}
