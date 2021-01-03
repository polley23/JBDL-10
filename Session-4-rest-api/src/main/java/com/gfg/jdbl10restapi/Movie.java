package com.gfg.jdbl10restapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Movie {
    private String name;
    private String genre;
    private String language;
    private Float rating;
    private List<Cast> castList;
}
