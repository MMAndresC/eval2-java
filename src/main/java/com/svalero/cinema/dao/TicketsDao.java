package com.svalero.cinema.dao;



import com.svalero.cinema.domain.Tickets;
import com.svalero.cinema.mappers.TicketsMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface TicketsDao {

    @SqlQuery("SELECT * FROM Tickets WHERE email= ? ORDER BY date_ticket DESC, hour DESC")
    @UseRowMapper(TicketsMapper.class)
    List<Tickets> getTickets(String email);

    @SqlQuery("SELECT * FROM Tickets WHERE email= ? AND date_ticket = TO_DATE( ?, 'DD/MM/YY') ORDER BY hour DESC")
    @UseRowMapper(TicketsMapper.class)
    ArrayList<Tickets> getTicketsInDate(String email, Date date);

    @SqlQuery("SELECT * FROM Tickets WHERE email= ? AND date_ticket < TO_DATE( ?, 'DD/MM/YY') ORDER BY hour DESC")
    @UseRowMapper(TicketsMapper.class)
    ArrayList<Tickets> getTicketsBeforeDate(String email, Date date);

    @SqlQuery("SELECT * FROM Tickets WHERE email= ? AND date_ticket > TO_DATE( ?, 'DD/MM/YY') ORDER BY hour DESC")
    @UseRowMapper(TicketsMapper.class)
    ArrayList<Tickets> getTicketsAfterDate(String email, Date date);

}
