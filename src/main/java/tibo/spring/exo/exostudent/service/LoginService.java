package tibo.spring.exo.exostudent.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import tibo.spring.exo.exostudent.dao.UserRepository;
import tibo.spring.exo.exostudent.entity.User;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    public LoginService(UserRepository userRepository, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.httpSession = httpSession;

        // add a default admin user
        userRepository.save(
                User.builder()
                        .username("admin")
                        .password("admin")
                        .build()
        );
    }

    public boolean logIn(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            httpSession.setAttribute("isLoggedIn", true);
            return true;
        }
        return false;
    }

    public boolean isLoggedIn() {
        try {
            return (boolean) httpSession.getAttribute("isLoggedIn");
        } catch (Exception e) {
            return false;
        }
    }

    public void logOut() {
        httpSession.removeAttribute("isLoggedIn");
    }
}
