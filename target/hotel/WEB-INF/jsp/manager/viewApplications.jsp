<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Applications"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <c:choose>
        <c:when test="${not empty applicationList}">
            <h2>Applications</h2>
            <c:forEach var="application" items="${applicationList}">
                <p>Number of people: ${application.quantity}<br>
                    Min price: ${application.minPrice} | Max price: ${application.maxPrice}<br>
                    Start date: ${application.startDate} | End date: ${application.endDate}<br>
                    Type: ${application.type}
                </p>
                <p>Wishes:<br>${application.wishes}</p>
                <form action="/jsp/manager/applicationOffer" method="post" id="offer-form">
                    <input type="hidden" name="application_id" value="${application.id}">
                    <div class="form-row">
                        <label for="room_id" class="col-form-label col-sm-2">Room id:</label>
                        <input type="number" class="form-control col-sm-2" min="1" step="1"
                               id="room_id" name="room_id">
                        <button type="submit" class="btn btn-primary col-sm-2">Offer</button>
                    </div>
                </form>
                <hr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h2>No applications now</h2>
        </c:otherwise>
    </c:choose>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
<script src="/js/jquery-3.6.0.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>