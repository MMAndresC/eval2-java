package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.ScreeningsDao;

import com.svalero.cinema.utils.DateUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;


@WebServlet("/screening-add")
public class ScreeningAddServlet extends HomeServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            int hall = Integer.parseInt(request.getParameter("hall-add"));
            int movie = Integer.parseInt(request.getParameter("movie-add"));
            String dateString = request.getParameter("date-add");
            LocalDate date = LocalDate.parse(dateString);
            Date sqlDate = DateUtils.getDate(date);
            String hour = request.getParameter("hour-add");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Database.connectDb();
            Database.jdbi.withExtension(ScreeningsDao.class, dao -> {
                dao.addScreening (hall, movie, sqlDate, hour);
                return null;
            });
            out.println("Saved");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
}
