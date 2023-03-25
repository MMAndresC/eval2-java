package com.svalero.cinema.dao;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import static com.svalero.cinema.utils.Constants.*;

public class Database {
    public void connectDb(){
        Jdbi jdbi = Jdbi.create(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Handle db = jdbi.open();
    }
}
