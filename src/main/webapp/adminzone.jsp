<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="includes/header.jsp"%>

<c:if test="${sessionScope.role != 'admin'}">
    <c:redirect url = "/"/>
</c:if>
<main class="container">
    <div class="row">
        <h2 class="col h2">Menu sesiones</h2>
    </div>
    <form class="row g-3" action="show-screenings" method="post" id="formShowScreenings">
        <div class="row">
            <label for="movies_id" class="form-label">Peliculas</label>
            <select id="movies_id" name="movies_id" class="form-select">
                <c:forEach items= "${sessionScope.movies}" var="movie">
                   <option value="${movie.movies_id}">${movie.title}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col">
            <button type="submit" class="btn btn-primary" id="btnShowScreenings">Mostrar sesiones</button>
        </div>
    </form>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Sala</th>
            <th scope="col">Fecha</th>
            <th scope="col">Hora</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <form class="row g-3" action="screening-edit" method="post" id="formEditScreening">
        <c:forEach items= "${screenings}" var="screening">
            <tr>
                <th scope="row"></th>
                <td>
                    <select id="select-hall" name="hall" class="form-select">>
                        <c:forEach items= "${sessionScope.halls}" var="hall">
                            <option value="${hall.id}" ${(screening.id_hall == hall.id) ? "selected" : ""}> Sala ${hall.id}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <input type="date" name="date" value="${screening.dateScreening}"/>
                </td>
                <td>
                    <input type="text" name="hour" value="${screening.hourScreening}"/>
                </td>
                <td></td>
            </tr>
        </c:forEach>
        </form>
        </tbody>
    </table>
    <div id="result"></div>

</main>

<%@include file="includes/footer.jsp"%>




