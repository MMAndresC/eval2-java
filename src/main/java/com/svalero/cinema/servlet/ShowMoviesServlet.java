package com.svalero.cinema.servlet;


import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.MoviesDao;
import com.svalero.cinema.domain.Movies;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/show-movies")
public class ShowMoviesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");

        Database.connectDb();
        Movies movie = Database.jdbi.withExtension(MoviesDao.class, dao -> dao.getMovie(5));
        Database.closeDb();
        response.getWriter().print("no se ke poner");
    }
}
