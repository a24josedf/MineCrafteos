package minecrafteos.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import minecrafteos.model.livingBeing.LivingBeing;
import minecrafteos.model.object.ObjectM;
import minecrafteos.model.users.User;

public class DBManager {

    private static Connection connection;
    ResultSet result;

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
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public User getUser(String userName) throws SQLException {
        openConnection();
        String sql = "SELECT * FROM usuario WHERE nombre = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, userName);
        result = ps.executeQuery();
        User u = null;
        if (result.next()) {
            u = new User(result.getString("nombre"), result.getString("contrasenha"));
        }
        ps.close();
        closeConnection();
        return u;
    }

    public User getUser(int userID) throws SQLException {
        openConnection();
        String sql = "SELECT * FROM usuario WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, userID);
        result = ps.executeQuery();
        User u = null;
        if (result.next()) {
            u = new User(result.getString("nombre"), result.getString("contrasenha"));
        }
        ps.close();
        closeConnection();
        return u;
    }

    public void addUser(User u) throws SQLException {
        openConnection();
        String sql = "INSERT INTO usuario (nombre, contrasenha) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, u.getName());
        ps.setString(2, u.getPassword());
        ps.executeUpdate();
        ps.close();
        closeConnection();
    }

    public void removeUser(String name) throws SQLException {
        openConnection();
        String sql = "DELETE FROM usuario WHERE nombre = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.executeUpdate();
        ps.close();
        closeConnection();
    }

    public LivingBeing getLivingBeing(int lbID) throws SQLException {
        openConnection();
        String sql = "SELECT * FROM ser_vivo WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, lbID);
        result = ps.executeQuery();
        LivingBeing lb = null;
        if (result.next()) {
            lb = new LivingBeing(
                    result.getInt("ID"),
                    result.getString("nombre"),
                    result.getString("ubicacion"),
                    result.getString("tipo")
            );
        }
        ps.close();
        closeConnection();
        return lb;
    }

    public LivingBeing getHarvestLivingBeing(int oID) throws SQLException {
        openConnection();
        String sql = "SELECT sv.* FROM ser_vivo sv " +
                     "JOIN cosecha c ON sv.ID = c.ID_ser_vivo " +
                     "JOIN objeto o ON c.ID_objeto = o.ID " +
                     "WHERE o.ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, oID);
        result = ps.executeQuery();
        LivingBeing lb = null;
        if (result.next()) {
            lb = new LivingBeing(
                    result.getInt("ID"),
                    result.getString("nombre"),
                    result.getString("ubicacion"),
                    result.getString("tipo")
            );
        }
        ps.close();
        closeConnection();
        return lb;
    }

    public LivingBeing getKillLivingBeing(int oID) throws SQLException {
        openConnection();
        String sql = "SELECT sv.* FROM ser_vivo sv " +
                     "JOIN mata m ON sv.ID = m.ID_ser_vivo " +
                     "JOIN objeto o ON m.ID_objeto = o.ID " +
                     "WHERE o.ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, oID);
        result = ps.executeQuery();
        LivingBeing lb = null;
        if (result.next()) {
            lb = new LivingBeing(
                    result.getInt("ID"),
                    result.getString("nombre"),
                    result.getString("ubicacion"),
                    result.getString("tipo")
            );
        }
        ps.close();
        closeConnection();
        return lb;
    }

    public ArrayList<ObjectM> objects(int oID) throws SQLException {
        openConnection();

        String sql = "SELECT * FROM forma WHERE ID_objeto_crafteable = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, oID);
        ResultSet rs = ps.executeQuery();

        HashMap<Integer, ObjectM> objectsP = new HashMap<>();
        while (rs.next()) {
            int materialID = rs.getInt("ID_objeto");
            int pos = rs.getInt("posicion");

            ObjectM ob = getObject(materialID);
            objectsP.put(pos, ob);
        }

        rs.close();
        ps.close();
        closeConnection();

        ArrayList<ObjectM> objects = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            objects.add(objectsP.get(i));
        }

        return objects;
    }
    
    public ArrayList<ObjectM> getAllObjects() throws SQLException {
        ArrayList<ObjectM> objects = new ArrayList<>();

        openConnection();
        String sql = "SELECT * FROM objeto";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet r = ps.executeQuery()) {
            while (r.next()) {
                objects.add(buildObject(r));
            }
        }
        closeConnection();
        return objects;
    }

    public ObjectM getObject(String oName) throws SQLException {
        openConnection();
        String sql = "SELECT * FROM objeto WHERE nombre = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, oName);
        result = ps.executeQuery();
        ObjectM o = null;
        if (result.next()) {
            o = buildObject(result);
        }
        ps.close();
        closeConnection();
        return o;
    }

    public ObjectM getObject(int oID) throws SQLException {
        openConnection();
        String sql = "SELECT * FROM objeto WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, oID);
        result = ps.executeQuery();
        ObjectM o = null;
        if (result.next()) {
            o = buildObject(result);
        }
        ps.close();
        closeConnection();
        return o;
    }

    private ObjectM buildObject(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        int idResult = rs.getInt("ID_objeto");
        int userID = rs.getInt("ID_usuario");
        String name = rs.getString("nombre");
        String type = rs.getString("tipo");
        boolean picable = rs.getBoolean("picable");
        boolean harvest = rs.getBoolean("cosechable");
        boolean kill = rs.getBoolean("matable");
        boolean furnace = rs.getBoolean("horneable");
        boolean crafteable = rs.getBoolean("crafteable");
        String image = rs.getString("imagen");

        User user = getUser(userID);
        ObjectM o = new ObjectM(id, image, name, type, picable, harvest, kill, crafteable, furnace, user);

        if (idResult > 0) {
            ObjectM oo = getObject(idResult);
            o.setObtainingObject(oo);
        }

        if (harvest) {
            o.setObtainingLV(getHarvestLivingBeing(id));
        }
        if (kill) {
            o.setObtainingLV(getKillLivingBeing(id));
        }

        return o;
    }

}
