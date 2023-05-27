<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="includes/header.jsp"%>

<main class="container">
    <form class="g-3 container">
        <div class="row">
            <img src="" class="img-thumbnail col" alt="avatar" name="img">
        </div>
        <div class="row">
            <input type="file" class="form-control col" id="image" name="image">
        </div>
        <div class="row">
            <div class="col">
                <label for="email" class="form-label">eMail</label>
                <div class="input-group">
                    <span class="input-group-text" id="inputGroupPrepend3">@</span>
                    <span class="" id="email" aria-describedby="inputGroupPrepend3" name ="email"></span>
                </div>
            </div>
        </div>
       <div class="row">
           <div class="col-md-6">
               <label for="name" class="form-label">Nombre</label>
               <input type="text" class="form-control" id="name">
           </div>
           <div class="col-md-6">
               <label for="phone" class="form-label">Telefono</label>
               <input type="text" class="form-control" id="phone">
           </div>
       </div>
        <div class="row">
            <div class="col-12">
                <button class="btn btn-primary" type="submit">Guardar cambios</button>
            </div>
        </div>
    </form>
</main>

<%@include file="includes/footer.jsp"%>