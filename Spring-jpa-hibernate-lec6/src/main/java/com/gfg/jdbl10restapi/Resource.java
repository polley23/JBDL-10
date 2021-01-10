package com.gfg.jdbl10restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController//To tell spring below apis are restful api
public class Resource {
    @Autowired
    private MovieService movieService;

    ObjectMapper objectMapper =  new ObjectMapper();

    @PostMapping("/v1/movie") //to tell spring we need a post api
    void create(@RequestBody  MovieVO movie){
       // this.movieList.add(movie);
        movieService.create(movie);
    }

    @SneakyThrows
    @GetMapping("/v1/movie") //to tell spring we need a get api
    ResponseEntity get(@RequestParam("name") String name){
        return ResponseEntity.ok(movieService.get(name));
    }

    @SneakyThrows
    @PatchMapping("/v1/movie/{name}") //to tell spring we need a patch api
    void patch(@PathVariable("name") String name,
            @RequestBody  MovieVO movieUpdateRequest){
        movieService.update(movieUpdateRequest,name);
    }


    @SneakyThrows
    @DeleteMapping("/v1/movie") //to tell spring we need a get api
    void delete(@RequestParam("name") String name){
        movieService.delete(name);
    }
}
