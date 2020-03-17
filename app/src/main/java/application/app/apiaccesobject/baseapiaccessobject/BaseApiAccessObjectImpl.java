package application.app.apiaccesobject.baseapiaccessobject;

import application.app.entity.base.Persistent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Component
public class BaseApiAccessObjectImpl implements BaseApiAccessObject {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> postRequest(String uri, HttpEntity<String> httpEntity) {
        return restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
    }

    @Override
    public String getRequest(String uri, HttpEntity<String> httpEntity) {
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class).getBody();
    }

    @Override
    public ResponseEntity<String> deleteRequest(String uri, HttpEntity<String> httpEntity) {
        return restTemplate.exchange(uri, HttpMethod.DELETE, httpEntity, String.class);
    }

    @Override
    public ResponseEntity<String> putRequest(String uri, HttpEntity<String> httpEntity) {
        return restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, String.class);
    }
}
