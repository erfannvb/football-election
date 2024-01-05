package nvb.dev.footballelection.controller;

import jakarta.persistence.EntityManager;
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
import org.hibernate.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "RegisterFilter", servletNames = "RegisterServlet", urlPatterns = "/register")
public class RegisterFilter implements Filter {

    EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    UserRepository userRepository = new UserRepositoryImpl(entityManager);
    UserService userService = new UserServiceImpl(entityManager, userRepository);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession httpSession = request.getSession();

        String username = request.getParameter("username");

        boolean usernameExists = false;

        List<User> userList = new ArrayList<>(userService.findAll());
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                usernameExists = true;
                break;
            }
        }

        if (usernameExists) {
            httpSession.setAttribute("error", "User with this username exists!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
            dispatcher.include(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
