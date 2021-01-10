package com.gfg.jdbl10restapi;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cast")
@Builder
public class Cast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
      @JoinTable(
              name = "movie_cast",
              joinColumns = {
                      @JoinColumn(name = "cast_id")
              },
              inverseJoinColumns = {
                      @JoinColumn(name = "movie_id")
              }
      )
    //@ManyToOne
    //    @JoinColumn(name = "movie_id", nullable = false)
    //    Movie movie;
    private List<Movie> movie;
}
