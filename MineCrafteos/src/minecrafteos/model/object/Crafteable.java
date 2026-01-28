/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minecrafteos.model.object;

import java.util.ArrayList;

/**
 *
 * @author alumno
 */
public class Crafteable extends ObjectM{

    private boolean furnace;
    private ArrayList<ObjectM> craftingItems;
    public Crafteable(String id, String image, String name, String type, boolean furnace) {
        super(id, image, name, type);
        this.furnace = furnace;
        craftingItems = new ArrayList<>();
    }

    public boolean isFurnace() {
        return furnace;
    }

    public void setFurnace(boolean furnace) {
        this.furnace = furnace;
    }

    public ArrayList<ObjectM> getCraftingItems() {
        return craftingItems;
    }

    public void setCraftingItems(ArrayList<ObjectM> crafting) {
        this.craftingItems = crafting;
    }

    @Override
    public String toString() {
        return "Crafteable{" + "name=" + this.getName() + ", furnace=" + furnace + '}';
    }
}
