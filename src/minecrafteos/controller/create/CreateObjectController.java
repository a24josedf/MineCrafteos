/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minecrafteos.controller.create;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import minecrafteos.audio.Audio;
import minecrafteos.controller.filechooser.FileChooserController;
import minecrafteos.model.object.ObjectM;
import minecrafteos.model.users.User;
import minecrafteos.services.DBManager;
import minecrafteos.view.MainJFrame;
import minecrafteos.view.create.CreateObjectJDialog;
import minecrafteos.view.filechooser.FileChooserJDialog;

/**
 *
 * @author dam2_alu25@inf.ald
 */
public class CreateObjectController {

    private CreateObjectJDialog view;
    private MainJFrame parent;
    private FileChooserJDialog imageView;

    public CreateObjectController(CreateObjectJDialog view, MainJFrame parent) {
        this.view = view;
        this.parent = parent;
        this.view.addAddButtonActionListener(this.getAddButtonActionListener());
        this.view.addCancelButtonActionListener(this.getCancelButtonActionListener());
        this.view.addFileChooserActionListener(this.getFileButtonActionListener());
        this.view.addCancelButtonMouseListener(this.addPressButtonMouseListener(view.getCancelButton()));
        this.view.addAddButtonMouseListener(this.addPressButtonMouseListener(view.getAddButton()));
        this.view.addChooserButtonMouseListener(this.addPressButtonMouseListener(view.getFileChooserButton()));
    }

    private boolean nameTextFieldEmpty() {
        String name = view.getName();
        if (name.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean ImageFileChooserEmpty() {
        return imageView.getSelectedImageFile() == null;
    }

    private boolean TypeComboBoxEmpty() {
        return view.getTypeComboBoxItem() == null || view.getTypeComboBoxItem().isEmpty();
    }

    private boolean CraftOrMeltCheckBoxEmpty() {
        return !view.isCraftChecked() && !view.isMeltChecked();
    }

    private ActionListener getAddButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sound();

                if (nameTextFieldEmpty()) {
                    JOptionPane.showMessageDialog(view, "Introduce a name for the item");
                }
                if (ImageFileChooserEmpty()) {
                    JOptionPane.showMessageDialog(view, "Choose an image for the item");
                }
                if (TypeComboBoxEmpty()) {
                    JOptionPane.showMessageDialog(view, "Select a type for the item");
                }
                if (CraftOrMeltCheckBoxEmpty()) {
                    JOptionPane.showMessageDialog(view, "Check if the item is crafted or melted");
                }

                if (!nameTextFieldEmpty() && !ImageFileChooserEmpty() && !TypeComboBoxEmpty() && !CraftOrMeltCheckBoxEmpty()) {
                    System.out.println("Objeto Creado");

                    boolean block = true;
                    boolean harvest = false;
                    boolean kill = false;
                    boolean crafteable = view.isCraftChecked();
                    boolean furnace = view.isMeltChecked();

                    DBManager db = new DBManager();
                    User newUser = new User("nombre", "contrasenha");
                    try {
                        db.addUser(newUser);
                        System.out.println("Usuario insertado correctamente: " + newUser.getName());
                    } catch (SQLException ex) {
                        System.out.println("Error al insertar usuario: " + ex.getMessage());
                    }

                    User userWithId = null;
                    try {
                        userWithId = db.getUser(newUser.getName());
                        if (userWithId != null) {
                            System.out.println("Usuario recuperado correctamente: " + userWithId.getName());
                        } else {
                            System.out.println("No se pudo recuperar el usuario de la BD");
                        }
                    } catch (SQLException ex) {
                        System.out.println("Error al obtener usuario: " + ex.getMessage());
                    }

                    ObjectM item = new ObjectM(
                            0,
                            imageView.getSelectedImageFile().getAbsolutePath(),
                            view.getName(),
                            view.getTypeComboBoxItem(),
                            block,
                            harvest,
                            kill,
                            crafteable,
                            furnace,
                            userWithId
                    );

                    try {
                        db.addObject(item);
                        System.out.println("Objeto guardado correctamente en la BD");
                    } catch (SQLException ex) {
                        System.out.println("Error al crear objeto: " + ex.getMessage());
                    }

                    JOptionPane.showMessageDialog(view, "Objeto guardado correctamente");
                    view.dispose();
                }
            }
        };
        return al;
    }

    private ActionListener getCancelButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sound();
                view.dispose();
            }
        };
        return al;

    }

    private ActionListener getFileButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sound();
                imageView = new FileChooserJDialog(view, true);
                FileChooserController fcc = new FileChooserController(imageView, CreateObjectController.this);
                imageView.setVisible(true);
            }
        };
        return al;

    }

    private MouseListener addPressButtonMouseListener(JButton button) {
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
            public void mouseClicked(MouseEvent e) {
            }

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
