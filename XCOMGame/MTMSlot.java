package XCOMGame;

import javalib.worldimages.WorldImage;

import java.util.Random;

public class MTMSlot implements MissionSlot {

    Random rng;

    public MTMSlot() {
        rng = new Random();
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

}
