<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="includes/header.jsp"%>

<script>
    // wait for the DOM to be loaded
    $(document).ready(function() {
        var optionsEdit = {
            target: '#result',
            beforeSubmit: showRequestEdit,
            success: showResponseEdit,
        }
         var optionsDelete = {
            target: '#result',
            beforeSubmit: showRequestDelete,
            success: showResponseDelete,
        }
        $('#formEditUser').ajaxForm(optionsEdit);
        $('#formDeleteUser').ajaxForm(optionsDelete);
    });
    function showRequestEdit(formData, jqForm, options){
        //Validacion de los datos
        var patternPhone = /^[0-9-()+]{3,20}/;
        //TODO borrar estos console.log
                 console.log(formData[0]);
                      console.log(formData[1]);
                         console.log(formData[2]);
                            console.log(formData[3]);
                    console.log(formData[4]);
        if(!formData[3].value.match(patternPhone)){
            alert('Formato de telefono no valido');
            $("#phone").select();
            return false;
        }
        return true;
    }

    function showResponseDelete(responseText, statusText) {
        if($.trim(responseText) == "deleted"){
            window.location.href = "/cinema";
        }
    };



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
                <button class="btn btn-danger">
                    Borrar usuario
                    <div class="modal" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Borrar su usuario</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <form action="delete-user" method="post" id="formDeleteUser">
                                    <div class="modal-body">
                                        <p>Borrara sus datos personales y el historial de todos los tickets que ha ido comprando</p>
                                        <p>Esta accion es irreversible, ¿Quiere darse de baja?</p>
                                        <input type="text" class="form-control" id="del-password">
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-danger">BORRAR</button>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </button>
            </div>
        </div>
    </form>
    <div id="result"></div>
</main>

<%@include file="includes/footer.jsp"%>