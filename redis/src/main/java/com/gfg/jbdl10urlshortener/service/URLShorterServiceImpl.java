package com.gfg.jbdl10urlshortener.service;

import com.gfg.jbdl10urlshortener.Repository.LongUrlRepository;
import com.gfg.jbdl10urlshortener.Repository.RedisRepository;
import com.gfg.jbdl10urlshortener.Repository.ShortUrlRepository;
import com.gfg.jbdl10urlshortener.entities.LongURL;
import com.gfg.jbdl10urlshortener.entities.ShortURL;
import com.gfg.jbdl10urlshortener.entities.UrlRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Service
public class URLShorterServiceImpl implements URLShortenerService {
    @Autowired
    private ShortUrlRepository shortUrlRepository;
    @Autowired
    private LongUrlRepository longUrlRepository;
    @Autowired
    private RedisRepository redisRepository;
    @Override

    public String shorten(String longUrlString) {
        ShortURL shortURL=ShortURL.builder()
                .domain("localhost:8080")
                .protocol("http")
                .expired(false)
                .createdAt(LocalDateTime.now())
                .build();
        LongURL longURL = LongURL.builder()
                .url(longUrlString)
                .createdAt(LocalDateTime.now())
                .shortURL(shortURL).build();
        shortURL.setLongURL(longURL);
        longURL = longUrlRepository.save(longURL);
        //build tiny url
        String key = getShortenedPath(longURL.getShortURL().getId());
        URI uri = URI.create(shortURL.getProtocol()+"://"+shortURL.getDomain()+"/"+key);
        redisRepository.save(new UrlRedis(key,longUrlString));
        return uri.toString();
    }

    private String getShortenedPath(Long Id) {
        String path = Base64.getEncoder().encodeToString(String.valueOf(Id).getBytes());
        return path;
    }

    @Override
    public String get(String shortUrlRequest) throws Exception {
        UrlRedis urlRedis = redisRepository.findById(shortUrlRequest)
               .orElseThrow(()->new Exception("shortURL doesn't exist or expired")) ;
//        Long id = 0l;
//        try {
//             id = decodePath(shortUrlRequest);
//        }catch (Exception e){
//            throw new Exception("invalid shortUrl");
//        }
//        ShortURL shortURL = shortUrlRepository.findByIdAndExpired(id,false)
//                .orElseThrow(()->  new Exception("shortURL doesn't exist or expired"));
        return urlRedis.getLongurl();
    }

    @Override
    public void expire() {
        Iterable<ShortURL> shortURLList = shortUrlRepository.findAll();
        shortURLList.forEach(
                shortURL -> {
                    if(!shortURL.getExpired()){
                        if(shortURL.getCreatedAt().isBefore(LocalDateTime.now().minusSeconds(10))){
                            shortURL.setExpired(true);
                            String key = getShortenedPath(shortURL.getId());
                            redisRepository.deleteById(key);
                            shortUrlRepository.save(shortURL);
                        }
                    }
                });
    }

    private Long decodePath(String shortUrl) {
        byte[] bytes = Base64.getDecoder().decode(shortUrl);
        String str = new String(bytes, StandardCharsets.UTF_8);
        return Long.parseLong(str);
    }
}
