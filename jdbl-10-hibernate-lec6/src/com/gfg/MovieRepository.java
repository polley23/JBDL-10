package com.gfg;

import java.util.List;

public interface MovieRepository {
    void create(Movie movie);
    Movie get(Long name);
    void update(Movie movie);
    void delete(String name);
}
