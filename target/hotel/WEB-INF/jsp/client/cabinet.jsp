<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Cabinet"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <c:choose>
        <c:when test="${not empty billList}">
            <h2>Your bills</h2>
            <h5>Click on room number to see more</h5>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Room</th>
                    <th>Room type</th>
                    <th>Price(1 day)</th>
                    <th>Start date</th>
                    <th>End date</th>
                    <th>Total</th>
                    <th>State</th>
                    <th>Accept</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="bill" items="${billList}">
                    <tr>
                        <td>
                            <button type="button" class="btn btn-outline-primary" data-toggle="modal"
                                    data-target="#modal${bill.room.id}">${bill.room.id}</button>
                        </td>
                        <td>${bill.room.type}</td>
                        <td>${bill.room.price}</td>
                        <td>${bill.startDate}</td>
                        <td>${bill.endDate}</td>
                        <td>${bill.total}</td>
                        <td>${bill.state}</td>
                        <td>
                            <form action="/jsp/client/payBill" method="post" id="pay-form">
                                <input type="hidden" name="bill_id" value="${bill.id}">
                                <c:choose>
                                    <c:when test="${bill.state == 'NEW'}">
                                        <button type="submit" class="btn btn-primary">Pay</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" class="btn btn-primary" disabled>Pay</button>
                                    </c:otherwise>
                                </c:choose>
                            </form>
                        </td>
                    </tr>
                    <!-- Modal -->
                    <div class="modal fade" id="modal${bill.room.id}" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Room ${bill.room.id}</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <img src="${bill.room.imagePath}" alt="room image" width="400" height="250">
                                    <p>Number of people: ${bill.room.peopleCapacity}<br>
                                        Status: ${bill.room.status}<br>
                                        Type: ${bill.room.type}</p>
                                    <p class="text-muted">
                                        <i class="bi bi-credit-card"></i> Price(1 day):
                                        &#8372;${bill.room.price}
                                    </p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                        Close
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <h2>You have no bills yet.</h2>
        </c:otherwise>
    </c:choose>
    <hr>
    <c:choose>
        <c:when test="${not empty applicationList}">
            <h2>Your applications</h2>
            <c:forEach var="application" items="${applicationList}">
                <p>Number of people: ${application.quantity}<br>
                    Min price: ${application.minPrice} | Max price: ${application.maxPrice}<br>
                    Start date: ${application.startDate} | End date: ${application.endDate}<br>
                    Type: ${application.type}
                </p>
                <p>Wishes:<br>${application.wishes}</p>
                <c:choose>
                    <c:when test="${not empty application.room}">
                        <h6>Special offer:</h6>
                        <h5 class="title">Room ${application.room.id}</h5>
                        <img src="${application.room.imagePath}" alt="room image" width="400" height="250">
                        <p>Number of people: ${application.room.peopleCapacity}<br>
                            Status: ${application.room.status}<br>
                            Type: ${application.room.type}</p>
                        <p class="text-muted">
                            <i class="bi bi-credit-card"></i> Price(1 day):
                            &#8372;${application.room.price}
                        </p>
                        <button type="button" class="btn btn-outline-primary" data-toggle="modal"
                                data-target="#modal${application.room.id}">Book
                        </button>
                        <!-- Modal -->
                        <div class="modal fade" id="modal${application.room.id}" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalLabel2" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel2">Book room ${application.room.id}</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form name="visit" id="book${application.room.id}" action="/jsp/client/bookRoom"
                                              method="post">
                                            <input type="hidden" name="room_id" value="${application.room.id}">
                                            <input type="hidden" name="account_id" value="${account.id}">
                                            <input type="hidden" name="room_price" value="${application.room.price}">
                                            <div class="row form-group">
                                                <div class="col">
                                                    <label for="start_date">Start date:</label>
                                                    <input type="text" class="form-control datetimepicker" id="start_date"
                                                           placeholder="Choose start date"
                                                           name="start_date" autocomplete="off">
                                                </div>
                                                <div class="col">
                                                    <label for="end_date">End date:</label>
                                                    <input type="text" class="form-control datetimepicker" id="end_date"
                                                           placeholder="Choose end date"
                                                           name="end_date" autocomplete="off">
                                                </div>
                                            </div>
                                            <p>Cost(1 day): &#8372;${application.room.price}</p>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" form="book${application.room.id}" class="btn btn-primary">
                                            Book</button>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                            Close
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <h5>You have no offers yet.</h5>
                    </c:otherwise>
                </c:choose>
                <form action="/jsp/client/cancelApplication" method="post" id="offer-form">
                    <input type="hidden" name="application_id" value="${application.id}">
                    <button type="submit" class="btn btn-primary col-sm-4">Cancel application</button>
                </form>
                <hr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h3>You have no applications yet.</h3>
        </c:otherwise>
    </c:choose>
    <p>You can <a href="/jsp/rooms" style="text-decoration: none">book a room</a> or <a
            href="/jsp/client/addApplication" style="text-decoration: none">create an application</a> and our
        manager will choose a room for you.</p>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
<script src="/js/jquery-3.6.0.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
<!-- this should go after your </body> -->
<link rel="stylesheet" type="text/css" href="/css/jquery.datetimepicker.css"/>
<script src="/js/jquery.js"></script>
<script src="/js/jquery.datetimepicker.full.min.js"></script>
<script>
    jQuery.noConflict();
    jQuery('.datepicker').datetimepicker({
        timepicker: false,
        format: 'd/m/Y'
    });
    jQuery('.timepicker').datetimepicker({
        datepicker: false,
        format: 'H:i'
    });
    jQuery('.datetimepicker').datetimepicker({
        format: 'd/m/y H:i'
    });
</script>
</html>