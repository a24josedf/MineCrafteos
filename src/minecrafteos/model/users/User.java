package minecrafteos.model.users;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author dam2_alu04@inf.ald
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
