package com.svalero.cinema.servlet;


import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.UsersDao;
import com.svalero.cinema.domain.Users;
import com.svalero.cinema.utils.PBKDF2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String responseError = "<div class='alert alert-danger' role='alert'> " +
                "<p>Email o password incorrectos</p>" +
                "<p>¿Es nuevo usuario?, <a href='signup.jsp'>AQUI</a> para registrarse</p>" +
                "</div>";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Database.connectDb();
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String encodedPass = PBKDF2.encodePassword(password);
            Users user = Database.jdbi.withExtension(UsersDao.class, dao -> dao.getRegisteredUser(email, encodedPass));

            if(user == null){
                out.println(responseError);
            }else{
                HttpSession session = request.getSession();
                session.setAttribute("user", email);
                session.setAttribute("role", user.getRole());
                //setting session to expiry in 30 mins
                session.setMaxInactiveInterval(30*60);
                Cookie userName = new Cookie("user", email);
                userName.setMaxAge(30*60);
                response.addCookie(userName);
                //Esto lo devuelvo para que en el ajax se evalue si ha tenido exito o no el login
                out.println("LogIn");
            }
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

    }
}
