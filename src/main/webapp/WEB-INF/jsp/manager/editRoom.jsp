<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Edit room"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <form action="/jsp/manager/editRoom" method="post" name="add-application" enctype="multipart/form-data">
        <input type="hidden" name="room_id" value="${room.id}">
        <div class="form-row">
            <label for="file" class="col-form-label col-sm-4">Choose image:</label>
            <div class="col-form-label col-sm-4">
                <input type="checkbox" id="keep-image" name="keep-image"
                       checked>
                <label for="keep-image">Keep image</label>
            </div>
            <input type="file" name="file" id="file" class="col-sm-4" accept="image/png, image/jpeg"/>
        </div>
        <div class="form-row">
            <label for="quantity" class="col-form-label col-sm-8">Number of people in the room:</label>
            <input type="number" class="form-control col-sm-4" min="0" max="6"
                   id="quantity" name="quantity" value="${room.peopleCapacity}">
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" class="form-control" id="price" name="price"
                   placeholder="Enter price" min="0" max="1000000"
                   autocomplete="off" value="${room.price}">

        </div>
        <div class="form-group">
            <label for="types">Select type:</label>
            <select class="custom-select" id="types" name="types">
                <c:forEach var="type" items="${typeList}">
                    <c:choose>
                        <c:when test="${type eq room.type}">
                            <option value="${type}" selected>${type}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${type}">${type}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="statuses">Select status:</label>
            <select class="custom-select" id="statuses" name="statuses">
                <c:forEach var="status" items="${statusList}">
                    <c:choose>
                        <c:when test="${status eq room.status}">
                            <option value="${status}" selected>${status}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${status}">${status}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Edit room</button>
    </form>


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
    jQuery('.datepicker').datetimepicker({
        timepicker: false,
        format: 'd:m:Y'
    });
</script>
</html>