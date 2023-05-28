package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.ScreeningsDao;
import com.svalero.cinema.domain.Screenings;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet("/show-screenings")
public class ScreeningsShowServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Database.connectDb();
            int movieId = parseInt(request.getParameter("movies_id"));
            List<Screenings> screeningsList = Database.jdbi.withExtension(ScreeningsDao.class, dao -> {
                return dao.getScreenings(movieId);
            });
            request.setAttribute("screenings", screeningsList);
            request.getRequestDispatcher("/adminzone.jsp").forward(request, response);
        }catch(Exception e){
            throw new ServletException("Error retrieving premiere movies",e);
        }

    }
}