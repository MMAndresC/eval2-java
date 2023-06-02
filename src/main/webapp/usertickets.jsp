<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="includes/header.jsp"%>

<main class="container">
    <div class="row">
        <h2>Tickets</h2>
    </div>
    <div class="row">
        <form class="d-flex" action="tickets-search" method="get" id="formSearchTicket">
            <div class="d-flex">
                <input class="form-control me-2" type="date" name="date" placeholder="Fecha" aria-label="Search" value="${date}">
                <button class="btn btn-outline-success" type="submit">Buscar</button>
                <button class="btn btn-outline-success" type="button">
                    <a href="show-tickets">Mostrar todos</a>
                </button>
            </div>
            <fieldset>
                <legend>Busqueda de ticket por fecha</legend>
                <div>
                    <input type="radio" id="exact" name="criteria" value="exact"
                           checked>
                    <label for="exact">exacta</label>
                </div>
                <div>
                    <input type="radio" id="before" name="criteria" value="before">
                    <label for="before">anterior</label>
                </div>

                <div>
                    <input type="radio" id="after" name="criteria" value="after">
                    <label for="after">posterior</label>
                </div>
            </fieldset>
        </form>
    </div>
    <section class="row">
        <ul class="container">
            <c:forEach items="${tickets}" var="ticket">
                <li class="row">
                    <span class="col">${ticket.title}</span>
                    <span class="col">${ticket.hall}</span>
                    <span class="col">${ticket.formattedDate}</span>
                    <span class="col">${ticket.hour}</span>
                    <span class="col">${ticket.mySeats}</span>
                    <span class="col">${ticket.price}€</span>
                </li>
            </c:forEach>
        </ul>
    </section>
</main>

<%@include file="includes/footer.jsp"%>