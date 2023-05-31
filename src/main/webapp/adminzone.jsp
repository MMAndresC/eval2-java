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
                    <option value="${movie.movies_id}" ${sessionScope.indexMovie == movie.movies_id ? "selected" : ""}>${movie.title}</option>
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
        <tbody id="list-screenings">
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
                        <input type="hidden" name="id" value="${screening.id}"/>
                        <input type="date" name="date" value="${screening.dateScreening}"/>
                    </td>
                    <td>
                        <input type="text" name="hour" value="${screening.hourScreening}"/>
                        <input type="hidden" id="movie" name="movie" value="${screening.id_movie}"/>
                    </td>
                    <td>
                        <button class="btn btn-primary" type="submit">Editar</button>
                    </td>
                    <td>
                        <button class="btn btn-danger" type="button" name="btn-delete" id="${screening.id}">Borrar</button>
                    </td>
                </tr>
            </c:forEach>
        </form>
        </tbody>
        <div class="container" id="confirm-dialog-del"hidden>

            <p>¿Seguro?</p>
            <button class="btn btn-danger" type="button" id="btn-confirm-del">
                <a href="screening-delete?id=" id="delete-link">Borrar</a>
            </button>
            <button class="btn btn-info" type="button" id="btn-cancel-del">Atras</button>

        </div>
    </table>
    <div id="result"></div>
    <div class="container">
        <form class="row g-3" action="screening-add" method="post" id="formAddScreening">
                <div class="row">
                    <span class="col-1">Sala</span>
                    <select id="select-hall-add" name="hall-add" class="form-select col-2">>
                        <c:forEach items= "${sessionScope.halls}" var="hall">
                            <option value="${hall.id}" > Sala ${hall.id}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row">
                    <input type="hidden" id="movie-add" name="movie-add" value="${sessionScope.indexMovie}"/>
                    <span class="col-1">Fecha</span>
                    <input type="date" name="date-add" id="date-add" class="col-2"/>
                </div>
                <div clas="row">
                    <span class="col-1">Hora</span>
                    <input type="text" name="hour-add" id="hour-add" class="col-2"/>
                </div>
                <div class="row">
                    <button class="btn btn-primary col-2" type="submit" ${(sessionScope.indexMovie == 0) ? "disabled" : ""}>Añadir</button>
                    <button class="btn btn-secondary col-2" type="reset">Limpiar</button>
                </div>
        </form>
    </div>

</main>

<%@include file="includes/footer.jsp"%>

<script>
    $(document).ready(function() {
        $('[name="btn-delete"]').click(function() {
            $('#confirm-dialog-del').removeAttr('hidden');
            $('#delete-link').attr("href", "screening-delete?id=" + this.id)
        })
        $('#btn-cancel-del').click(function() {
            $('#confirm-dialog-del').attr('hidden', '');
        })
        //Cuando cambie el select de movies limpie el listado anterior
        $('#movies_id').change(function() {
            $('#list-screenings').html("");
        });

        var optionsEdit = {
            target: "#result",
            beforeSubmit: showRequestEdit,
            success: showResponseEdit,
        };
        $('#formEditScreening').ajaxForm(optionsEdit);

        var optionsAdd = {
            target: "#result",
            beforeSubmit: showRequestAdd,
            success: showResponseAdd,
        }
        $('#formAddScreening').ajaxForm(optionsAdd);
    })

    function showRequestEdit(formData, jqForm, options){
        console.log(formData[0]);
        console.log(formData[1]);
        console.log(formData[2]);
        console.log(formData[3]);
        var patternHour = /^([01]?[0-9]|2[0-3]):[0-5][0-9]$/;
        if(!formData[3].value.match(patternHour)){
            alert('Formato de hora no valido, ej 03:15');
            $("[name='hour']").select();
            return false;
        }

        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1;
        var yyyy = today.getFullYear();
        if(dd<10) dd='0'+dd;
        if(mm<10) mm='0'+mm;
        today =  yyyy + '-' + mm + '-' + dd;
        if(formData[2].value < today){
            alert('Fecha no valida');
            $("[name='date']").select();
            return false;
        }
        return true;
    }

   function showResponseEdit(responseText, statusText) {
        if($.trim(responseText) == "Saved"){
           console.log("saved");
        }
   }

   function showRequestAdd(formData, jqForm, options){
        console.log(formData[0]);
        console.log(formData[1]);
        console.log(formData[2]);
        console.log(formData[3]);
        var patternHour = /^([01]?[0-9]|2[0-3]):[0-5][0-9]$/;
        if(!formData[3].value.match(patternHour)){
            alert('Formato de hora no valido, ej 03:15');
            $("[name='hour-add']").select();
            return false;
        }

        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1;
        var yyyy = today.getFullYear();
        if(dd<10) dd='0'+dd;
        if(mm<10) mm='0'+mm;
        today =  yyyy + '-' + mm + '-' + dd;
        if(formData[2].value < today){
            alert('Fecha no valida');
            $("[name='date-add']").select();
            return false;
        }
        return true;
   }

    function showResponseAdd(responseText, statusText) {
        if($.trim(responseText) == "Saved"){
            window.location.href = "adminzone.jsp";
        }
   }



</script>


