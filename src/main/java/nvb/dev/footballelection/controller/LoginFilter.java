package nvb.dev.footballelection.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
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
import java.util.Optional;

@WebFilter(filterName = "LoginFilter", servletNames = "LoginServlet", urlPatterns = "/login")
public class LoginFilter implements Filter {

    EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    UserRepository userRepository = new UserRepositoryImpl(entityManager);
    UserService userService = new UserServiceImpl(entityManager, userRepository);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession httpSession = request.getSession();

        try {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Optional<User> userOptional = userService.findUserByUsernameAndPassword(username, password);
            if (userOptional.isPresent()) {
                User currentUser = userOptional.get();
                httpSession.setAttribute("currentUser", currentUser);
                filterChain.doFilter(request, response);
            }

        } catch (NoResultException e) {
            httpSession.setAttribute("error", "Invalid Username/Password. Please Try Again!");
            response.sendRedirect("/login.jsp");
        }

    }
}
