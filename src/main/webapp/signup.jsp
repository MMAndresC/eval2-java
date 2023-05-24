<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="includes/header.jsp"%>

<script>
    $(document).ready(function() {
        var options = {
            target: "#result",
            beforeSubmit: showRequest,
            success: showResponse,
        };

        // bind to the form's submit event
        $('#formRegisterUser').submit(function() {
            $(this).ajaxSubmit(options);
            return false;
        });
    });
     function showRequest(formData, jqForm, options){
        console.log(formData[0]);
        console.log(formData[1]);
     }
     function showResponse(responseText, statusText) {
     //TODO modal con mensaje de error?, mirar como mandar un json de respuesta del server y asi cambiar pag solo cuando este logueado
        if($.trim(responseText) == "LogIn"){
            window.location.href = "/cinema";
        }
     }
</script>

<main class="container">
    <form class="row g-3" action="signup" method="post" id="formRegisterUser">
        <div class="col-12">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="col-12">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary" id="btnSubmit">Log in</button>
        </div>
    </form>
    <div id="result"></div>
</main>


<%@include file="includes/footer.jsp"%>