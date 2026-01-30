package minecrafteos;

import java.net.URL;
import java.sql.SQLException;
import minecrafteos.controller.MainController;
import minecrafteos.model.users.User;
import minecrafteos.model.users.Users;
import minecrafteos.view.MainJFrame;
import minecrafteos.services.DBManager;

/**
 *
 * @author alumno
 */
public class MineCrafteos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        MainJFrame mainView = new MainJFrame();
        Users modelUsers = new Users();
        MainController mainController = new MainController(mainView, modelUsers);
        mainView.setVisible(true);
        DBManager db = new DBManager();
        System.out.println(db.getUser("steve"));
        User u = new User("carlos", "1");
        db.addUser(u);
        System.out.println(db.getUser("carlos"));
        db.removeUser("carlos");
        System.out.println(db.getUser("carlos"));
        System.out.println(db.getObject("roca"));
        
    }
    
}
