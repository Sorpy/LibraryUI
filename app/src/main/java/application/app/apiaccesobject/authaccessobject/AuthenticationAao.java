package application.app.apiaccesobject.authaccessobject;

import application.app.entity.param.AccountParam;
import application.app.entity.param.RegisterFormParam;
import application.app.entity.param.UserParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public interface AuthenticationAao {

    String login(String resourceSpecific, HttpEntity<String> httpEntity);

    void logout(String resourceSpecific, HttpEntity<String> httpEntity);

    ResponseEntity<String> register(String resourceSpecific, HttpEntity<String> httpEntity) throws JsonProcessingException;
}
