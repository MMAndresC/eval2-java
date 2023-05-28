package com.svalero.cinema.dao;


import com.svalero.cinema.domain.CinemaHalls;
import com.svalero.cinema.mappers.CinemaHallsMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.util.List;


public interface CinemaHallsDao {
    @SqlQuery("SELECT * FROM Halls")
    @UseRowMapper(CinemaHallsMapper.class)
    List<CinemaHalls> getCinemaHalls();


}
