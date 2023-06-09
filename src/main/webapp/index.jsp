<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="includes/header.jsp"%>
    <main>
        <div class="d-flex flex-column align-items-center">
            <h1 class="h1 mt-5 mb-5 text-uppercase">Cartelera</h1>
            <form class="d-flex" action="movie-search" method="get" id="formSearchMovie">
                <input class="form-control me-3" type="search" name="title" placeholder="Titulo" aria-label="Search">
                <input class="form-control ms-1 me-2" type="search" name="genre" placeholder="Genero" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Buscar</button>
            </form>
        </div>
        <section>
            <div class="album py-5 bg-light">
                <div class="container">
                     <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                        <c:forEach items= "${premiere}" var="movie">
                                <div class="col card-movie">
                                    <div class="card shadow-sm">
                                        <a href="movie-detail?id=${movie.movies_id}">
                                            <img src="../assets/${movie.poster}" class="bd-placeholder-img card-img-top"/>
                                            <p class="text-center mt-2">${movie.title}</p>
                                        </a>
                                    </div>
                                </div>
                        </c:forEach>
                     </div>
                </div>
            </div>
        </section>
    </main>

<%@include file="includes/footer.jsp"%>



