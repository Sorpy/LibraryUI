package application.app.controller;

import application.app.entity.LoginEntity;
import application.app.apiaccesobject.AccountStatusProcessor;
import application.app.apiaccesobject.authaccessobject.AuthenticationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationImpl loginProcessor;

    @Autowired
    private AccountStatusProcessor accountStatusProcessor;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("login", new LoginEntity());
        return "login";
    }


    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("login") LoginEntity login)
    {
        final String uri = "http://localhost:8080/menu/login";
        HttpHeaders headers = new HttpHeaders();

        headers.setBasicAuth(login.getUsername(),login.getPassword());
        HttpEntity<String> httpEntity = new HttpEntity<>("params",headers);
        try{
            String authToken = loginProcessor.login(uri,httpEntity);
            return "redirect:/index?token=" + authToken;
        }catch (Exception e) {
        }
        return "login";
    }

    @RequestMapping("/index")
    public String menu(Model model,@RequestParam(name = "token")String token){
        model.addAttribute("token", token);
        return "index";
    }

    @RequestMapping("/test")
    public String test(Model model,@RequestParam(name = "token") String token){
        final String uri = "http://localhost:8080/AccountStatus/listAll";
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization",token);
        HttpEntity<String> httpEntity = new HttpEntity<>("params",headers);
        String statuses = accountStatusProcessor.listAll(uri,httpEntity);
        model.addAttribute("statuses",statuses);
        return "test";
    }
}
