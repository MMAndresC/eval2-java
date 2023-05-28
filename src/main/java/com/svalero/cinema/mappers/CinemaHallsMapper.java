package com.svalero.cinema.mappers;

import com.svalero.cinema.domain.CinemaHalls;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CinemaHallsMapper implements RowMapper<CinemaHalls>{

    @Override
    public CinemaHalls map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new CinemaHalls(
                rs.getInt("id"),
                rs.getInt("cols"),
                rs.getInt("lines")
        );
    }
}
