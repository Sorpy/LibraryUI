package application.app.apiaccesobject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class AccountStatusProcessor {

    @Autowired
    private RestTemplate restTemplate;

    public String listAll(String uri, HttpEntity<String> httpEntity) {
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class).getBody();
    }
}
