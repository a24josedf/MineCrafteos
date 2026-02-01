package minecrafteos.controller.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import minecrafteos.audio.Audio;
import minecrafteos.model.users.User;
import minecrafteos.model.users.Users;
import minecrafteos.view.users.SessionJDialog;

/**
 *
 * @author dam2_alu09@inf.ald
 */
public class SessionJDialogController {

    private final SessionJDialog view;
    private final Users model;

    public SessionJDialogController(SessionJDialog view, Users model) throws IOException, ClassNotFoundException {
        this.view = view;
        this.model = model;
        //manageLogInButton();
        this.view.addLogInButtonActionListener(this.getLogInButtonActionListener());
        this.view.addSingUpButtonActionListener(this.getSingUpButtonActionListener());
        this.view.addLogInMouseListener(this.addPressButtonMouseListener(this.view.getLoginButton()));
        this.view.addSignUpMouseListener(this.addPressButtonMouseListener(this.view.getSignupButton()));
    }

    private void verifyLogIn() throws IOException, ClassNotFoundException, SQLException {
        String username = view.getUsername().trim();
        String password = view.getPassword().toString().trim();
        //User u = new User(username, password);
        int state = 3;
        User u = model.getUser(username);
        if (u == null) {
            state = 2;
        }
        else{
            if (password.equals(u.getPassword().trim())) {
                model.setCurrentUser(model.getUser(username));
                state = 1;
            }
            switch (state) {
                case 1:
                    JOptionPane.showMessageDialog(view, "User loged in correctly");
                    view.dispose();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(view, "Incorrect password");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(view, "Username not found.");
                    clearView();
                    break;
            }
        }
            
        
            
    }

    private void verifySingUp() throws IOException, ClassNotFoundException, SQLException {
        String username = view.getUsername().trim();
        String password = view.getPassword().toString().trim();
        User u = new User(username, password);
        int state = 2;
        if (model.getUser(username)==null) {
            state = 1;
            model.addUser(u);
        }
        
        switch (state) {
            case 1:
                JOptionPane.showMessageDialog(view, "User singed up successfully, log in to get into the aplication");
                clearView();
                break;
            case 2:
                JOptionPane.showMessageDialog(view, "Username already exists");
                break;
        }
    }

    private void clearView() {
        view.setPassword("");
        view.setUsername("");
    }

    private boolean passwordTextFieldEmpty() {
        String password = view.getPassword();
        if (password.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean usernameTextFieldEmpty() {
        String username = view.getUsername();
        if (username.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private void manageLogInButton() throws IOException, ClassNotFoundException {
        File file = new File("users.ser");
        if (file.length() < 1) {
            view.enableDisableLogInButton(false);
        } else {
            view.enableDisableLogInButton(true);
        }
    }

    public ActionListener getLogInButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sound();
                if (usernameTextFieldEmpty()) {
                    JOptionPane.showMessageDialog(view, "Introduce an user");
                }
                if (passwordTextFieldEmpty()) {
                    JOptionPane.showMessageDialog(view, "Introduce a password");
                }
                if (!usernameTextFieldEmpty() && !passwordTextFieldEmpty()) {
                    try {
                        verifyLogIn();
                    } catch (IOException ex) {
                        Logger.getLogger(SessionJDialogController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SessionJDialogController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        System.getLogger(SessionJDialogController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                }
                try {
                    manageLogInButton();
                } catch (IOException ex) {
                    Logger.getLogger(SessionJDialogController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SessionJDialogController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        };
        return al;
    }

    public ActionListener getSingUpButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sound();
                if (usernameTextFieldEmpty()) {
                    JOptionPane.showMessageDialog(view, "Introduce an user");
                }
                if (passwordTextFieldEmpty()) {
                    JOptionPane.showMessageDialog(view, "Introduce a password");
                }
                if (!usernameTextFieldEmpty() && !passwordTextFieldEmpty()) {
                    try {
                        verifySingUp();
                    } catch (IOException ex) {
                        Logger.getLogger(SessionJDialogController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SessionJDialogController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        System.getLogger(SessionJDialogController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                }
                try {
                    manageLogInButton();
                } catch (IOException ex) {
                    Logger.getLogger(SessionJDialogController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SessionJDialogController.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    
    private void sound() {
        Audio player = new Audio();
        player.play("/audio/boton.wav");
        player.setVolume(0.9f);
    }
}
