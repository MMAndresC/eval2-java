<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="includes/header.jsp"%>

<main class="mt-5">
    <div class="container">
        <h1 class="h1 mt-4 mb-2 h1-title">${movie.title}</h1>
        <div class="row" id="pelicula">

            <div class="col-md-4">
                <img src="../assets/${movie.poster}" alt="" title="" class="img-responsive" width="375" height="556">
                <h2 class="h2 mt-3 h2-entradas">Compra de entradas</h2>
                <div class="sesiones">
                    <div class="horarios">
                        <ul>
                            <c:forEach items="${screenings}" var="screening">
                                <li>${screening.formattedDate}</li>
                                <ul>
                                    <li>
                                        <a href="preticket-info?id=${screening.id}">${screening.formattedDate} Sala ${screening.id_hall} - ${screening.hourScreening}</a>
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
