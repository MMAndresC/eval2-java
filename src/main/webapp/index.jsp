<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="includes/header.jsp"%>
    <main>
        <h1>Cartelera</h1>
        <section>
            <div class="album py-5 bg-light">
                <div class="container">
                     <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                        <c:forEach items= "${premiere}" var="movie">
                                <div class="col">
                                    <div class="card shadow-sm">
                                        <a href="sessions.jsp?id=${movie.movies_id}">
                                            <img src="../assets/${movie.poster}" class="bd-placeholder-img card-img-top"/>
                                            <p>${movie.title}</p>
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