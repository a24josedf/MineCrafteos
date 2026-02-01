package minecrafteos.controller.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import minecrafteos.audio.Audio;
import minecrafteos.model.object.ObjectM;
import minecrafteos.services.DBManager;
import minecrafteos.view.search.SearchJDialog;

/**
 *
 * @author alumno
 */
public class SearchController {
    private SearchJDialog view;
    private DBManager db;
    private ArrayList<ObjectM> madeInObjectsList = new ArrayList<>();
    private ArrayList<ObjectM> craftItemsList = new ArrayList<>();
    private ArrayList<ObjectM> furnaceItemsList = new ArrayList<>();

    public SearchController(SearchJDialog view) {
        this.view = view;
        this.db = new DBManager();
        this.view.getSearchButtonActionListener(this.getSearchButtonActionListener());
        this.filterItems();
    }
    
    private void filterItems() {
        try {
            for(ObjectM object : db.getAllObjects()){
                if(object.isCrafteable() || object.isFurnace()){
                    madeInObjectsList.add(object);
                }
            }
        } catch (SQLException ex) {
            System.getLogger(SearchController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    private void searchBarConfig() throws SQLException{
        String input = view.getSearchTextField().getText();
        for(ObjectM object : madeInObjectsList){
            if(input.equals(object.getName()) || input.equals(object.getName().toLowerCase())){
                isItemFurnace(object, object.getImage());
            }
        }
    }
    
    private void isItemFurnace(ObjectM object, String imageCraft) throws SQLException{
        if(!object.isFurnace()){
            this.craftItemsList = db.objects(object.getId());
            
            view.showCraftingLayeredPane(true);
            view.showFurnaceLayeredPane(false);
            view.setItemsCraftingButton(craftItemsList, imageCraft);
        } else {
            this.furnaceItemsList = db.objects(object.getId());
            
            view.showFurnaceLayeredPane(true);
            view.showCraftingLayeredPane(false);
            view.setItemsFurnaceButton(furnaceItemsList, imageCraft);
        }
    }
    
    private ActionListener getSearchButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound();
                try {
                    searchBarConfig();
                } catch (SQLException ex) {
                    System.getLogger(SearchController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        };
        return al;
    }
    
    private void sound(){
        Audio player = new Audio();
        player.play("/audio/boton.wav");
        player.setVolume(0.9f);
    }
}
