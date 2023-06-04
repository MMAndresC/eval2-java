<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="includes/header.jsp"%>


<main class="container mt-5 main-user-tickets">
    <div class="row mb-5 text-center">
        <h2 class="h2 text-uppercase">Tickets</h2>
    </div>
    <div class="row">
        <form class="d-flex" action="tickets-search" method="get" id="formSearchTicket">
            <div class="d-flex align-items-center col-6">
                <input class="form-control me-2 height-user-ticket input-search-date" type="date" name="date" placeholder="Fecha" aria-label="Search" value="${date}">
                <input type="hidden" name="btn" value="1"/>
                <button class="btn btn-outline-success height-user-ticket me-1" type="submit">Buscar</button>
                <button class="btn btn-outline-success height-user-ticket ms-1" type="button">
                    <a href="show-tickets?btn=1">Mostrar todos</a>
                </button>
            </div>
            <fieldset class="col-5 border ps-5 pt-2 pb-2">
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
    <section class="row mt-5">
        <ul class="container container-data-tickets">
            <c:forEach items="${tickets}" var="ticket" begin="${offset}" end="${limit}">
                <li class="row">
                    <span class="col-5">${ticket.title}</span>
                    <span class="col-2">Sala ${ticket.hall}</span>
                    <span class="col-2">${ticket.formattedDate}</span>
                    <span class="col-1">${ticket.hour}</span>
                    <span class="col-1">${ticket.mySeats}</span>
                    <span class="col-1">${ticket.price}€</span>
                </li>
            </c:forEach>
        </ul>
        <nav aria-label="..." class="mt-4 d-flex justify-content-center">

                <ul class="pagination pagination-lg">
                    <c:forEach begin="0" end="${end}" varStatus="status">
                        <li class="page-item" aria-current="page">
                            <button type="submit" class="page-link">
                                <a href="show-tickets?btn=${status.index + 1}">${status.index + 1}</a>
                            </button>
                        </li>
                    </c:forEach>
                </ul>

        </nav>
    </section>
</main>

<%@include file="includes/footer.jsp"%>