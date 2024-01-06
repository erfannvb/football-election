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

    <jsp:include page="components/ok_message.jsp"/>

    <div class="row mt-5">

        <div class="col-md-12">
            <div class="card" data-bs-toggle="modal" data-bs-target="#electionModal">
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
            <div class="card" data-bs-toggle="modal" data-bs-target="#statsModal">
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

<!-- Election Modal -->
<div class="modal fade" id="electionModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5">Which team will be the champion of the Premier League ?</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <div class="form-check mb-4">
                            <label class="form-check-label" for="esteghlal">Esteghlal Tehran</label>
                            <input class="form-check-input" type="radio" name="team" id="esteghlal"
                                   value="Esteghlal Tehran">
                        </div>
                        <div class="form-check mb-4">
                            <label class="form-check-label" for="sepahan">Sepahan Isfahan</label>
                            <input class="form-check-input" type="radio" name="team" id="sepahan"
                                   value="Sepahan Isfahan">
                        </div>
                        <div class="form-check mb-4">
                            <label class="form-check-label" for="teraktor">Teraktorsazi Tabriz</label>
                            <input class="form-check-input" type="radio" name="team" id="teraktor"
                                   value="Teraktorsazi Tabriz">
                        </div>
                        <div class="form-check mb-4">
                            <label class="form-check-label" for="persepolis">Persepolis Tehran</label>
                            <input class="form-check-input" type="radio" name="team" id="persepolis"
                                   value="Persepolis Tehran">
                        </div>
                        <div class="form-check mb-4">
                            <label class="form-check-label" for="none">None</label>
                            <input class="form-check-input" type="radio" name="team" id="none"
                                   value="None">
                        </div>
                    </div>
                    <hr>
                    <div class="container text-center">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="voteBtn">Vote</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    $(document).ready(() => {
        $("#voteBtn").click(() => {
            let team = $('input:radio[name="team"]:checked').val();
            $.ajax({
                type: 'POST',
                data: {team: team},
                url: 'http://localhost:8080/vote',
                success: (data) => {
                    window.location = '/electionPage.jsp';
                }
            });
        });
    });

</script>

<!-- Election Modal -->
<div class="modal fade" id="statsModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5">Statistics</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
