package com.svalero.cinema.mappers;

import com.svalero.cinema.domain.Screenings;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ScreeningsMapper implements RowMapper<Screenings>{

    @Override
    public Screenings map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Screenings(
                rs.getInt("screening_id"),
                rs.getInt("id_movie"),
                rs.getInt("id_hall"),
                rs.getDate("dateScreening"),
                rs.getString("hourScreening")
        );
    }
}
