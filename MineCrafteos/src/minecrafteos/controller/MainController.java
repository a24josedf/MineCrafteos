package minecrafteos.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import minecrafteos.controller.create.CreateController;
import minecrafteos.controller.search.SearchController;
import minecrafteos.controller.users.SessionJDialogController;
import minecrafteos.model.users.Users;
import minecrafteos.view.MainJFrame;
import minecrafteos.view.create.CreateJDialog;
import minecrafteos.view.search.SearchJDialog;
import minecrafteos.view.users.SessionJDialog;

/**
 *
 * @author dam2_alu04@inf.ald
 */
public class MainController {
    private MainJFrame view;
    private Users modelUser;

    public MainController(MainJFrame view, Users modelUser) {
        this.view = view;
        this.modelUser = modelUser;
        this.view.addUserButtonActionListener(this.addUserButtonActionListener());
        this.view.addSearchButtonActionListener(this.addSearchButtonActionListener());
        this.view.addCreateObjectButtonActionListener(this.addCreateObjectButtonActionListener());
    }
    
    private ActionListener addSearchButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                 SearchJDialog sjd = new SearchJDialog(view, true);
                 SearchController sc =  new SearchController(sjd);
                 sjd.setVisible(true);
            }
        };
        return al;
    
    }

    private ActionListener addUserButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                    SessionJDialog sjd = new SessionJDialog(view, true);
                try {
                    SessionJDialogController sjdc = new SessionJDialogController(sjd,modelUser);
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    sjd.setVisible(true);
            }
        };
        return al;
    
    }
    
     private ActionListener addCreateObjectButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                 CreateJDialog cjd = new CreateJDialog(view, true);
                 CreateController cc =  new CreateController(cjd);
                 cjd.setVisible(true);
            }
        };
        return al;
    
    }
}
