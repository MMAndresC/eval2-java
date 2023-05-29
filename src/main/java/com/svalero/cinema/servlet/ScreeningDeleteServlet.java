package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.ScreeningsDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/screening-delete")
public class ScreeningDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Database.connectDb();
            int deleted = Database.jdbi.withExtension(ScreeningsDao.class, dao -> dao.deleteScreening(id));
            response.sendRedirect("adminzone.jsp");
        }catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
}
