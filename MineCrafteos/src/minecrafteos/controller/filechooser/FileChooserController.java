/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minecrafteos.controller.filechooser;

import minecrafteos.controller.create.CreateController;
import minecrafteos.view.filechooser.FileChooserJDialog;

/**
 *
 * @author invitado
 */
public class FileChooserController {
    
    private FileChooserJDialog view;
    private CreateController parentController;

    public FileChooserController(FileChooserJDialog view, CreateController parentController) {
        this.view = view;
        this.parentController = parentController;
    }
    
}
