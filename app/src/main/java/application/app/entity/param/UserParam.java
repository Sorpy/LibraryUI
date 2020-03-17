package application.app.entity.param;

import application.app.entity.base.Persistent;
import org.springframework.stereotype.Component;

@Component
public class UserParam extends Persistent {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
