package application.app.entity.result;

import application.app.entity.base.PersistentNamed;
import org.springframework.stereotype.Component;

@Component
public class ApiSessionResult extends PersistentNamed {

    private Long userId;

    private String authToken;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
