package nvb.dev.footballelection.controller;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import nvb.dev.footballelection.base.repository.util.HibernateUtil;
import nvb.dev.footballelection.entity.User;
import nvb.dev.footballelection.entity.Vote;
import nvb.dev.footballelection.repository.UserRepository;
import nvb.dev.footballelection.repository.VoteRepository;
import nvb.dev.footballelection.repository.impl.UserRepositoryImpl;
import nvb.dev.footballelection.repository.impl.VoteRepositoryImpl;
import nvb.dev.footballelection.service.UserService;
import nvb.dev.footballelection.service.VoteService;
import nvb.dev.footballelection.service.impl.UserServiceImpl;
import nvb.dev.footballelection.service.impl.VoteServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {

    private static final String ERROR = "error";

    private final EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

    private final VoteRepository voteRepository = new VoteRepositoryImpl(entityManager);
    private final VoteService voteService = new VoteServiceImpl(entityManager, voteRepository);

    private final UserRepository userRepository = new UserRepositoryImpl(entityManager);
    private final UserService userService = new UserServiceImpl(entityManager, userRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession httpSession = req.getSession();
            User currentUser = (User) httpSession.getAttribute("currentUser");

            String team = req.getParameter("team");
            String hasVotedInput = req.getParameter("hasVotedInput");

            Vote vote = new Vote(team);

            if (voteService.validate(vote)) {

                currentUser.setHasVoted(true);
                currentUser.setVote(vote);

                voteService.save(vote);
                userService.update(currentUser);

                httpSession.setAttribute("message",
                        "Your vote submitted successfully! Your vote was : " + team);

                Optional<User> userByVoteId = userService.findUserByVoteId(currentUser.getId());
                if (userByVoteId.isPresent()) {
                    User userVote = userByVoteId.get();
                    httpSession.setAttribute("userVote", userVote);
                    httpSession.setAttribute("hasVotedInput", hasVotedInput);
                } else {
                    httpSession.setAttribute(ERROR, "Vote validation failed.");
                }

            }

        } catch (Exception e) {
            e.getStackTrace();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute(ERROR, "An error occurred during the voting process.");
        }
    }
}
