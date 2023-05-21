package com.svalero.cinema.dao;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import static com.svalero.cinema.utils.Constants.*;

public class Database {

    public static Jdbi jdbi;
    public static Handle db;
    public static void connectDb(){
        try {
            jdbi = Jdbi.create(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            jdbi.installPlugin(new SqlObjectPlugin());
            db = jdbi.open();
            System.out.println("Database open");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public static void closeDb(){
        db.close();
        System.out.println("Database close");
    }
}
