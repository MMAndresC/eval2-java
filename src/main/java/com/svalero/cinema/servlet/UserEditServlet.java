package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.UsersDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@WebServlet("/user-edit")
@MultipartConfig
public class UserEditServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            HttpSession session = request.getSession(false);
            String email = ((String) session.getAttribute("user")).trim();
            String newImg = request.getParameter("newImg");
            String image = request.getParameter("image");
            String imgToEdit = image;
            String phone = request.getParameter("phone");
            String name = request.getParameter("name");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Database.connectDb();
            if(newImg != ""){
                Part imagePart = request.getPart("newImg");
                String imgName = imagePart.getSubmittedFileName();
                if(!(imgName.equalsIgnoreCase("Default_pfp.jpg"))) {
                    int index = imgName.lastIndexOf('.');

                    String extension = imgName.substring(index);
                    if (extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png")) {
                        String imagePath = request.getServletContext().getInitParameter("image-path");
                        String fileName = UUID.randomUUID() + extension;
                        InputStream fileStream = imagePart.getInputStream();
                        //Copia la nueva imagen y elimina la antigua siempre que no sea la default
                        Files.copy(fileStream, Path.of(imagePath + File.separator + fileName));
                        if(!(image.equalsIgnoreCase("Default_pfp.jpg"))){
                            Files.delete(Paths.get(imagePath + File.separator + image));
                        }
                        imgToEdit = fileName;
                    }
                }
            }
            String finalImgToEdit = imgToEdit;
            int regEdited = Database.jdbi.withExtension(UsersDao.class, dao -> dao.editDataUser(name, finalImgToEdit, phone, email));
            out.println("Saved");
        }catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
}
