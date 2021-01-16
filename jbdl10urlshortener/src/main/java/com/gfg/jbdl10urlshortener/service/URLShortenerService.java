package com.gfg.jbdl10urlshortener.service;

public interface URLShortenerService {
    String shorten(String longUrl);
    String get(String shortUrl) throws Exception;
    void expire();
}
