/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minecrafteos.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import minecrafteos.model.livingBeing.LivingBeing;
import minecrafteos.model.object.ObjectM;
import minecrafteos.model.users.User;

/**
 *
 * @author alumno
 */
public class DBManager {
    private static Connection connection;
    ResultSet result;
    public void start() throws SQLException {
        openConnection();
        closeConnection();
        
    }
    private void openConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/minecraft";
            connection = DriverManager.getConnection(url, "root", "abc123.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Excepcion: " + ex.getMessage());
        }

    }

    private void closeConnection() throws SQLException {
        connection.close();
    }
    
    public User getUser(String userName) throws SQLException{
        openConnection();
        String sql = "Select * FROM usuario" + " WHERE nombre = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, userName);
        result = ps.executeQuery();
        String name = "";
        String password = "";
        
        while (result.next()) {
            String search = result.getString(2).toLowerCase();
            if (search.equals(userName.toLowerCase())) {
                name = result.getString(2);
                password = result.getString(3);
            }
        }
        closeConnection();
        ps.close();
        if (!name.isEmpty()) {
             return new User(name, password);
        }else{
            return null;
        }
    }
    
    public void removeUser(String name) throws SQLException{
        openConnection();
        String sql = "DELETE FROM usuario WHERE nombre = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.executeUpdate();
        closeConnection();
        ps.close();
    }
    
    public void addUser(User u) throws SQLException{
        openConnection();
        String name = u.getName();
        String password = u.getPassword();
        String sql = "INSERT INTO usuario (nombre,contrasenha) VALUES (?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, password);
        ps.executeUpdate();
        closeConnection();
        ps.close();
    }
    
    public ArrayList<ObjectM> objects(int oID) throws SQLException{
        ArrayList<ObjectM> objects = new ArrayList<ObjectM>();
        openConnection();
        String sql = "Select * FROM forma JOIN objeto USING objeto.id = forma.ID_objeto_crafteable JOIN objeto using objeto.ID = forma.ID_objeto " + " WHERE forma.ID_objeto_crafteable = ? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, oID);
        result = ps.executeQuery();
        int coID;
        int pos;
        HashMap<Integer, ObjectM> objectsP = new HashMap<Integer, ObjectM>();
        while(result.next()){
            coID = result.getInt(2);
            pos = result.getInt(3);
            ObjectM ob = getObject(coID);
            objectsP.put(pos, ob);
            
        }
        for (int i = 1; i < 10; i++) {
            ObjectM e = objectsP.get(i);
            objects.add(e);
        }
        closeConnection();
        ps.close();
        return objects;
    }
    
    public ObjectM getObject(String oName) throws SQLException{
        openConnection();
        String sql = "Select * FROM objeto" + " WHERE nombre = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, oName);
        result = ps.executeQuery();
        int id = result.getInt(1);
        int oID  = result.getInt(2);
        ObjectM obtainingObject;
        int uID  = result.getInt(2);
        User user;
        String name = result.getString(3);
        String type = result.getString(4);
        boolean furnace = result.getBoolean(5);
        boolean crafteable = result.getBoolean(6);
        String image = result.getString(4);
        int lID  = result.getInt(2);
        LivingBeing obtainingLV;
        ArrayList<ObjectM> craftingItems;
        
        closeConnection();
        ps.close();
        if (!name.isEmpty()) {
            ObjectM o = new ObjectM(id, image, name, type, crafteable, furnace, user);
            o.set
            return o;
        }else{
            return null;
        }
    }
    
    
    public ObjectM getObject(int Id) throws SQLException{
        openConnection();
        String sql = "Select * FROM objeto" + " WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, Id);
        result = ps.executeQuery();
        int id = result.getInt(1);
        int oID  = result.getInt(2);
        ObjectM obtainingObject;
        int uID  = result.getInt(2);
        User user = getUser(uID);
        String name = result.getString(3);
        String type = result.getString(4);
        boolean furnace = result.getBoolean(5);
        boolean crafteable = result.getBoolean(6);
        String image = result.getString(7);
        int lID  = result.getInt(8);
        ObjectM o;
        
        
        if (!name.isEmpty()) {
            o = new ObjectM(id, image, name, type, crafteable, furnace, user);
            if (crafteable) {
                o.setCraftingItems(objects(id));
            }
            
        }
        closeConnection();
        ps.close();
        return o;
    }
    
}
