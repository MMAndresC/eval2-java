<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="includes/header.jsp"%>



<main class="container mt-5 d-flex justify-content-center main-signup">
    <div>
        <form class="row g-3" action="signup" method="post" id="formRegisterUser">
            <div class="col-12 w-60 mt-2 mb-2">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="col-12 w-60 mb-2">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="col-12 mb-2 d-flex justify-content-center">
                <button type="submit" class="btn btn-primary" id="btnSubmit">Log in</button>
            </div>
        </form>
        <div id="result" class="mb-5"></div>
    </div>
</main>


<%@include file="includes/footer.jsp"%>

<script>
    $(document).ready(function() {
        var options = {
            target: "#result",
            success: showResponse,
        };

        // bind to the form's submit event
        $('#formRegisterUser').submit(function() {
            $(this).ajaxSubmit(options);
            return false;
        });
    });

     function showResponse(responseText, statusText) {
        if($.trim(responseText) == "LogIn"){
            window.location.href = "/cinema";
        }
     }
</script>