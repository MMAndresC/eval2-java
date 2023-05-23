package com.svalero.cinema.mappers;

import com.svalero.cinema.domain.Users;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersMapper implements RowMapper<Users> {
    @Override
    public Users map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Users(
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("phone"),
                rs.getString("image"),
                rs.getString("role")
        );
    }
}
