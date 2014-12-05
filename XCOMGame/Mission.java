package XCOMGame;

import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

import java.awt.*;

public class Mission implements MissionSlot {

    int timeLeft;
    City city;
    Posn textPos;
    boolean hasEvent;

    public Mission(int timeLeft, City city, Posn textPos, boolean hasEvent) {
        this.timeLeft = timeLeft;
        this.city = city;
        this.textPos = textPos;
        this.hasEvent = hasEvent;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean hasImage() {
        return true;
    }

    //Todo: Add text, play with image positions and sizes
    public WorldImage getImage() {
        WorldImage dot = ImageFactory.diskImage(city.posn, 20, new Color(255, 0, 0));
        WorldImage text = ImageFactory.textImage(textPos, ("Alien Attack in " + city.name), 20, 1, new Color(255, 0, 0));
        return ImageFactory.overlayImages(dot, text);
    }

    public MissionSlot onTick() {
        if (timeLeft > 0) {
            return new Mission(timeLeft-1, city, textPos, false);
        } else {
            return new MTMSlot(true);
        }
    }

    public boolean hasEvent() {
        return hasEvent;
    }

    public GameEvent getEvent() {
        return new GameEvent(MapWorld.tickerEID, ("Alien Attack in " + city));
    }

    public boolean failure() {
        return false;
    }

}
