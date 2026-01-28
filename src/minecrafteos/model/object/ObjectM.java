package minecrafteos.model.object;

import java.util.ArrayList;
import minecrafteos.model.livingBeing.LivingBeing;
import minecrafteos.model.users.User;

/**
 *
 * @author alumno
 */
public class ObjectM {
    private int id;
    private String image;
    private String name;
    private String type;
    private ObjectM obtainingObject;
    private LivingBeing obtainingLV;
    private boolean crafteable;
    private boolean furnace;
    private User user;
    private ArrayList<ObjectM> craftingItems;

    public ObjectM(int id, String image, String name, String type, boolean crafteable,  boolean furnace, User user) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.type = type;
        this.crafteable = crafteable;
        this.furnace = furnace;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCrafteable() {
        return crafteable;
    }

    public void setCrafteable(boolean crafteable) {
        this.crafteable = crafteable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ObjectM getObtainingObject() {
        return obtainingObject;
    }

    public void setObtainingObject(ObjectM obtainingObject) {
        this.obtainingObject = obtainingObject;
    }

    public LivingBeing getObtainingLV() {
        return obtainingLV;
    }

    public void setObtainingLV(LivingBeing obtainingLV) {
        this.obtainingLV = obtainingLV;
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

    public void setCraftingItems(ArrayList<ObjectM> craftingItems) {
        this.craftingItems = craftingItems;
    }
    
}
