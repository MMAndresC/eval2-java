package com.svalero.cinema.dao;

import com.svalero.cinema.domain.Users;
import com.svalero.cinema.mappers.UsersMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;


public interface UsersDao {
    @SqlQuery("SELECT * FROM Users WHERE email= ?")
    @UseRowMapper(UsersMapper.class)
    Users getUserForEmail(String email);

    @SqlQuery("SELECT * FROM Users WHERE email = ? AND password = ?")
    @UseRowMapper(UsersMapper.class)
    Users getRegisteredUser(String enail, String password);

    @SqlUpdate("INSERT INTO Users (email, password, name, phone, image, role) VALUES (?, ?, ?, ?, ?, ?)")
    void addUser(String email, String password, String name, String phone, String image, String role);
}
