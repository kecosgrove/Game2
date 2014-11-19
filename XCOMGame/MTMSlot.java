package XCOMGame;

import javalib.worldimages.WorldImage;

import java.util.Random;

public class MTMSlot implements MissionSlot {

    Random rng;
    boolean panic;

    public MTMSlot(boolean panic) {
        rng = new Random();
        this.panic = panic;
    }

    public boolean isEmpty() {
        return true;
    }

    public WorldImage getImage() {
        throw new RuntimeException("No available WorldImage");
    }

    public MissionSlot onTick() {
        return this;
    }
    
    public boolean panicEvent() {
        return panic;
    }

}
