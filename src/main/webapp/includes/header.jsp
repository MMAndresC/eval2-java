<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
    <script src="https://malsup.github.io/jquery.form.js"></script>
    <link rel="icon" type="image/x-icon" href="../assets/favicon.ico">
    <link rel="stylesheet" href="./styles/style2.css">
    <title>Cinexin</title>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light nav-color">
        <div class="container-fluid">
            <a class="navbar-brand d-flex align-items-center" href="/cinema">
                <img src="../assets/logo.jpg" alt="" width="80" height="50" class="d-inline-block align-text-top">
                <span class="ms-2">Cinexin</span>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse d-flex justify-content-end align-items-center menu" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 d-flex align-items-center">
                    <c:if test = "${sessionScope.user == null}">
                        <li class="nav-item me-1">
                            <a class="nav-link" href="signin.jsp">Registrarse</a>
                        </li>
                        <li class="nav-item  me-1">
                            <a class="nav-link" href="signup.jsp">Log in</a>
                        </li>
                    </c:if>
                    <c:if test = "${sessionScope.user != null}">
                        <li class="nav-item  me-3 d-flex align-items-center">
                            Bienvenide, ${sessionScope.user}
                        </li>
                        <c:if test = "${sessionScope.role.trim() == 'admin'}">
                            <li class="nav-item  me-2">
                                <a class="nav-link" href="adminzone.jsp">Zona Admin</a>
                            </li>
                        </c:if>
                        <li class="nav-item dropdown  me-2">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Zona Usuario
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="user-data">Datos</a></li>
                                <li><a class="dropdown-item" href="show-tickets?btn=1">Entradas</a></li>
                            </ul>
                        </li>
                        <li class="nav-item  ms-1">
                            <button type="button" data-bs-toggle="modal" data-bs-target="#logoutModal" class="btn btn-outline-danger btn-login rounded">
                                ${sessionScope.user.trim().toUpperCase().charAt(0)}
                            </button>
                            <!-- Modal -->
                            <div class="modal fade" id="logoutModal" tabindex="-1" aria-labelledby="logoutModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="logoutModalLabel">Cerrar sesion</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            ¿Desea salir de la sesion?
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" >
                                                <a href="logout">Cerrar</a>
                                            </button>
                                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Atras</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</header>