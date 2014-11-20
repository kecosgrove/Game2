package XCOMGame;

import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

public class Mission implements MissionSlot {

    int timeLeft;
    String city;
    Posn mapPos;

    public Mission(int timeLeft, String city, Posn mapPos) {
        this.timeLeft = timeLeft;
        this.city = city;
        this.mapPos = mapPos;
    }

    public boolean isEmpty() {
        return false;
    }

    //Todo: Figure this out, also for FailMSlot
    public WorldImage getImage() {
        return null; //temp
    }

    public MissionSlot onTick() {
        if (timeLeft > 0) {
            return new Mission(timeLeft-1, city, mapPos);
        } else {
            return new MTMSlot(true);
        }
    }
    
    public boolean panicEvent() {
        return false;
    }

    public boolean failure() {
        return false;
    }

}
