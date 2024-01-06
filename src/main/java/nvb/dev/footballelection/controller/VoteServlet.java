package nvb.dev.footballelection.controller;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import nvb.dev.footballelection.base.repository.util.HibernateUtil;
import nvb.dev.footballelection.entity.Vote;
import nvb.dev.footballelection.repository.VoteRepository;
import nvb.dev.footballelection.repository.impl.VoteRepositoryImpl;
import nvb.dev.footballelection.service.VoteService;
import nvb.dev.footballelection.service.impl.VoteServiceImpl;

import java.io.IOException;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {

    private final EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    private final VoteRepository voteRepository = new VoteRepositoryImpl(entityManager);
    private final VoteService voteService = new VoteServiceImpl(entityManager, voteRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String team = req.getParameter("team");

            Vote vote = new Vote(team);

            if (voteService.validate(vote)) {
                voteService.save(vote);
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("message",
                        "Your vote submitted successfully! Your vote was : " + team);
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
