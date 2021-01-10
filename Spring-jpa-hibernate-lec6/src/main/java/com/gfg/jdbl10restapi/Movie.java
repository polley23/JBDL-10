package com.gfg.jdbl10restapi;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "movie")
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String genre;
    private String language;
    private Float rating;
    @ManyToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Cast> castList;
}
