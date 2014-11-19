package XCOMGame;

import javalib.worldimages.WorldImage;

//Todo: Redesign for 1 per continent
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
            return new MTMSlot();
        }
    }

}
