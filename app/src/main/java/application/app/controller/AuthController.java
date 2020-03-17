package application.app.controller;

import application.app.apiaccesobject.authaccessobject.AuthenticationAao;
import application.app.config.Credentials;
import application.app.entity.CustomUsernamePasswordAuthentication;
import application.app.entity.param.RegisterFormParam;
import application.app.entity.param.UserParam;
import application.app.processor.authprocessor.AuthProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AuthProcessor authProcessor;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("login", new UserParam());
        return "login";
    }


    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("login") UserParam user)
    {
        String uri = "/menu/login";
        try{

            String authToken = authProcessor.login(uri,user);
            String redirectURL = "redirect:/index?token=" + authToken;
            return redirectURL;
        }catch (Exception e) {
            return "login";
        }

    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("account",new RegisterFormParam());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("account")RegisterFormParam account){
        String uri = "/menu/register";
        try{
            authProcessor.register(uri,account);
            return "redirect:/login";
        }catch (Exception e){
            return "register";
        }

    }

    @RequestMapping("/menu/logout")
    public String logout(@RequestParam(name = "token")String token)
    {
        String uri = "/menu/logout";
        try{
            authProcessor.logout(uri,token);
            return "redirect:/menu/login";
        }catch (Exception e) {
            return "index";
        }
    }

    @RequestMapping("/index")
    public String menu(Model model,@RequestParam(name = "token")String token){
        model.addAttribute("token", token);
        Authentication authentication = Credentials.getAuthentication();
        CustomUsernamePasswordAuthentication customAuthentication = (CustomUsernamePasswordAuthentication) authentication;
        model.addAttribute("userId",customAuthentication.getId());
        return "index";
    }

}
