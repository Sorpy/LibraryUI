package application.app.apiaccesobject.authaccessobject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationAaoImpl implements AuthenticationAao {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String login(String uri, HttpEntity<String> httpEntity) {
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class).getBody();
    }

    @Override
    public void logout(String uri, HttpEntity<String> httpEntity) {
        restTemplate.exchange(uri, HttpMethod.DELETE, httpEntity, String.class);
    }

    @Override
    public ResponseEntity<String> register(String uri, HttpEntity<String> httpEntity) {
        return restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
    }
}
