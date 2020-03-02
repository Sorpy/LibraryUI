package application.app.apiaccesobject.authaccessobject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationImpl implements Authentication {

    @Autowired
    private RestTemplate restTemplate;

    public String login(String uri,HttpEntity<String> httpEntity){
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class).getBody();
    }

    @Override
    public String logout(String uri, HttpEntity<String> httpEntity) {
        return null;
    }

    @Override
    public String register(String uri, HttpEntity<String> httpEntity) {
        return null;
    }
}
