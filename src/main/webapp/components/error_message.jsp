<%
    String error = (String) session.getAttribute("error");
    if (error != null) {%>

<div class="alert alert-danger alert-dismissible fade show" role="alert">
    <strong><%= error %></strong>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>

<%
        session.removeAttribute("error");
    }
%>