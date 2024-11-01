package mk.ukim.finki.aud.web.controller;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.aud.model.User;
import mk.ukim.finki.aud.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.aud.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    //@RequestMapping(method = RequestMethod.GET, value = "")
    @GetMapping
    public String getLoginPage(){
        return "login";
    }
    @PostMapping
    public String login(HttpServletRequest request, Model model){

        User user = null;
        try{
            user = this.authService.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", user);

            return "redirect:/home";
        }
        catch (InvalidUserCredentialsException exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }
}
