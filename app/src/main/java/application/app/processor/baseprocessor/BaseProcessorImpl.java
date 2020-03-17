package application.app.processor.baseprocessor;

import application.app.apiaccesobject.baseapiaccessobject.BaseApiAccessObject;
import application.app.entity.base.Persistent;
import application.app.entity.base.PersistentNamed;
import application.app.entity.result.AccountResult;
import application.app.processor.common.Common;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public abstract class BaseProcessorImpl<IN extends Persistent,OUT extends Persistent, AAO extends BaseApiAccessObject>
        implements BaseProcessor<IN,OUT> {


    @Value("${mainURL}")
    private String resource;

    @Autowired
    private AAO apiAccess;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public ResponseEntity<String> postRequest(String resourceSpecific, String authToken, IN param) throws JsonProcessingException {
        String uri = resource+resourceSpecific;
        String requestBody = mapper.writeValueAsString(param);
        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, Common.getHttpHeader(authToken));
        return apiAccess.postRequest(uri,httpEntity);
    }

    @Override
    public ArrayList<OUT> getRequest(String resourceSpecific, String authToken) throws JsonProcessingException {
        ArrayList<OUT> results;
        String uri = resource+resourceSpecific;
        HttpEntity<String> httpEntity = new HttpEntity<>("", Common.getHttpHeader(authToken));

           results = mapper.readValue(apiAccess.getRequest(uri,httpEntity),ArrayList.class);
           return results;
    }

    @Override
    public OUT getRequestById(String resourceSpecific, String authToken) throws JsonProcessingException {
        String uri = resource+resourceSpecific;
        HttpEntity<String> httpEntity = new HttpEntity<>("", Common.getHttpHeader(authToken));
        return mapper.readValue(apiAccess.getRequest(uri,httpEntity),getMyType());
    }

    @Override
    public ResponseEntity<String> deleteRequest(String resourceSpecific, String authToken) {
        String uri = resource+resourceSpecific;
        HttpEntity<String> httpEntity = new HttpEntity<>("", Common.getHttpHeader(authToken));
        return apiAccess.deleteRequest(uri,httpEntity);
    }

    @Override
    public ResponseEntity<String> putRequest(String resourceSpecific, String authToken, IN param) throws JsonProcessingException {
        String uri = resource+resourceSpecific + "/"+ param.getId();
        String requestBody = mapper.writeValueAsString(param);

        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, Common.getHttpHeader(authToken));
        return apiAccess.putRequest(uri,httpEntity);
    }

    public abstract Class<OUT> getMyType();
}
