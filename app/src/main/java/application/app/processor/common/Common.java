package application.app.processor.common;

import org.springframework.http.HttpHeaders;

public class Common {

    public static HttpHeaders getHttpHeader(String authToken){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",authToken);
        headers.set("Content-Type","application/json");
        return  headers;
    }
}
