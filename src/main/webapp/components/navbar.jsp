<%@ page import="nvb.dev.footballelection.entity.User" %>
<%
    User currentUser = (User) session.getAttribute("currentUser");
%>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/index.jsp"><span style="color: blue">Football</span>Election</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/index.jsp">Home</a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">

                <%
                    if (currentUser == null) {%>

                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/register.jsp">Register</a>
                </li>

                <%
                } else {
                %>

                <li class="nav-item">
                    <a class="nav-link" aria-current="page"
                       href="/electionPage.jsp">
                        <%= currentUser.getUsername() %>
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="logout">Logout</a>
                </li>

                <% } %>

            </ul>
        </div>
    </div>
</nav>