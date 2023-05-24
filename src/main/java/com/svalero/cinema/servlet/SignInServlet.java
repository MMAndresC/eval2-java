package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.UsersDao;
import com.svalero.cinema.domain.Users;
import com.svalero.cinema.utils.PBKDF2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@WebServlet("/signin")
@MultipartConfig
public class SignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Database.connectDb();
            String email = request.getParameter("email");
            Users user = Database.jdbi.withExtension(UsersDao.class, dao -> dao.getUserForEmail(email));

            if(!(user == null)){
                out.println(
                        "<div class='alert alert-danger' role='alert'> " +
                            "<p>Email ya registrado </p>" +
                            "<p>Si ya tiene creado un usuario, pinche <a href='signup.jsp'>AQUI</a></p>" +
                        "</div>"
                );
            }else{
                String fileName;
                String imagePath = request.getServletContext().getInitParameter("image-path");
                String password = request.getParameter("password");
                //Codifico el password antes de meterlo en la bd
                String encodedPass = PBKDF2.encodePassword(password);
                String name = request.getParameter("name");
                String phone = request.getParameter("phone");
                String role = "user";
                String image = request.getParameter("image");
                if( image != "" ){
                    Part imagePart = request.getPart("image");
                    String imgName = imagePart.getSubmittedFileName();
                    int index = imgName.lastIndexOf('.');
                    String extension = imgName.substring(index);
                    if(extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png")){
                        fileName = UUID.randomUUID() + extension;
                        InputStream fileStream = imagePart.getInputStream();
                        Files.copy(fileStream, Path.of(imagePath + File.separator + fileName));
                    }else{
                        fileName = "Default_pfp.jpg";
                    }
                }else{
                    fileName = "Default_pfp.jpg";
                }
                Database.jdbi.withExtension(UsersDao.class, dao -> {
                    dao.addUser(email, encodedPass, name, phone, fileName, role);
                    return null;
                });
            }
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

    }
}
