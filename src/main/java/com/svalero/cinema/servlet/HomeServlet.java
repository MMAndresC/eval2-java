package com.svalero.cinema.servlet;

import com.svalero.cinema.dao.Database;
import com.svalero.cinema.dao.MoviesDao;
import com.svalero.cinema.domain.Movies;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomeServlet  extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Database.connectDb();
            List<Movies> moviesList = Database.jdbi.withExtension(MoviesDao.class, MoviesDao::getPremiereMovies);
            request.setAttribute("premiere", moviesList); // Store products in request scope.
            request.setAttribute("total", moviesList.size());
            request.getRequestDispatcher("/index.jsp").forward(request, response); // Forward to JSP page
        }catch(Exception e){
            throw new ServletException("Error retrieving premiere movies",e);
        }

    }
}
