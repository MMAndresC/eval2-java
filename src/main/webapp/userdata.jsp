<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="includes/header.jsp"%>

<script>
    $(document).ready(function() {
        $('#btn-delete').click(function() {
            $('#confirm-dialog-del').removeAttr('hidden');
        })
        $('#btn-cancel-del').click(function() {
            $('#confirm-dialog-del').attr('hidden', '');
        })
        var optionsDelete = {
            target: "#result",
            beforeSubmit: showRequestDelete,
            success: showResponseDelete,
        };

        $('#formDeleteUser').submit(function() {
            $(this).ajaxSubmit(optionsDelete);
            return false;
        });
    })

    function showRequestDelete(formData, jqForm, options){
        console.log(formData);
    }

    function showResponseDelete(responseText, statusText) {
        if($.trim(responseText) == "deleted"){
             window.location.href = "/cinema";
        }
    }
</script>

<main class="container">
    <form class="g-3 container" action="edit-user" method="post" id="formEditUser">
        <div class="row">
            <img src="../cinema-data/${data.image}" class="img-thumbnail col" alt="avatar" name="img">
        </div>
        <div class="row">
            <input type="file" class="form-control col" id="image" name="image">
        </div>
        <div class="row">
            <div class="col">
                <label for="email" class="form-label">eMail</label>
                <div class="input-group">
                    <span class="input-group-text" id="inputGroupPrepend3">@</span>
                    <span class="" id="email" aria-describedby="inputGroupPrepend3" name ="email">${data.email}</span>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <label for="name" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="name" value="${data.name}">
            </div>
            <div class="col-md-6">
                <label for="phone" class="form-label">Telefono</label>
                <input type="text" class="form-control" id="phone" value="${data.phone}">
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <button class="btn btn-primary" type="submit">Guardar cambios</button>
                <button type="button" id="btn-delete" class="btn btn-warning" data-toggle="modal" data-target="#exampleModal">BORRAR</button>
            </div>
        </div>
    </form>
    <div class="container" id="confirm-dialog-del"hidden>
        <form class="" action="user-delete" method="post" id="formDeleteUser">
            <p>Borrara sus datos personales y el historial de todos los tickets que ha ido comprando</p>
            <p>Esta accion es irreversible, ¿Quiere darse de baja?</p>
            <input type="text" class="form-control" id="password" name="password" placeholder="password">
            <button class="btn btn-danger" type="submit" id="btn-confirm-del">BORRAR</button>
            <button class="btn btn-info" type="button" id="btn-cancel-del">Atras</button>
        </form>
    </div>
    <div id="result"></div>
</main>

<%@include file="includes/footer.jsp"%>