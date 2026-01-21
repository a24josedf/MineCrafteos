package minecrafteos.controller.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import minecrafteos.audio.Audio;
import minecrafteos.model.object.Crafteable;
import minecrafteos.model.object.ObjectM;
import minecrafteos.view.search.SearchJDialog;

/**
 *
 * @author alumno
 */
public class SearchController {
    private SearchJDialog view;
    private ArrayList<Crafteable> itemsList;
    private ArrayList<ObjectM> normalItemsList;

    public SearchController(SearchJDialog view) {
        this.view = view;
        this.itemsList = new ArrayList<>();
        this.normalItemsList = new ArrayList<>();
        this.view.getSearchButtonActionListener(this.getSearchButtonActionListener());
        this.createObjects();
        System.out.println(itemsList.toString());
    }
    
    private void createObjects(){
        for(int i = 0; i < 10; i++){
            ObjectM item = new ObjectM("item" + i, "/img/itemDefault.png", "item" + i, "typeDefault");
            normalItemsList.add(item);
        }
        for (int i = 0; i < 5; i++){
            Crafteable item = new Crafteable("item" + i, "/img/itemDefault.png", "item" + i, "typeDefault", true);
            itemsList.add(item);
            for(int j = 0; j < 3; j++){
                item.getCraftingItems().add(normalItemsList.get(i));
            }
        }
        for (int i = 5; i < 10; i = i + 1){
            Crafteable item = new Crafteable("item" + i, "/img/itemDefault.png", "item" + i, "typeDefault", false);
            itemsList.add(item);
            for(int j = 0; j < 10; j++){
                item.getCraftingItems().add(normalItemsList.get(i));
            }
        }
    }
    
    private void searchBarConfig(){
        String input = view.getSearchTextField().getText();
        for(Crafteable item : itemsList){
            if(input.equals(item.getName())){
                view.isItemFurnace(item.isFurnace());
                view.setItemsCraftingButton(normalItemsList);
            }
        }
    }
    
    private ActionListener getSearchButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound();
                searchBarConfig();
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
