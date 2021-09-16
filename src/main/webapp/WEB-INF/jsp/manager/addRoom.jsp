<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Add room"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <form action="/jsp/manager/addRoom" method="post" name="add-application" enctype="multipart/form-data">
        <div class="form-row">
            <label for="file" class="col-form-label col-sm-8">File:</label>
            <input type="file" name="file" id="file" class="col-sm-4" accept="image/png, image/jpeg"/>
        </div>
        <div class="form-row">
            <label for="quantity" class="col-form-label col-sm-8">Number of people in the room:</label>
            <input type="number" class="form-control col-sm-4" min="0" max="6"
                   id="quantity" name="quantity">
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" class="form-control" id="price" name="price"
                   placeholder="Enter price" min="0" max="1000000"
                   autocomplete="off">

        </div>
        <div class="form-group">
            <label for="types">Select type:</label>
            <select class="custom-select" id="types" name="types">
                <c:forEach var="type" items="${typeList}">
                    <option value="${type}">${type}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="statuses">Select status:</label>
            <select class="custom-select" id="statuses" name="statuses">
                <c:forEach var="status" items="${statusList}">
                    <option value="${status}">${status}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Add room</button>
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