package XCOMGame;

import javalib.worldimages.WorldImage;

/**
 * Created by User on 11/19/2014.
 */
public class FailMSlot implements MissionSlot {

    public boolean isEmpty() {
        return false;
    }

    public WorldImage getImage() {
        return null; //temp
    }

    public MissionSlot onTick() {
        return this;
    }

    public boolean panicEvent() {
        return false;
    }

    public boolean failure() {
        return true;
    }

}
