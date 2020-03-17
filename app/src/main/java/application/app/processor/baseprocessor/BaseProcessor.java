package application.app.processor.baseprocessor;

import application.app.entity.base.Persistent;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public interface BaseProcessor<IN extends Persistent,
                                OUT extends Persistent>{

    ResponseEntity<String> postRequest(String uri,String authToken,IN param) throws JsonProcessingException;

    ArrayList<OUT> getRequest(String uri, String authToken) throws JsonProcessingException;

    ResponseEntity<String> deleteRequest(String uri,String authToken);

    OUT getRequestById(String uri, String authToken) throws JsonProcessingException;

    ResponseEntity<String> putRequest(String uri,String authToken,IN param) throws JsonProcessingException;
}
