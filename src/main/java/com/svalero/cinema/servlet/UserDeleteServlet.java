package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.UsersDao;
import com.svalero.cinema.domain.Users;
import com.svalero.cinema.utils.PBKDF2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user-delete")
public class UserDeleteServlet extends HomeServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String responseError = "<div class='alert alert-danger' role='alert'> " +
                "<p>No ha sido posible borrar el usuario, password incorrecto</p>" +
                "</div>";
        HttpSession session = request.getSession(false);
        if(session != null){
            //Borrar usario e imagen
            try{
                String email = ((String) session.getAttribute("user")).trim();
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Database.connectDb();
                String password = request.getParameter("password");
                String encodedPass = PBKDF2.encodePassword(password);
                boolean isDeleted = Database.jdbi.withExtension(UsersDao.class, dao -> dao.deleteUser(email, encodedPass));
                if(isDeleted){
                    session.invalidate();
                    out.println("deleted");
                }else{
                    out.println(responseError);
                }
            }catch(ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            }
        }else{
            out.println(responseError);
        }
    }
}
