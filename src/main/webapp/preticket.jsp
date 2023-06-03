<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="includes/header.jsp"%>

<main class="container">
    <div class="row">
        <section class="col-6 d-flex flex-column">
            <div>
                <img src="../assets/theater.jpg" class="img-fluid"/>
            </div>
            <div id="theatre">
                <c:forEach begin="1" end="${lines}" varStatus="line">
                    <c:forEach begin="1" end="${cols}" varStatus="col">
                        <input type="checkbox" name="seat" value="Fila: ${line.index} Butaca: ${col.index}">
                    </c:forEach>
                    <br>
                </c:forEach>
            </div>
        </section>

        <section class="col-6">
            <div class="card">
                <div class="card-header">
                    ${screening.title}
                </div>
                <div class="card-body">
                    <h5 class="card-title">${screening.formattedDate} - ${screening.hourScreening}</h5>
                    <h5>Sala ${screening.id_hall}</h5>
                    <div id="show-chosen-seats"></div>
                    <p id="show-price">0€</p>
                    <button type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" class="btn btn-primary"
                            id="btn-prebuy" ${sessionScope.user == null ? "disabled" : ""} > Comprar
                    </button>
                    <!-- Modal -->
                    <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="confirmModalLabel">Confirmar compra</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <p>¿Desea comprar entrada para ${screening.title} el ${screening.formattedDate}
                                        a las ${screening.hourScreening}?</p>
                                </div>
                                <div class="modal-footer">
                                    <form class="d-flex" action="buy-ticket" method="post" id="formBuyTicket">
                                        <input type="hidden" name="email" value="${sessionScope.user}"/>
                                        <input type="hidden" name="idMovie" value="${screening.id_movie}"/>
                                        <input type="hidden" name="idHall" value="${screening.id_hall}"/>
                                        <input type="hidden" name="date" value="${screening.dateScreening}"/>
                                        <input type="hidden" name="hour" value="${screening.hourScreening}"/>
                                        <input type="hidden" name="price" value=""/>
                                        <button type="submit" class="btn btn-secondary" id="btn-buy">Comprar</button>
                                    </form>
                                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Atras</button>
                                </div>
                                <div id="warning" class='alert alert-danger' role='alert'>
                                    <p>Tiene que seleccionar su butaca</p>
                                </div>
                            </div>
                        </div>º
                    </div>
                    <c:if test="${sessionScope.user == null}">
                        <div class='alert alert-danger' role='alert'>
                            <p>Tienes que <a href="signup.jsp">loguearte</a> o <a href="signin.jsp">registrarte</a> para comprar entradas</p>
                        </div>
                    </c:if>
                </div>
            </div>
        </section>
    </div>


</main>

<%@include file="includes/footer.jsp"%>

<script>
    window.addEventListener('load', (event) => {
        let btnPreBuy = document.getElementById('btn-prebuy');
        btnPreBuy.addEventListener('click', (event) => {
            let price = document.getElementById('show-price');
            if(price.textContent.trim() === '0€'){
                document.getElementById('btn-buy').disabled = true;
            }
        });
        let seats = document.querySelectorAll('[name="seat"]');
        seats.forEach(seat => {
            seat.addEventListener('click', (event) => {
                let div = document.getElementById('show-chosen-seats');
                let line = event.target.value.split(' ')[1];
                let col = event.target.value.split(' ')[3];
                let id = line + '-' + col;
                if(event.target.checked){
                    let p = document.createElement('p');
                    p.innerText = event.target.value
                    p.setAttribute('id', id);
                    div.append(p);
                    document.getElementById('btn-buy').disabled = false;
                    document.getElementById('warning').hidden = true;
                }else{
                    let seatUnchecked = document.getElementById(id);
                    seatUnchecked.remove();
                }
                let numTickets = div.childElementCount;
                document.getElementById('show-price').innerText = (numTickets * 6.50) + '€'.trim();
                document.querySelector('[name="price"]').value = numTickets * 6.50;
                if(numTickets === 0) document.getElementById('warning').hidden = false;
            });
        });
    });

</script>


