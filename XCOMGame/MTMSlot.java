package XCOMGame;

import javalib.worldimages.WorldImage;

import java.util.Random;

public class MTMSlot implements MissionSlot {

    boolean panic;

    public MTMSlot(boolean panic) {
        this.panic = panic;
    }

    public boolean isEmpty() {
        return true;
    }

    public WorldImage getImage() {
        throw new RuntimeException("No Image");
    }

    public MissionSlot onTick() {
        if (panic) return new MTMSlot(false);
        else return this;
    }
    
    public boolean panicEvent() {
        return panic;
    }

    public boolean failure() {
        return false;
    }

}
