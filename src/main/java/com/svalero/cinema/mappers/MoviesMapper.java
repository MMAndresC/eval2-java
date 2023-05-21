package com.svalero.cinema.mappers;

import com.svalero.cinema.domain.Movies;
import com.svalero.cinema.utils.DateUtils;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MoviesMapper implements RowMapper<Movies>{

    @Override
    public Movies map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Movies(
                rs.getInt("movies_id"),
                rs.getString("title"),
                rs.getString("poster"),
                rs.getString("synopsis"),
                rs.getString("rated"),
                rs.getInt("duration"),
                rs.getString("genre"),
                rs.getString("director"),
                rs.getString("actors"),
                DateUtils.getLocalDate(rs.getDate("initDate")),
                rs.getString("isPremiere").charAt(0) == 'y' ? true : false
        );
    }
}
