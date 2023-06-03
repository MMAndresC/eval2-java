package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.ScreeningsDao;
import com.svalero.cinema.dao.TicketsDao;
import com.svalero.cinema.domain.Tickets;
import com.svalero.cinema.utils.DateUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/buy-ticket")
public class TicketAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            int idMovie = Integer.parseInt(request.getParameter("idMovie"));
            int idHall = Integer.parseInt(request.getParameter("idHall"));
            String hour = request.getParameter("hour");
            float price = Float.parseFloat(request.getParameter("price"));
            String dateString = request.getParameter("date");
            LocalDate date = LocalDate.parse(dateString);
            Date sqlDate = DateUtils.getDate(date);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Database.connectDb();
            Database.jdbi.withExtension(TicketsDao.class, dao -> {
                dao.addTicket(email, idMovie, idHall, sqlDate, hour, price);
                return null;
            });
            response.sendRedirect("show-tickets?btn=1");
        }catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
}
