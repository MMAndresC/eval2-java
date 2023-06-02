package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.ScreeningsDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;


@WebServlet("/screening-delete")
public class ScreeningDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Database.connectDb();
            int deleted = Database.jdbi.withExtension(ScreeningsDao.class, dao -> dao.deleteScreening(id));
            HttpSession session = request.getSession(false);
            session.setAttribute("indexMovie", 0);
            response.sendRedirect("adminzone.jsp");
        }catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
}
