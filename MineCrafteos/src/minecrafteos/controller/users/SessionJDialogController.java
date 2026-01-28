package minecrafteos.controller.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
        manageLogInButton();
        this.view.addLogInButtonActionListener(this.getLogInButtonActionListener());
        this.view.addSingUpButtonActionListener(this.getSingUpButtonActionListener());
    }

    private void verifyLogIn() throws IOException, ClassNotFoundException {
        File file = new File("users.ser");
        String username = view.getUsername().trim();
        String password = view.getPassword().toString().trim();
        //User u = new User(username, password);
        int state = 3;
        for (User us : model.deserializedList()) {
            if (username.equals(us.getName().trim())) {
                if (password.equals(us.getPassword().trim())) {
                    model.setCurrentUser(model.getUser(username));
                    state = 1;
                } else {
                    state = 2;
                }
            }
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

    private void verifySingUp() throws IOException, ClassNotFoundException {
        File file = new File("users.ser");
        String username = view.getUsername().trim();
        String password = view.getPassword().toString().trim();
        User u = new User(username, password);
        int state = 3;
        if (file.length() < 1) {
            model.addUser(u);
            state = 1;
        } else {
            for (User us : model.deserializedList()) {
                if (username.equals(us.getName().trim())) {
                    state = 2;
                } else {
                    model.addUser(u);
                }
            }
        }
        switch (state) {
            case 1:
                JOptionPane.showMessageDialog(view, "User singed up successfully, log in to get into the aplication");
                clearView();
                break;
            case 2:
                JOptionPane.showMessageDialog(view, "Username already exists");
                break;
            case 3:
                JOptionPane.showMessageDialog(view, "User singed up successfully, log in to get into the aplication");
                clearView();
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
}
