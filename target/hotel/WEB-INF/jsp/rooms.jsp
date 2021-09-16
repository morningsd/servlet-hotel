<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Rooms"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="row">
        <div class="col-md-3">
            <form class="search_form" id="search_form" method="post" action="/jsp/filter">
                <button class="btn btn-outline-secondary btn-sm btn-block" type="submit">
                    <i class="bi bi-search" aria-hidden="true">Filter</i>
                </button>

                <button type="button" class="btn btn-outline-info btn-sm btn-block" data-toggle="collapse"
                        data-target="#search_feed">Refine your filter<span
                        class="caret"></span>
                </button>

                <div id="search_feed" class="collapse in">
                    <hr>
                    <div class="list-group list-group">
                        <h4>Categories</h4>
                    </div>
                    <hr>
                    <h4>Date</h4>
                    <select class="custom-select" name="search_date">
                        <c:choose>
                            <c:when test="${dateOrder eq 'ASC'}">
                                <option value="ASC" selected>Ascending&#8593;
                                </option>
                                <option value="DESC">Descending&#8595;
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option value="ASC">Ascending&#8593;</option>
                                <option value="DESC" selected>Descending&#8595;
                                </option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                    <hr>
                    <h4>Price</h4>
                    <select class="custom-select" name="search_price">
                        <c:choose>
                            <c:when test="${priceOrder eq 'ASC'}">
                                <option value="ASC" selected>Ascending&#8593;
                                </option>
                                <option value="DESC">Descending&#8595;
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option value="ASC">Ascending&#8593;</option>
                                <option value="DESC" selected>Descending&#8595;
                                </option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>
            </form>
        </div>
        <div class="col-md-9">
            <c:if test="${currentPage > maxPage}">
                <h2>No data. You can return to exposition list <a href="?page=1">here</a></h2>
            </c:if>

            <c:forEach var="room" items="${roomList}">

                <h3 class="title">Room #${room.id}</h3>
                <img src="${room.imagePath}" alt="room image" width="400" height="250">
                <p>Number of people: ${room.peopleCapacity}<br>
                    Status: ${room.status}<br>
                    Type: ${room.type}</p>
                <p class="text-muted">
                    <i class="bi bi-credit-card"></i> Price(1 day):
                    &#8372;${room.price}
                </p>
                <ul class="list-inline">
                    <li class="list-inline-item">
                        <c:choose>
                            <c:when test="${empty accountRole}">
                                <form class="form-inline" action="/jsp/login">
                                    <button type="submit" class="btn btn-outline-primary">Book</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${room.status == 'FREE'}">
                                        <button type="button" class="btn btn-outline-primary" data-toggle="modal"
                                                data-target="#modal${room.id}">Book</button></c:when>
                                    <c:otherwise>
                                        <button type="button" class="btn btn-outline-primary" data-toggle="modal"
                                                data-target="#modal${room.id}" disabled>Book</button></c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                        <!-- Modal -->
                        <div class="modal fade" id="modal${room.id}" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Book room #${room.id}</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form name="visit" id="book${room.id}" action="/jsp/client/bookRoom"
                                              method="post" class="visit-form">
                                            <input type="hidden" name="room_id" value="${room.id}">
                                            <input type="hidden" name="account_id" value="${account.id}">
                                            <input type="hidden" name="room_price" value="${room.price}">
                                            <div class="row form-group">
                                                <div class="col datetime">
                                                    <label for="date_timepicker_start">Start date:</label>
                                                    <input type="text" class="form-control datetimepicker" id="date_timepicker_start"
                                                           placeholder="Choose start date"
                                                           name="start_date" autocomplete="off">
                                                </div>
                                                <div class="col datetime">
                                                    <label for="date_timepicker_end">End date:</label>
                                                    <input type="text" class="form-control datetimepicker" id="date_timepicker_end"
                                                           placeholder="Choose end date"
                                                           name="end_date" autocomplete="off">
                                                </div>
                                            </div>
                                            <p>Cost(1 day): &#8372;${room.price}</p>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" form="book${room.id}" class="btn btn-primary">
                                            Book</button>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                            Close
                                        </button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </li>
                    <c:if test="${accountRole == 'MANAGER'}">
                        <li class="list-inline-item">
                            <form class="form-inline" action="/jsp/manager/editRoom">
                                <input type="hidden" name="room_id" value="${room.id}">
                                <button type="submit" class="btn btn-outline-secondary">Edit</button>
                            </form>
                        </li>
                        <li class="list-inline-item">
                            <form class="form-inline" action="/jsp/manager/deleteRoom">
                                <input type="hidden" name="room_id" value="${room.id}">
                                <button type="submit" class="btn btn-outline-danger">Delete</button>
                            </form>
                        </li>
                    </c:if>
                </ul>
                <hr>
            </c:forEach>
        </div>
    </div>
    <c:if test="${not empty roomList}">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:choose>
                    <c:when test="${currentPage == 1}">
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1">Previous</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="?page=${currentPage-1}" tabindex="-1">Previous</a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <c:forEach begin="${(currentPage-3 <= 0) ? 1 : currentPage-2}"
                           end="${(currentPage-3 <= 0) ? (maxPage >= 5 ? 5 : maxPage) : (maxPage >= currentPage+2 ? currentPage+2 : maxPage)}"
                           var="val">
                    <c:choose>
                        <c:when test="${val == currentPage}">
                            <li class="page-item active"><a class="page-link" href="?page=${val}">${val}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="?page=${val}">${val}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${currentPage < maxPage}">
                        <li class="page-item">
                            <a class="page-link" href="?page=${currentPage+1}">Next</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="#">Next</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </c:if>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
<script src="/js/jquery-3.6.0.min.js"></script>
<script src="/js/jquery.validate.min.js"></script>
<script src="/js/form-validation.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
<!-- this should go after your </body> -->
<link rel="stylesheet" type="text/css" href="/css/jquery.datetimepicker.css"/>
<script src="/js/jquery.js"></script>
<script src="/js/jquery.datetimepicker.full.min.js"></script>
<script>
    jQuery.noConflict();
    jQuery('.datetimepicker').datetimepicker({
        timepicker: false,
        //format: 'd/m/y',
        minDate:'-1970/01/01',
        beforeShowDay: disableHoliday
    });

    function disableHoliday(date) {
        const startDate = new Date($("date_timepicker_start").val());
        const endDate = new Date($("date_timepicker_end").val());
        const filterDate = new Date(date);
        console.log($("date_timepicker_start"));
        const day = filterDate.getDay();
        if (isValidDate(startDate)) {
            return [day >= startDate]
        } else if (isValidDate(endDate)) {
            return [day <= endDate]
        } else if (isValidDate(startDate) && isValidDate(endDate)) {
            return [day >= startDate && day <= endDate]
        }
        return true;
    }

    function isValidDate(d) {
        return d instanceof Date && !isNaN(d);
    }

    function calc(id) {
        var price = document.getElementById("ticket_price" + id).innerHTML;
        var noTickets = document.getElementById("quantity" + id).value;
        var total = parseFloat(price) * noTickets;
        if (!isNaN(total)) {
            document.getElementById("total" + id).innerHTML = total;
        }
    }
</script>
</html>