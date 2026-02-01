/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minecrafteos.controller.create;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
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
                view.dispose();
            }
        };
        return al;

    }

    private ActionListener getFileButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                imageView = new FileChooserJDialog(view, true);
                FileChooserController fcc = new FileChooserController(imageView, CreateObjectController.this);
                imageView.setVisible(true);
            }
        };
        return al;

    }

}
