package XCOMGame;

import javalib.worldimages.WorldImage;

/**
 * Created by User on 11/15/2014.
 */
public class Mission implements MissionSlot {

    int timeLeft;
    int location;
    String city;

    public Mission(int timeLeft, int location, String city) {
        this.timeLeft = timeLeft;
        this.location = location;
        this.city = city;
    }

    public boolean isEmpty() {
        return false;
    }

    public WorldImage getImage() {
        return null; //temp
    }

    public MissionSlot onTick() {
        if (timeLeft > 0) {
            return new Mission(timeLeft-1, location, city);
        } else {
            return new MTMSlot(location);
        }
    }

    public int getEvent() {
        throw new RuntimeException("No event");
    }

}
