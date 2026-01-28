package minecrafteos.model.object;

import minecrafteos.model.livingBeing.LivingBeing;
import minecrafteos.model.users.User;

/**
 *
 * @author alumno
 */
public class ObjectM {
    private String id;
    private String image;
    private String name;
    private String type;
    private ObjectM obtainingObject;
    private LivingBeing obtainingLV;

    public ObjectM(String id, String image, String name, String type) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    
}
