package application.app.controller;

import application.app.config.Credentials;
import application.app.entity.CustomUsernamePasswordAuthentication;
import application.app.entity.param.AccountParam;
import application.app.processor.accountprocessor.AccountProcessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseCrudController<Long,AccountParam, AccountProcessor>{


    @Override
    public String getController() {
        return "/Account";
    }

    @Override
    public String getReturnPoint() {
        return "account";
    }

    @Override
    public Object getMyType() {
        return new AccountParam();
    }


}
