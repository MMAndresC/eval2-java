<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="includes/header.jsp"%>

<main class="container">
    <div class="row">
        <section class="col">
            <form>
                <c:forEach begin="0" end="5" varStatus="row">
                    <c:forEach begin="0" end="15" varStatus="col">
                        <input type="checkbox" name="seat" value="${row.index}-${col.index}">
                    </c:forEach>
                    <br>
                </c:forEach>
            </form>
        </section>

        <section class="col">

        </section>
    </div>


</main>

<%@include file="includes/footer.jsp"%>