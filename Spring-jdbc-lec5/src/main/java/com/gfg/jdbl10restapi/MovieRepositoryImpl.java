package com.gfg.jdbl10restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class MovieRepositoryImpl implements MovieRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Movie movie) {
        String query = "INSERT INTO MOVIE \n" +
                "(`name`,`genre`,`language`,`rating`)\n" +
                "VALUES\n" +
                "(?,?,?,?)";
        jdbcTemplate.update(query, movie.getName(),movie.getGenre(),movie.getLanguage(),movie.getRating());
        String query3 =  "SELECT * FROM MOVIE\n" +
                "WHERE `name` = ?";
        List<Integer> ids = jdbcTemplate
                .query(query3, new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, movie.getName());
                    }
                }, new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                       return resultSet.getInt(1);
                    }
                });
        movie.getCastList()
                .forEach(cast -> {
                    String query2 = "INSERT INTO CAST \n" +
                            "(`name`,`movie_id`)\n" +
                            "VALUES\n" +
                            "(?,?)";
                    jdbcTemplate.update(query2,cast.getName(),ids.get(0));
                });
    }

    @Override
    public List<Movie> get(String name) {
        String query =  "SELECT * FROM MOVIE m join CAST c ON\n" +
                " m.id = c.movie_id " +
                "WHERE m.name = ?";
        List<Movie> movieList = jdbcTemplate
                .query(query, new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, name);
                    }
                }, new RowMapper<Movie>() {
                    @Override
                    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
                       Movie movie = new Movie();
                       movie.setName(resultSet.getString(2));
                       movie.setGenre(resultSet.getString(3));
                       movie.setLanguage(resultSet.getString(4));
                       movie.setRating(resultSet.getFloat(5));
                       List<Cast> casts = new ArrayList<>();
                       Cast cast = new Cast();
                       cast.setName(resultSet.getString(7));
                       casts.add(cast);
                       movie.setCastList(casts);
                       return movie;
                    }
                });
        return movieList;
    }

    @Override
    public void update(Movie movie) {
        String query = "UPDATE MOVIE SET `language` = ? WHERE `name` = ?";
        jdbcTemplate.update(query, movie.getLanguage(),movie.getName());
    }

    @Override
    public void delete(String name) {
        String query = "DELETE FROM MOVIE where `name` = ? ";
        jdbcTemplate.update(query,name);
    }
}

// N layer Architecture
//Controller (Application layer) Layer 1 we server request to client
//Business or service layer Layer 2 we keep our business logic
//Repository Layer Layer 3 Here we interact with data source
