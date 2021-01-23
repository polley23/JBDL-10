package com.gfg.jbdl10urlshortener.entities;

import lombok.*;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LongURL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @Column(nullable = false)
    private String url;
    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @OneToOne(cascade = CascadeType.ALL)
    private ShortURL shortURL;
}
