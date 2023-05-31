package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.ScreeningsDao;
import com.svalero.cinema.utils.DateUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;


@WebServlet("/screening-edit")
public class ScreeningEditServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            int hall = Integer.parseInt(request.getParameter("hall"));
            String dateString = request.getParameter("date");
            LocalDate date = LocalDate.parse(dateString);
            Date sqlDate = DateUtils.getDate(date);
            String hour = request.getParameter("hour");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Database.connectDb();
            int edited = Database.jdbi.withExtension(ScreeningsDao.class, dao -> dao.editScreening(hall, sqlDate, hour, id));
            HttpSession session = request.getSession(false);
            session.setAttribute("indexMovie", 0);
            if(edited > 0){
                out.println("Saved");
            }else{
                out.println(
                        "<div class='alert alert-danger' role='alert'> " +
                                "<p>No se han podido guardar los nuevos datos de la sesion</p>" +
                        "</div>"
                );
            }
        }catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
}
