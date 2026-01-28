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
    private ArrayList<ObjectM> craftItemsList;
    private ArrayList<ObjectM> furnaceItemsList;

    public SearchController(SearchJDialog view) {
        this.view = view;
        this.itemsList = new ArrayList<>();
        this.craftItemsList = new ArrayList<>();
        this.furnaceItemsList = new ArrayList<>();
        this.view.getSearchButtonActionListener(this.getSearchButtonActionListener());
        this.createObjects();
    }
    
    private void createObjects(){
        /*
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
        */
        for(int i = 0; i < 3; i++){
            ObjectM item = new ObjectM("item" + i, "/img/itemDefault.png", "item" + i, "typeDefault");
            craftItemsList.add(item);
        }
        ObjectM empty = new ObjectM("empty", "/img/itemEmpty.png", "empty", "typeDefault");
        craftItemsList.add(empty);
        ObjectM item2 = new ObjectM("item2", "/img/itemDefault.png", "item2", "typeDefault");
        craftItemsList.add(item2);
        craftItemsList.add(empty);
        craftItemsList.add(empty);
        craftItemsList.add(item2);
        craftItemsList.add(empty);
        Crafteable item = new Crafteable("craft", "/img/itemDefaultCraft.png", "craft", "typeDefault", false);
        itemsList.add(item);
        for(ObjectM normalItem : craftItemsList){
            item.getCraftingItems().add(normalItem);
        }
        
        for(int i = 0; i < 2; i++){
            ObjectM furn = new ObjectM("furn" + i, "/img/itemDefaultFurnace.png", "furn" + i, "typeDefault");
            furnaceItemsList.add(furn);
        }
        
        Crafteable furnaceItem = new Crafteable("furnace", "/img/itemDefaultCraft.png", "furnace", "typeDefault", true);
        itemsList.add(furnaceItem);
        for(ObjectM normalItem : furnaceItemsList){
            furnaceItem.getCraftingItems().add(normalItem);
        }
    }
    
    private void searchBarConfig(){
        String input = view.getSearchTextField().getText();
        for(Crafteable item : itemsList){
            if(input.equals(item.getName())){
                isItemFurnace(item.isFurnace(), item.getImage());
            }
        }
    }
    
    private void isItemFurnace(boolean state, String imageCraft){
        if(!state){
            view.showCraftingLayeredPane(true);
            view.showFurnaceLayeredPane(false);
            view.setItemsCraftingButton(craftItemsList, imageCraft);
        } else {
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
