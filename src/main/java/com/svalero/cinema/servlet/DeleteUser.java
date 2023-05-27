package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.UsersDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("delete-user")
public class DeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try{
            HttpSession session = request.getSession(false);
            String email = ((String) session.getAttribute("user")).trim();
            String password = (request.getParameter("password")).trim();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Database.connectDb();
            boolean isDeleted = Database.jdbi.withExtension(UsersDao.class, dao -> dao.deleteUser(email, password));
            if(!isDeleted){
                out.println("<p class='alert alert-danger' role='alert'>No se ha podido autenticar el usuario, no se ha borrado la cuenta</p>");
            }else{
                session.invalidate();
                out.println("deleted");
            }
        } catch (Exception e) {
            throw new ServletException("Error deleting registered user", e);
        }

    }
}
