<%@ page import="nvb.dev.footballelection.entity.User" %>
<%@ page import="jakarta.persistence.EntityManager" %>
<%@ page import="nvb.dev.footballelection.base.repository.util.HibernateUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.persistence.Query" %>
<%@ page import="nvb.dev.footballelection.repository.UserRepository" %>
<%@ page import="nvb.dev.footballelection.repository.impl.UserRepositoryImpl" %>
<%@ page import="nvb.dev.footballelection.service.UserService" %>
<%@ page import="nvb.dev.footballelection.service.impl.UserServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
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

    <div class="mt-5">
        <jsp:include page="components/ok_message.jsp"/>
    </div>

    <div class="row mt-3">

        <%
            try {
                String team = currentUser.getVote().getTeam();
                if (team != null && team.equals("Esteghlal Tehran")) {
        %>

        <div class="col-md-12">
            <div class="card" id="esteghlal-card">
                <div class="card-body text-center">
                    <div class="card-title"><strong>Your vote</strong></div>
                    <div class="container mb-3">
                        <img src="img/logos/es.png" alt="Election" class="img-fluid rounded" style="max-width: 100px">
                    </div>
                    <div><%=team%>
                    </div>
                </div>
            </div>
        </div>

        <%
        } else if (team != null && team.equals("Sepahan Isfahan")) {
        %>

        <div class="col-md-12">
            <div class="card" id="sepahan-card">
                <div class="card-body text-center">
                    <div class="card-title"><strong>Your vote</strong></div>
                    <div class="container mb-3">
                        <img src="img/logos/se.png" alt="Election" class="img-fluid rounded" style="max-width: 100px">
                    </div>
                    <div><%=team%>
                    </div>
                </div>
            </div>
        </div>

        <%
        } else if (team != null && team.equals("Teraktorsazi Tabriz")) {
        %>

        <div class="col-md-12">
            <div class="card" id="teraktor-card">
                <div class="card-body text-center">
                    <div class="card-title"><strong>Your vote</strong></div>
                    <div class="container mb-3">
                        <img src="img/logos/tr.png" alt="Election" class="img-fluid rounded" style="max-width: 100px">
                    </div>
                    <div><%=team%>
                    </div>
                </div>
            </div>
        </div>

        <%
        } else if (team != null && team.equals("Persepolis Tehran")) {
        %>

        <div class="col-md-12">
            <div class="card" id="persepolis-card">
                <div class="card-body text-center">
                    <div class="card-title"><strong>Your vote</strong></div>
                    <div class="container mb-3">
                        <img src="img/logos/pe.png" alt="Election" class="img-fluid rounded" style="max-width: 100px">
                    </div>
                    <div><%=team%>
                    </div>
                </div>
            </div>
        </div>

        <%
                }
            } catch (NullPointerException e) {
                e.getStackTrace();
            }
        %>

    </div>

    <div class="row mt-3">

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

    <div class="row mt-3">

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
                    <input type="hidden" name="hasVotedInput" value="<%= currentUser.isHasVoted() %>">
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

                        <%
                            try {
                                User userVote = (User) session.getAttribute("userVote");
                                if (userVote != null && userVote.getVote().getId() != null && userVote.isHasVoted()) {
                        %>

                        <button type="button" class="btn btn-primary" id="voteBtn" disabled>Vote</button>
                        <div class="container mt-2">
                            <p style="font-size: 17px">You cannot vote more than once.</p>
                        </div>

                        <%
                        } else if (currentUser.isHasVoted()) {
                        %>

                        <button type="button" class="btn btn-primary" id="voteBtn" disabled>Vote</button>
                        <div class="container mt-2">
                            <p style="font-size: 17px">You cannot vote more than once.</p>
                        </div>

                        <%
                        } else {
                        %>

                        <button type="button" class="btn btn-primary" id="voteBtn">Vote</button>

                        <%
                            }
                        } catch (NullPointerException e) {
                        %>

                        <button type="button" class="btn btn-primary" id="voteBtn">Vote</button>

                        <%
                            }
                        %>

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
            let hasVotedInput = $('input:hidden[name="hasVotedInput"]').val();
            $.ajax({
                type: 'POST',
                data: {
                    team: team,
                    hasVotedInput: hasVotedInput
                },
                url: 'http://localhost:8080/vote',
                success: () => {
                    window.location = '/electionPage.jsp';
                },
                error: (err) => {
                    console.log(err);
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

                <%

                    EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

                    try {

                        entityManager.getTransaction().begin();

                        String hql = "select count(u.id), u.vote.team from User u" +
                                " where u.hasVoted = true group by u.vote.team";

                        Query query = entityManager.createQuery(hql);

                        List<Object[]> resultList = query.getResultList();
                        for (Object[] result : resultList) {
                            long voteCount = (long) result[0];
                            String teamName = (String) result[1];
                %>

                <p><strong><%= teamName %> : <%= voteCount %>
                </strong></p>

                <%
                        }

                    } catch (Exception e) {
                        e.getStackTrace();
                    } finally {
                        entityManager.close();
                    }
                %>

                <hr>

                <%

                    EntityManager voteEntityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

                    try {
                        voteEntityManager.getTransaction().begin();

                        String totalVotesHql = "select count(u.id) from User u where u.hasVoted = true";

                        Query query = voteEntityManager.createQuery(totalVotesHql);

                        long totalVotes = (long) query.getSingleResult();

                %>

                <p><strong>All Votes -> <%= totalVotes %>
                </strong></p>

                <%
                    } catch (Exception e) {
                        e.getStackTrace();
                    } finally {
                        voteEntityManager.close();
                    }

                %>

            </div>
            <div class="modal-footer">
                <div class="container text-center">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
