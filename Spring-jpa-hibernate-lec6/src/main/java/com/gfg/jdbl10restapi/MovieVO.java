package com.gfg.jdbl10restapi;

import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MovieVO {
    private String name;
    private String genre;
    private String language;
    private Float rating;
    private List<CastVO> castList;
}
