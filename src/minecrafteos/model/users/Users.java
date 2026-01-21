package minecrafteos.model.users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<User> users;
    private User currentUser;
    private final File file = new File("users.ser");

    public Users() {
        this.users = deserializedList();  
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User u) throws IOException {
        users.add(u);
        serializeList();
    }

    public void removeUser(User u) throws IOException {
        users.remove(u);
        serializeList();
    }

    public User getUser(String name) {
        for (User u : users) {
            if (u.getName().equals(name)) {
                return u;
            }
        }
        return null;
    }
    
    public void serializeList() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(users);
        }
    }

    public ArrayList<User> deserializedList() {
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<User>) ois.readObject();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();   
        }
    }  
    
}
