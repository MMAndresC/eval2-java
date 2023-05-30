package com.svalero.cinema.dao;

import com.svalero.cinema.domain.Screenings;
import com.svalero.cinema.mappers.ScreeningsMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.sql.Date;
import java.util.List;

public interface ScreeningsDao {
    @SqlQuery("SELECT * FROM Screenings WHERE id_movie= ? ORDER BY dateScreening DESC")
    @UseRowMapper(ScreeningsMapper.class)
    List<Screenings> getScreenings(int idMovie);

    @SqlUpdate("DELETE FROM Screenings WHERE screening_id = ?")
    int deleteScreening(int id);

    @SqlUpdate("UPDATE Screenings SET id_hall = ?, dateScreening = TO_DATE( ?, 'DD/MM/YY'), hourScreening = ? WHERE screening_id = ?")
    int editScreening(int hall, Date date, String hour, int id);


}
