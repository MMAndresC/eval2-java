<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="includes/header.jsp"%>

<main>
    <div class="container">
        <h1>${movie.title}</h1>
        <div class="row" id="pelicula">
            <div class="col-md-4">
                <div class="info">
                    <span class="vose">4K</span>
                </div>
                <img src="../assets/${movie.poster}" alt="" title="" class="img-responsive" width="375" height="556">
                <h2>Compra de entradas</h2>
                <div class="sesiones">
                    <div class="horarios">
                        <ul>
                            <c:forEach items="${screenings}" var="screening">
                                <li>${screening.dateScreening}</li>
                                <ul>
                                    <li>
                                        <a href="/ticket?id=${screening.id}">${screening.dateScreening} Sala ${screening.id_hall} - ${screening.hourScreening}</a>
                                        <br>
                                    </li>

                                </ul>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

            </div>
            <div class="col-md-8">
                <div class="datos"><label>Fecha de estreno:</label> <span class="date-display-single">${movie.iniDate}</span></div>
                <div class="datos"><label>Género:</label> <span>${movie.genre}</span></div>
                <div class="datos"><label>Clasificación:</label> <span>${movie.rated}</span></div>
                <div class="datos"><label>Duración:</label> <span>${movie.duration}min.</span></div>
                <div class="datos"><label>Director:</label> <span>${movie.director}</span></div>
                <div class="datos"><label>Intérpretes:</label> <span>${movie.actors}</span></div>
                <div class="sinopsis"><p>${movie.synopsis}</p>
                    <p><strong>TAMBIÉN DISPONIBLE EN VERSIÓN ORIGINAL EN INGLÉS CON SUBTÍTULOS EN ESPAÑOL</strong></p>
                    <p><strong>DISPONIBLE COMPRA DE MEGAENTRADA. 6 MODELOS DIFERENTES</strong></p>
                </div>
            </div>
        </div>
    </div>
</main>

<%@include file="includes/footer.jsp"%>
