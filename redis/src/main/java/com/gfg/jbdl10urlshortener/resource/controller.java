package com.gfg.jbdl10urlshortener.resource;

import com.gfg.jbdl10urlshortener.models.URLShortenRequest;
import com.gfg.jbdl10urlshortener.service.URLShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class controller {
    @Autowired
    private URLShortenerService urlShortenerService;

    @PostMapping("/longurl/")
    ResponseEntity shortenUrl(@RequestBody URLShortenRequest urlShortenRequest){
        return ResponseEntity.ok(urlShortenerService.shorten(urlShortenRequest.getUrl()));
    }

    @GetMapping("{shorturl}")
    ResponseEntity getLongURL(@PathVariable("shorturl") String shorturl){
        try {
            return ResponseEntity.ok(urlShortenerService.get(shorturl));
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PutMapping("/shorturl/expiration")
    ResponseEntity expire(){
        urlShortenerService.expire();
        return ResponseEntity.ok().build();
    }

}
