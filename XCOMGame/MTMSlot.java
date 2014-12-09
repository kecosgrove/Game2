package XCOMGame;

import javalib.worldimages.Posn;
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

    public boolean hasImage() {
        return false;
    }

    public WorldImage getImage() {
        throw new RuntimeException("No Image");
    }

    public MissionSlot onTick() {
        if (panic) {
            return new MTMSlot(false);
        }
        return this;
    }
    
    public boolean hasEvent() {
        return panic;
    }

    public GameEvent getEvent() {
        return new GameEvent(MapWorld.panicEID, MapWorld.panicRate);
    }

    public boolean failure() {
        return false;
    }

    public GameEvent onMouseClicked(Posn mouse) {
        return new GameEvent(-1);
    }

}
