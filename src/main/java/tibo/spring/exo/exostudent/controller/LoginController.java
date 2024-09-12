package tibo.spring.exo.exostudent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tibo.spring.exo.exostudent.service.LoginService;

@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (loginService.logIn(username, password)) {
            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        loginService.logOut();
        return "redirect:/";
    }
}
