package com.gfg.jbdl10urlshortener.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShortURL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @Column(nullable = false)
    private String domain="localhost:8080";
    @Column(nullable = false)
    private String protocol="http";
    private Boolean expired=false;
    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;
    @OneToOne(cascade = CascadeType.ALL)
    private LongURL longURL;
}
