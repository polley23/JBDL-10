package com.gfg.jdbl10restapi;

import javassist.NotFoundException;

import java.util.List;

public interface MovieService {
    void create(MovieVO movie);
    MovieVO get(String name) throws NotFoundException;
    void update(MovieVO movie,String name) throws NotFoundException;
    void delete(String name) throws NotFoundException;
}
