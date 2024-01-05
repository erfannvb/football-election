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
import nvb.dev.footballelection.repository.UserRepository;
import nvb.dev.footballelection.repository.impl.UserRepositoryImpl;
import nvb.dev.footballelection.service.UserService;
import nvb.dev.footballelection.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    UserRepository userRepository = new UserRepositoryImpl(entityManager);
    UserService userService = new UserServiceImpl(entityManager, userRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            HttpSession httpSession = req.getSession();

            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            User user = new User(firstName, lastName, username, password);
            if (userService.validate(user)) {
                userService.save(user);
                httpSession.setAttribute("message", username + " registered successfully!");
                resp.sendRedirect("/register.jsp");
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
