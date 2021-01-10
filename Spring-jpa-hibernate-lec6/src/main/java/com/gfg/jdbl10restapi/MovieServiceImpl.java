package com.gfg.jdbl10restapi;

import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void create(MovieVO movieVO) {
        List<Cast> casts = new ArrayList<>();

        Movie movie = Movie.builder()
                .name(movieVO.getName())
                .genre(movieVO.getGenre())
                .language(movieVO.getLanguage())
                .rating(movieVO.getRating())
                .castList(casts)
                .build();
        movieVO.getCastList()
                .forEach(castVO -> {
                    Cast cast = Cast.builder()
                            .name(castVO.getName())
                            .movie(Arrays.asList(movie))
                            .build();
                    casts.add(cast);
                });
        movieRepository.save(movie);
    }

    @Override
    public MovieVO get(String name) throws NotFoundException {
        Movie movie = movieRepository.findByName(name)
                .orElseThrow(()-> new NotFoundException("Movie with "+name+ " is not found!"));
        List<CastVO> castVOS = new ArrayList<>();
        movie.getCastList()
                .forEach(cast -> {
                    CastVO castVO = CastVO.builder()
                            .name(cast.getName())
                            .build();
                    castVOS.add(castVO);
                });
        return MovieVO.builder()
                .name(movie.getName())
                .genre(movie.getGenre())
                .language(movie.getLanguage())
                .rating(movie.getRating())
                .castList(castVOS)
                .build();

    }

    @Override
    public void update( MovieVO movieVO, String name) throws NotFoundException {
        Movie movie = movieRepository.findByName(name)
                .orElseThrow(()-> new NotFoundException("Movie with "+name+ " is not found!"));
        if(movieVO.getRating() != null){
            movie.setRating(movieVO.getRating());
        }
        if(movieVO.getGenre() != null){
            movie.setGenre(movieVO.getGenre());
        }
        if(movieVO.getLanguage() != null){
            movie.setLanguage(movieVO.getLanguage());
        }movieRepository.save(movie);
    }

    @Override
    public void delete(String name) throws NotFoundException {
        Movie movie = movieRepository.findByName(name)
                .orElseThrow(()-> new NotFoundException("Movie with "+name+ " is not found!"));
        movieRepository.delete(movie);
    }
}
