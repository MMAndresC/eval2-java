package com.svalero.cinema.dao;

import com.svalero.cinema.domain.Movies;
import com.svalero.cinema.mappers.MoviesMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;


import java.util.List;

public interface MoviesDao {
    @SqlQuery("SELECT * FROM movies WHERE isPremiere ='y'")
    @UseRowMapper(MoviesMapper.class)
    List<Movies> getPremiereMovies();

    @SqlQuery("SELECT * FROM movies WHERE initDate < CURRENT_DATE")
    @UseRowMapper(MoviesMapper.class)
    List<Movies> getFuturePremiereMovies();

    @SqlQuery("SELECT * FROM Movies WHERE movies_id = ?")
    @UseRowMapper(MoviesMapper.class)
    Movies getMovie(int id);

}
