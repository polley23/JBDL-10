package com.gfg.jdbl10restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController//To tell spring below apis are restful api
public class Resource {
    @Autowired
    private MovieService movieService;

    ObjectMapper objectMapper =  new ObjectMapper();

    @PostMapping("/v1/movie") //to tell spring we need a post api
    void create(@RequestBody  Movie movie){
       // this.movieList.add(movie);
        movieService.create(movie);
    }

    @GetMapping("/v1/movie") //to tell spring we need a get api
    ResponseEntity get(@RequestParam("name") String name){
        return ResponseEntity.ok(movieService.get(name));
    }

    @PatchMapping("/v1/movie/{name}") //to tell spring we need a patch api
    void patch(@PathVariable("name") String name,
            @RequestBody  Movie movieUpdateRequest){
        movieService.update(movieUpdateRequest);
    }

    @PutMapping("/v1/movie/{name}") //to tell spring we need a put api
    HttpEntity put(@PathVariable("name") String name,
                 @RequestBody  Movie movieUpdateRequest){
//        for(Movie movie : movieList){
//            if(movie.getName().equals(name)){
//               movieList.remove(movie);
//               movieList.add(movieUpdateRequest);
//                return new HttpEntity("movie got updated ");
//            }
//        }
        return new HttpEntity("No movie found");
    }

    @DeleteMapping("/v1/movie") //to tell spring we need a get api
    void delete(@RequestParam("name") String name){
        movieService.delete(name);
    }
}
