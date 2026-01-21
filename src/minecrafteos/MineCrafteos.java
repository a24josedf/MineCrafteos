package minecrafteos;

import java.net.URL;
import minecrafteos.audio.Audio;
import minecrafteos.controller.MainController;
import minecrafteos.model.users.Users;
import minecrafteos.view.MainJFrame;

/**
 *
 * @author alumno
 */
public class MineCrafteos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainJFrame mainView = new MainJFrame();
        Users modelUsers = new Users();
        MainController mainController = new MainController(mainView, modelUsers);
        backgroundMusic();
        mainView.setVisible(true);
        
    }
    
    public static void backgroundMusic(){
        Audio player = new Audio();
        player.playLoop("/audio/musica.wav");
    }
}
