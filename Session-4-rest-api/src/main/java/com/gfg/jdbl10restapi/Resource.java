package com.gfg.jdbl10restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController//To tell spring below apis are restful api
public class Resource {
    @Autowired
    private List<Movie> movieList;

    ObjectMapper objectMapper =  new ObjectMapper();

    @PostMapping("/v1/movie") //to tell spring we need a post api
    void create(@RequestBody  Movie movie){
        this.movieList.add(movie);
    }

    @GetMapping("/v1/movie") //to tell spring we need a get api
    HttpEntity get(@RequestParam("name") String name){
        for(Movie movie : movieList){
            if(movie.getName().equals(name)){
                return new HttpEntity<>(movie);
            }
        }
        return new HttpEntity("No movie found");
    }

    @PatchMapping("/v1/movie/{name}") //to tell spring we need a patch api
    HttpEntity patch(@PathVariable("name") String name,
            @RequestBody  Movie movieUpdateRequest){
        for(Movie movie : movieList){
            if(movie.getName().equals(name)){
                movie.setRating(movieUpdateRequest.getRating());
                return new HttpEntity("rating got updated");
            }
        }
        return new HttpEntity("No movie found");
    }

    @PutMapping("/v1/movie/{name}") //to tell spring we need a put api
    HttpEntity put(@PathVariable("name") String name,
                 @RequestBody  Movie movieUpdateRequest){
        for(Movie movie : movieList){
            if(movie.getName().equals(name)){
               movieList.remove(movie);
               movieList.add(movieUpdateRequest);
                return new HttpEntity("movie got updated ");
            }
        }
        return new HttpEntity("No movie found");
    }

    @DeleteMapping("/v1/movie") //to tell spring we need a get api
    HttpEntity delete(@RequestParam("name") String name){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForEntity("http://localhost:8080/v1/movie?name="+name, String.class).getBody();
        if(response.equals("No movie found")){
            return new HttpEntity("No movie found");
        }
        try {
            Movie movieResponse = objectMapper.readValue(response, Movie.class);
            Movie movieToRemove=null;
            for(Movie movie : movieList){
                if(movie.getName().equals(movieResponse.getName())){
                   movieToRemove = movie;
                }
            }
            movieList.remove(movieToRemove);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new HttpEntity("Movie got deleted");
    }
}
