package application.app.processor.authprocessor;

import application.app.apiaccesobject.authaccessobject.AuthenticationAao;
import application.app.entity.param.AccountParam;
import application.app.entity.param.RegisterFormParam;
import application.app.entity.param.UserParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.reflect.FieldUtils.*;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthProcessorImpl implements AuthProcessor {

    @Autowired
    private AuthenticationAao authenticationAao;

    private ObjectMapper mapper = new ObjectMapper();

    @Value("${mainURL}")
    private String resource;

    public String convertBase64(String username, String password){
        String originalInput = username+":"+password;
        return Base64.getEncoder().encodeToString(originalInput.getBytes());
    }

    @Override
    public String login(String resourceSpecific, UserParam param) {
        String uri = resource+resourceSpecific;
        HttpHeaders headers = new HttpHeaders();

        headers.setBasicAuth(param.getUsername(),param.getPassword());
        HttpEntity<String> httpEntity = new HttpEntity<>("",headers);
        return authenticationAao.login(uri,httpEntity);
    }

    @Override
    public void logout(String resourceSpecific, String authToken) {
        String uri = resource+resourceSpecific;
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization",authToken);
        HttpEntity<String> httpEntity = new HttpEntity<>("",headers);
        authenticationAao.logout(uri,httpEntity);
    }

    @Override
    public ResponseEntity<String> register(String resourceSpecific, RegisterFormParam account) throws JsonProcessingException {
        String uri = resource+resourceSpecific;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Registration",convertBase64(account.getUsername(),account.getPassword()));
        headers.set("Content-Type","application/json");
        String jsonInString = mapper.writeValueAsString(convertAccount(account,new AccountParam()));
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonInString,headers);
        return authenticationAao.register(uri,httpEntity);
    }

    private AccountParam convertAccount(RegisterFormParam entity, AccountParam result) {
        Map<String, Field> entityInfo = new HashMap<>();
        for (Field field : getAllFieldsList(entity.getClass())) {
            field.setAccessible(true);
            entityInfo.put(field.getName(), field);
        }
        Map<String, Field> resultInfo = new HashMap<>();
        for (Field field : getAllFieldsList(result.getClass())) {
            field.setAccessible(true);
            resultInfo.put(field.getName(), field);
        }

        entityInfo.forEach((key,value)-> {
            try {

                if (resultInfo.containsKey(key)) {
                    writeField(result, key,value.get(entity),true);
                }
            } catch (IllegalAccessException e) {
                System.out.println(e);
            }
        });
        return result;
    }
}
