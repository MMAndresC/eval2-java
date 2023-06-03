package com.svalero.cinema.mappers;

import com.svalero.cinema.domain.Tickets;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketsMapper implements RowMapper<Tickets> {

    @Override
    public Tickets map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Tickets(
                rs.getString("email"),
                rs.getInt("id_movie"),
                rs.getInt("id_hall"),
                rs.getDate("date_ticket"),
                rs.getString("hour"),
                rs.getFloat("price")
        );
    }
}
