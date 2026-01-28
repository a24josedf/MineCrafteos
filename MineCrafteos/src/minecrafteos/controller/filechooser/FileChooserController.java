/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minecrafteos.controller.filechooser;

import minecrafteos.controller.create.CreateObjectController;
import minecrafteos.view.filechooser.FileChooserJDialog;

/**
 *
 * @author invitado
 */
public class FileChooserController {
    
    private FileChooserJDialog view;
    private CreateObjectController parentController;

    public FileChooserController(FileChooserJDialog view, CreateObjectController parentController) {
        this.view = view;
        this.parentController = parentController;
    }
    
}
