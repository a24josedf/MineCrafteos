package minecrafteos.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import minecrafteos.audio.Audio;
import minecrafteos.controller.create.CreateObjectController;
import minecrafteos.controller.search.SearchController;
import minecrafteos.controller.users.SessionJDialogController;
import minecrafteos.model.users.Users;
import minecrafteos.view.MainJFrame;
import minecrafteos.view.create.CreateObjectJDialog;
import minecrafteos.view.filechooser.FileChooserJDialog;
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
        this.view.addCreateObjectButtonActionListener(this.addCreateButtonActionListener());
        this.view.addSearchButtonMouseListener(this.addPressButtonMouseListener(view.getSearchButton()));
        this.view.addCreateObjectButtonMouseListener(this.addPressButtonMouseListener(view.getCreateObjectButton()));
        this.view.addUserButtonMouseListener(this.addUserButtonMouseListener(view.getUserButton()));
    }
    
    private ActionListener addCreateButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sound();
                CreateObjectJDialog cjd = new CreateObjectJDialog(view, true);
                CreateObjectController cc =  new CreateObjectController(cjd,view);
                cjd.setVisible(true);
            }
        };
        return al;
    }
    
    private ActionListener addSearchButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sound();
                SearchJDialog sjd = new SearchJDialog(view, true);
                SearchController sc = new SearchController(sjd);
                sjd.setVisible(true);
            }
        };
        return al;
    }

    private ActionListener addUserButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sound();
                SessionJDialog sjd = new SessionJDialog(view, true);
                try {
                    SessionJDialogController sjdc = new SessionJDialogController(sjd, modelUser);
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                sjd.setVisible(true);
            }
        };
        return al;
    }
    
    private MouseListener addPressButtonMouseListener(JButton button){
        MouseListener ml = new MouseListener() {

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon icon = new ImageIcon(getClass().getResource("/img/buttonEnteredMC.png"));
                view.setBackgroundButtons(button, icon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon icon = new ImageIcon(getClass().getResource("/img/buttonMC.png"));
                view.setBackgroundButtons(button, icon);
            }

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                ImageIcon icon = new ImageIcon(getClass().getResource("/img/buttonPressedMC.png"));
                view.setBackgroundButtons(button, icon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ImageIcon icon = new ImageIcon(getClass().getResource("/img/buttonEnteredMC.png"));
                view.setBackgroundButtons(button, icon);
            }
        };
        return ml;
    }
    
    private MouseListener addUserButtonMouseListener(JButton button){
        MouseListener ml = new MouseListener() {

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.GRAY);
            }

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
        };
        return ml;
    }
    
    private void sound(){
        Audio player = new Audio();
        player.play("/audio/boton.wav");
        player.setVolume(0.9f);
    }
}