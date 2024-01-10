<%@ page import="nvb.dev.footballelection.entity.User" %>
<%@ page import="jakarta.persistence.EntityManager" %>
<%@ page import="nvb.dev.footballelection.base.repository.util.HibernateUtil" %>
<%@ page import="nvb.dev.footballelection.repository.UserRepository" %>
<%@ page import="nvb.dev.footballelection.repository.impl.UserRepositoryImpl" %>
<%@ page import="nvb.dev.footballelection.service.UserService" %>
<%@ page import="nvb.dev.footballelection.service.impl.UserServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Vote Table</title>
    <jsp:include page="components/common_css_js.jsp"/>
</head>
<body style="background-image: linear-gradient(to right, #7762ca, #6078d6, #4e8adb, #4b9bdb, #58a9d8);">

<jsp:include page="components/navbar.jsp"/>

<%
    try {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        UserRepository userRepository = new UserRepositoryImpl(entityManager);
        UserService userService = new UserServiceImpl(entityManager, userRepository);

        List<User> userList = new ArrayList<>(userService.findAll());

        request.setAttribute("userList", userList);

    } catch (Exception e) {
        e.getStackTrace();
    }
%>

<div class="container w-75 mt-5 p-3">
    <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover">
            <caption class="text-white">Vote Table</caption>
            <tr>
                <th style="text-align: center">First Name</th>
                <th style="text-align: center">Last Name</th>
                <th style="text-align: center">Vote</th>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td style="text-align: center"><c:out value="${user.firstName}"/></td>
                    <td style="text-align: center"><c:out value="${user.lastName}"/></td>
                    <td style="text-align: center"><c:out value="${user.vote.team}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
