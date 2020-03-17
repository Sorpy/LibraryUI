package application.app.config;

import application.app.entity.result.ApiSessionResult;
import application.app.entity.CustomUsernamePasswordAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (authentication instanceof  CustomUsernamePasswordAuthentication) {

            CustomUsernamePasswordAuthentication authToken = (CustomUsernamePasswordAuthentication) authentication;

            if (authToken != null) {
                if (authToken.getAuthToken() != null) {

                    RestTemplate restTemplate = new RestTemplate();

                    String urlBasic = "http://localhost:8081/ApiSession/getSession?token=";
                    String url = urlBasic + authToken.getAuthToken();

                    HttpHeaders headers = new HttpHeaders();
                    headers.set("Authorization", authToken.getAuthToken());
                    HttpEntity<String> request = new HttpEntity<String>(headers);

                    ResponseEntity<ApiSessionResult> response = restTemplate.exchange(url, HttpMethod.GET, request, ApiSessionResult.class);
                    if (response.getStatusCodeValue() == 200) {
                        ApiSessionResult apiSessionResult = response.getBody();


                        assert apiSessionResult != null;
                        CustomUsernamePasswordAuthentication auth = new CustomUsernamePasswordAuthentication("", "",
                                authToken.getAuthToken(), apiSessionResult.getUserId());

                        SecurityContextHolder.getContext().setAuthentication(auth);
                        return auth;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
