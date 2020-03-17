package application.app.controller;

import application.app.config.Credentials;
import application.app.entity.CustomUsernamePasswordAuthentication;
import application.app.entity.base.Persistent;
import application.app.entity.param.AccountParam;
import application.app.entity.result.AccountResult;
import application.app.processor.baseprocessor.BaseProcessor;
import application.app.processor.baseprocessor.BaseProcessorImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@Controller
public abstract class BaseCrudController<PK extends Serializable,IN extends Persistent,PROCESSOR extends BaseProcessor> {

    @Autowired
    private PROCESSOR processor;

    @GetMapping("/listAll")
    public String listAll(Model model, @RequestParam(name = "token")String token){
        String uri = getController()+"/listAll";
        try {
            model.addAttribute("token",token);
            model.addAttribute("entity",processor.getRequest(uri,token));
            return getReturnPoint();
        } catch (Exception e){
            return "index";
        }

    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @RequestParam(name = "token")String token,@PathVariable PK id) {
        String uri = getController()+"/findByPK/" + id;
        try {
            model.addAttribute("result",processor.getRequestById(uri,token));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        model.addAttribute("id",id);
        model.addAttribute("token",token);
        return getReturnPoint() +"-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@RequestParam(name = "token")String token,@ModelAttribute("result") IN param){
        String uri = getController()+"/update";
        try {
            processor.putRequest(uri,token,param);
            return "redirect:/index?token="+ token;
        } catch (Exception e) {
            return "index";
        }
    }

    @RequestMapping("/delete/{id}")
    public String delete(@RequestParam(name = "token")String token,@PathVariable PK id){
        String uri = getController()+"/delete/"+id;
        try {
            processor.deleteRequest(uri,token);
            return String.format("redirect:/%s/listAll?token=%s",getReturnPoint(),token);
        } catch (Exception e) {
            return "index";
        }
    }

    @GetMapping("/create")
    public String createForm(Model model,@RequestParam(name = "token") String token){
        Authentication authentication = Credentials.getAuthentication();
        CustomUsernamePasswordAuthentication customAuthentication = (CustomUsernamePasswordAuthentication) authentication;
        model.addAttribute("userId",customAuthentication.getId());
        model.addAttribute("statusId",1);
        model.addAttribute("typeId",1);
        model.addAttribute("token",token);
        model.addAttribute("entity",getMyType());
        return getReturnPoint()+"-create";
    }

    @PostMapping("/create")
    public String create(Model model,@RequestParam(name = "token") String token,@ModelAttribute("entity") IN param) {
        String uri = getController() + "/create";

        try {
            processor.postRequest(uri, token, param);
            return String.format("redirect:/%s/listAll?token=%s", getReturnPoint(), token);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "index";
        }
    }

    public abstract String getController();

    public abstract String getReturnPoint();

    public abstract Object getMyType();
}
