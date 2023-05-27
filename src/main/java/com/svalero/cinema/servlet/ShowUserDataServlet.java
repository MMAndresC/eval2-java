package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.UsersDao;
import com.svalero.cinema.domain.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user-data")
public class ShowUserDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null) {
            try {
                String email = ((String) session.getAttribute("user")).trim();
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Database.connectDb();
                Users user = Database.jdbi.withExtension(UsersDao.class, dao -> dao.getUserForEmail(email));
                user.setPassword(""); //No se reenvia el password por proteccion
                request.setAttribute("data", user); // Store products in request scope.
                request.getRequestDispatcher("/userdata.jsp").forward(request, response); // Forward to JSP page
            } catch (Exception e) {
                throw new ServletException("Error retrieving user's data", e);
            }
        }else{
            response.sendRedirect("/cinema");
        }
    }
}
