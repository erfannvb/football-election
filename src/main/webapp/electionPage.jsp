<%@ page import="nvb.dev.footballelection.entity.User" %>
<%

    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        session.setAttribute("error", "You are not logged-in! Login first.");
        response.sendRedirect("/login.jsp");
        return;
    }

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Election Page</title>
    <jsp:include page="components/common_css_js.jsp"/>
</head>
<body style="background-image: linear-gradient(to right, #7762ca, #6078d6, #4e8adb, #4b9bdb, #58a9d8);">

<jsp:include page="components/navbar.jsp"/>

<div class="container election">

    <div class="row mt-5">

        <div class="col-md-12">
            <div class="card">
                <div class="card-body text-center">
                    <div class="container mb-3">
                        <img src="img/election.png" alt="Election" class="img-fluid rounded" style="max-width: 100px">
                    </div>
                    <div>Open Election Form</div>
                </div>
            </div>
        </div>

    </div>

    <div class="row mt-5">

        <div class="col-md-12">
            <div class="card">
                <div class="card-body text-center">
                    <div class="container mb-3">
                        <img src="img/stats.png" alt="Election" class="img-fluid rounded" style="max-width: 100px">
                    </div>
                    <div>Statistics</div>
                </div>
            </div>
        </div>

    </div>

</div>


</body>
</html>
