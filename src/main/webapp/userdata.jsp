<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="includes/header.jsp"%>



<main class="container mt-5 main-user-data">
    <form class="g-3 container" action="user-edit" method="post" id="formEditUser">
        <div class="row d-flex justify-content-center mb-2">
            <img src="../cinema-data/${data.image}" class="col img-data" alt="avatar" name="img" id="img">
        </div>
        <div class="row mb-3 mt-2 p-2 d-flex justify-content-center row-img-data">
            <input type="hidden" class="form-control" id="image" name="image" value="${data.image}">
            <input type="file" class="form-control" id="newImg" name="newImg" accept="image/jpg, image/png, image/jpeg"
                   onchange="document.getElementById('img').src = window.URL.createObjectURL(this.files[0])">
        </div>
        <div class="row mb-3">
            <div class="col">
                <div class="input-group d-flex align-items-center justify-content-center">
                    <span class="input-group-text" id="inputGroupPrepend3">@</span>
                    <span class="ms-2" id="email" aria-describedby="inputGroupPrepend3" name ="email">${data.email}</span>
                </div>
            </div>
        </div>
        <div class="row mb-5 mt-2">
            <div class="col-md-7">
                <label for="name" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="name" name="name" value="${data.name}">
            </div>
            <div class="col-md-5">
                <label for="phone" class="form-label">Telefono</label>
                <input type="text" class="form-control" id="phone" name="phone" value="${data.phone}">
            </div>
        </div>
        <div class="row mb-2 mt-3 ">
            <div class="col-12 d-flex justify-content-center">
                <button class="btn btn-primary me-2" type="submit">Guardar cambios</button>
                <button type="button" id="btn-delete" class="btn btn-warning ms-2" >BORRAR usuario</button>
            </div>
        </div>
    </form>
    <div class="container modal-delete" id="confirm-dialog-del"hidden>
        <form class="" action="user-delete" method="post" id="formDeleteUser">
            <p>Borrara sus datos personales y el historial de todos los tickets que ha ido comprando</p>
            <p>Esta accion es irreversible, ¿Quiere darse de baja?</p>
            <input type="hidden" class="form-control" id="image-del" name="image-del" value="${data.image}"/>
            <input type="password" class="form-control mb-4" id="password" name="password" placeholder="password">
            <div class="d-flex justify-content-center">
                <button class="btn btn-danger me-1" type="submit" id="btn-confirm-del">BORRAR</button>
                <button class="btn btn-info ms-1" type="button" id="btn-cancel-del">Atras</button>
            </div>
        </form>
    </div>
    <div id="result"></div>
</main>

<%@include file="includes/footer.jsp"%>

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
            success: showResponseDelete,
        };

        $('#formDeleteUser').submit(function() {
            $(this).ajaxSubmit(optionsDelete);
            return false;
        });

        var optionsEdit = {
            target: "#result",
            beforeSubmit: showRequestEdit,
            success: showResponseEdit,
        };
        $('#formEditUser').ajaxForm(optionsEdit);
    })

    function showResponseDelete(responseText, statusText) {
        if($.trim(responseText) == "deleted"){
             window.location.href = "/cinema";
        }
    }

   function showRequestEdit(formData, jqForm, options){
        var patternPhone = /^[0-9-()+]{3,20}/;
        if(!formData[3].value.match(patternPhone)){
            alert('Formato de telefono no valido');
            $("#phone").select();
            return false;
        }
        return true;
   }

   function showResponseEdit(responseText, statusText) {
        if($.trim(responseText) == "Saved"){
             window.location.href = "/cinema/user-data";
        }
   }

</script>