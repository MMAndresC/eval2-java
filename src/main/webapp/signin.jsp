<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="includes/header.jsp"%>


<main class="container mt-5 main-signin">
    <form class="row g-3" action="signin" method="post" id="formNewUser">
        <div class="col-md-6">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="col-md-6">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="col-8">
            <label for="name" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="name" name="name" autocomplete="off" required>
        </div>
        <div class="col-4 mb-2">
            <label for="phone" class="form-label">Telefono</label>
            <input type="tel" class="form-control" id="phone" name="phone" autocomplete="off" required>
        </div>
        <div class="col-6 d-flex justify-content-center">
            <img src="../cinema-data/Default_pfp.jpg" alt="user-avatar" id="img" class="img-fluid img-signin"/>
        </div>
        <div class="col-6 d-flex flex-column justify-content-center">
            <label for="image" class="form-label">Avatar</label>
            <input type="file" class="form-control w-60" id="image" name="image" accept="image/jpg, image/png, image/jpeg"
                   onchange="document.getElementById('img').src = window.URL.createObjectURL(this.files[0])">
        </div>
        <div class="col-12">
            <div class="form-check mt-4 mb-3">
                <input class="form-check-input" type="checkbox" value="" id="invalidCheck3" required>
                <label class="form-check-label" for="invalidCheck3">
                    He leido y acepto los terminos de usuario
                </label>
            </div>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary mt-4 mb-4" id="btnSubmit">Crear Usuario</button>
        </div>
    </form>
    <div id="result"></div>
</main>


<%@include file="includes/footer.jsp"%>

<script>
        // wait for the DOM to be loaded
        $(document).ready(function() {
            var options = {
                target: '#result',
                beforeSubmit: showRequest,
                success: showResponse,
                clearForm: true,
                resetForm: true,
            }
            $('#formNewUser').ajaxForm(options);
        });

            function showRequest(formData, jqForm, options){
                //Validacion de los datos
                var patternEmail = /^[a-zA-Z0-9\._-]+@[a-zA-Z0-9-]{2,}[.][a-zA-Z]{2,4}$/;
                var patternPassword = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;
                var patternPhone = /^[0-9-()+]{3,20}/;
                 if(!formData[0].value.match(patternEmail)){
                    alert('Formato de mail no valido');
                    $("#email").select();
                    return false;
                }
                if(!formData[1].value.match(patternPassword)){
                    alert('Contraseña tiene que ser longitud 8-15, letra mayuscula, minusculas, numero y simbolo');
                    $("#password").select();
                    return false;
                }
                if(!formData[3].value.match(patternPhone)){
                    alert('Formato de telefono no valido');
                    $("#phone").select();
                    return false;
                }
                return true;
            }

            function showResponse(responseText, statusText) {
                $("#img").attr("src", "../cinema-data/Default_pfp.jpg");
                if($.trim(responseText) == "LogIn"){
                    window.location.href = "/cinema";
                }
            };

</script>