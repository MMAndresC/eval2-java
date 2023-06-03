package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.CinemaHallsDao;
import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.MoviesDao;
import com.svalero.cinema.dao.ScreeningsDao;
import com.svalero.cinema.domain.CinemaHalls;
import com.svalero.cinema.domain.Movies;
import com.svalero.cinema.domain.Screenings;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@WebServlet("/preticket-info")
public class preTicketInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int screeningId = Integer.parseInt(request.getParameter("id"));
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Database.connectDb();
            Screenings screening = Database.jdbi.withExtension(ScreeningsDao.class, dao -> {
                return dao.getSelectedScreening(screeningId);
            });
            CinemaHalls hall = Database.jdbi.withExtension(CinemaHallsDao.class, dao -> {
                return dao.getScreeningHall(screening.getId_hall());
            });
            Movies movie = Database.jdbi.withExtension(MoviesDao.class, dao -> {
                return dao.getMovie(screening.getId_movie());
            });
            screening.setTitle(movie.getTitle());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            String strDate = dateFormat.format(screening.getDateScreening());
            screening.setFormattedDate(strDate);
            request.setAttribute("lines", hall.getLines());
            request.setAttribute("cols", hall.getCols());
            request.setAttribute("screening", screening);
            request.getRequestDispatcher("/preticket.jsp").forward(request, response);

        }catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
}
