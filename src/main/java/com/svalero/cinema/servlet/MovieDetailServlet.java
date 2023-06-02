package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.MoviesDao;
import com.svalero.cinema.dao.ScreeningsDao;
import com.svalero.cinema.domain.FrontScreenings;
import com.svalero.cinema.domain.Movies;
import com.svalero.cinema.domain.Screenings;
import com.svalero.cinema.utils.DateUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/movie-detail")
public class MovieDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Database.connectDb();
            Movies movie = Database.jdbi.withExtension(MoviesDao.class, dao -> dao.getMovie(id));
            List<Screenings> screenings = Database.jdbi.withExtension(ScreeningsDao.class, dao -> dao.getScreeningsAsc(id));
            request.setAttribute("movie", movie);
            request.setAttribute("screenings", screenings);
            request.getRequestDispatcher("/moviedetail.jsp").forward(request, response);
        }catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

    }
}
