package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.MoviesDao;
import com.svalero.cinema.dao.TicketsDao;
import com.svalero.cinema.domain.Movies;
import com.svalero.cinema.domain.Tickets;
import com.svalero.cinema.utils.DateUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;


@WebServlet("/tickets-search")
public class TicketsSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO solomuestra un registro y con la paginacion no va bien
        try{
            HttpSession session = request.getSession(false);
            if(session == null){
                response.sendRedirect("/cinema");
            }else{
                String email = ((String) session.getAttribute("user")).trim();
                String date = request.getParameter("date");
                String criteria = request.getParameter("criteria");
                LocalDate localDate = LocalDate.parse(date);
                Date sqlDate = DateUtils.getDate(localDate);
                ArrayList<Tickets> tickets = new ArrayList<>();
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Database.connectDb();
                switch (criteria) {
                    case "exact" -> tickets = Database.jdbi.withExtension(TicketsDao.class, dao -> dao.getTicketsInDate(email, sqlDate));
                    case "before" -> tickets = Database.jdbi.withExtension(TicketsDao.class, dao -> dao.getTicketsBeforeDate(email, sqlDate));
                    case "after" -> tickets = Database.jdbi.withExtension(TicketsDao.class, dao -> dao.getTicketsAfterDate(email, sqlDate));
                }
                if(tickets.size() > 0){
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                    tickets.forEach((ticket) -> {
                        Movies movie = Database.jdbi.withExtension(MoviesDao.class, dao -> dao.getMovie(ticket.getIdMovie()));
                        ticket.setTitle(movie.getTitle());
                        String strDate = dateFormat.format(ticket.getDate());
                        ticket.setFormattedDate(strDate);
                    });
                    request.setAttribute("tickets", tickets);
                    request.setAttribute("date", date);
                    //El numero de botones que tiene que dibujar para la paginacion
                    int numPages = tickets.size() / 5;
                    request.setAttribute("end", numPages);
                    int btn = Integer.parseInt(request.getParameter("btn"));
                    int offset = (btn - 1) * 5;
                    request.setAttribute("offset", offset);
                    if((btn - 1) * 5 <= (tickets.size() - 1) ){
                        request.setAttribute("limit", offset + 4);
                    }else{
                        request.setAttribute("limit", tickets.size() - 1);
                    }
                }else{
                    request.setAttribute("limit", 0);
                    request.setAttribute("offset", 0);
                    request.setAttribute("end", 0);
                }
                request.getRequestDispatcher("/usertickets.jsp").forward(request, response);
            }
        }catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

}
