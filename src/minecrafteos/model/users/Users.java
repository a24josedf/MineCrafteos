package minecrafteos.model.users;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import minecrafteos.services.DBManager;

public class Users implements Serializable {
    private DBManager dbm;
    private User currentUser;

    public Users() {
        dbm = new DBManager();
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void addUser(User u) throws IOException, SQLException {
       dbm.addUser(u);
    }

    public void removeUser(User u) throws IOException, SQLException {
        String name = u.getName();
       dbm.removeUser(name);
    }

    public User getUser(String name) throws SQLException {
        return dbm.getUser(name);
    }
    
    public void serializeList() throws IOException {
       
    }

    public ArrayList<User> deserializedList() {
        
        return null;
        
    }  
    
}
