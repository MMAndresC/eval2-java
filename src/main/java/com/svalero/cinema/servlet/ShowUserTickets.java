package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.MoviesDao;
import com.svalero.cinema.dao.TicketsDao;
import com.svalero.cinema.domain.Movies;
import com.svalero.cinema.domain.Tickets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@WebServlet("/show-tickets")
public class ShowUserTickets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if(session == null){
                response.sendRedirect("/cinema");
            }else{
                String email = ((String) session.getAttribute("user")).trim();
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Database.connectDb();
                List<Tickets> tickets = Database.jdbi.withExtension(TicketsDao.class, dao -> dao.getTickets(email));
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                tickets.forEach((ticket) -> {
                    Movies movie = Database.jdbi.withExtension(MoviesDao.class, dao -> dao.getMovie(ticket.getIdMovie()));
                    ticket.setTitle(movie.getTitle());
                    String strDate = dateFormat.format(ticket.getDate());
                    ticket.setFormattedDate(strDate);
                });
                request.setAttribute("tickets", tickets);
                //Para que el input date del search muestre el dia por defecto
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date today = Calendar.getInstance().getTime();
                String dateToString = df.format(today);
                request.setAttribute("date", dateToString);
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
                request.getRequestDispatcher("/usertickets.jsp").forward(request, response);
            }
        }catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
}
