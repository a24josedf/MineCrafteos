/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minecrafteos.controller.create;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import minecrafteos.audio.Audio;
import minecrafteos.controller.filechooser.FileChooserController;
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
                    //ObjectM item = new ObjectM();
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
    return new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            FileChooserJDialog fcjd = new FileChooserJDialog(parent, true);
            fcjd.setLocationRelativeTo(parent);
            fcjd.setVisible(true);

            File selectedFile = fcjd.getSelectedImageFile(); 

            if (selectedFile != null) {
                imageView = fcjd; 
                System.out.println("Archivo elegido: " + selectedFile.getAbsolutePath());
            } else {
                System.out.println("Selección cancelada");
            }
        }
    };
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
