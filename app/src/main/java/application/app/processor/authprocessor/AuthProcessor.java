package application.app.processor.authprocessor;

import application.app.entity.param.RegisterFormParam;
import application.app.entity.param.UserParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface AuthProcessor {

    String login(String resourceSpecific, UserParam param);

    void logout(String resourceSpecific, String authToken);

    ResponseEntity<String> register(String resourceSpecific, RegisterFormParam account) throws JsonProcessingException;
}
