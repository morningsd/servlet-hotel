<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Create application"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <form action="/jsp/client/addApplication" method="post" name="add-application">
        <input type="hidden" name="account_id" value="${account.id}">
        <div class="form-row">
            <label for="quantity" class="col-form-label col-sm-8">Number of people in the room:</label>
            <input type="number" class="form-control col-sm-4" min="0" max="6"
                   id="quantity" name="quantity">
        </div>
        <div class="row form-group">
            <div class="col">
                <label for="start_date">Start date:</label>
                <input type="text" class="form-control datetimepicker" id="start_date"
                       placeholder="Choose start day"
                       name="start_date" autocomplete="off">
            </div>
            <div class="col">
                <label for="end_date">End date:</label>
                <input type="text" class="form-control datetimepicker" id="end_date"
                       placeholder="Choose end date"
                       name="end_date" autocomplete="off">
            </div>
        </div>
        <div class="form-group row">
            <div class="col">
                <label for="min-price">Min price:</label>
                <input type="number" class="form-control" id="min-price" name="min-price"
                       placeholder="Enter min price" min="0" max="1000000"
                       autocomplete="off">
            </div>
            <div class="col">
                <label for="max-price">Max price:</label>
                <input type="number" class="form-control" id="max-price" name="max-price"
                       placeholder="Enter max price" min="0" max="1000000"
                       autocomplete="off">
            </div>
        </div>
        <div class="form-group">
            <label for="wishes">Wishes:</label>
            <textarea class="form-control" id="wishes" name="wishes"
                      placeholder="Enter your wishes here"
                      rows="3"></textarea>
        </div>
        <div class="form-group">
            <label for="types">Select type:</label>
            <select class="custom-select" id="types" name="types">
                <c:forEach var="type" items="${typeList}">
                    <option value="${type}">${type}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Add application</button>
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
    jQuery('.datetimepicker').datetimepicker({
        format: 'd/m/y H:i'
    });
</script>
</html>