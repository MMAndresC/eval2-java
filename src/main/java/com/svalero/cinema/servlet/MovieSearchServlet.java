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

@WebServlet("/movie-search")
public class MovieSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try{
            String title = request.getParameter("title").trim().toLowerCase();
            String genre = request.getParameter("genre").trim().toLowerCase();
            if(title != "" || genre != ""){
                Class.forName("oracle.jdbc.driver.OracleDriver");
                List<Movies> moviesList = Database.jdbi.withExtension(MoviesDao.class, MoviesDao::getPremiereMovies);
                Movies movie = moviesList.stream()
                        .filter(movies -> movies.getTitle().toLowerCase().contains(title) &&
                                movies.getGenre().toLowerCase().contains(genre))
                        .findFirst().orElse(null);
                if(movie != null){
                    response.sendRedirect("movie-detail?id=" + movie.getMovies_id());
                }else{
                    response.sendRedirect("/cinema");
                }
            }else{
                response.sendRedirect("/cinema");
            }
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
}
