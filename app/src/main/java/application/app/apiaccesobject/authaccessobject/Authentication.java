package application.app.apiaccesobject.authaccessobject;

import org.springframework.http.HttpEntity;

public interface Authentication {

    String login(String uri, HttpEntity<String> httpEntity);

    String logout(String uri, HttpEntity<String> httpEntity);

    String register(String uri, HttpEntity<String> httpEntity);
}
