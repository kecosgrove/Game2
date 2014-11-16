package XCOMGame;

import javalib.worldimages.WorldImage;

public class MTMSlot implements MissionSlot {

    int panicEvent;

    public MTMSlot(int panicEvent) {
        this.panicEvent = panicEvent;
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

    public int getEvent() {
        return panicEvent;
    }

}
