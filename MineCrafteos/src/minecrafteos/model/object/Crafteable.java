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
    private ArrayList<ObjectM> crafting;
    public Crafteable(String id, String image, String name, String type, boolean furnace) {
        super(id, image, name, type);
        this.furnace = furnace;
        crafting = new ArrayList<>();
    }

    public boolean isFurnace() {
        return furnace;
    }

    public void setFurnace(boolean furnace) {
        this.furnace = furnace;
    }

    public ArrayList<ObjectM> getCrafting() {
        return crafting;
    }

    public void setCrafting(ArrayList<ObjectM> crafting) {
        this.crafting = crafting;
    }
    
    
}
