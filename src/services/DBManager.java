/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
}
