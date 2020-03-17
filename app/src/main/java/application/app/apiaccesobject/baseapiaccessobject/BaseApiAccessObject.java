package application.app.apiaccesobject.baseapiaccessobject;

import application.app.entity.base.Persistent;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public interface BaseApiAccessObject{
    ResponseEntity<String> postRequest(String uri, HttpEntity<String> httpEntity);

    String getRequest(String uri, HttpEntity<String> httpEntity);

    ResponseEntity<String> deleteRequest(String uri, HttpEntity<String> httpEntity);

    ResponseEntity<String> putRequest(String uri, HttpEntity<String> httpEntity);
}
