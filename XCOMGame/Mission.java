package XCOMGame;

import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

import java.awt.*;

public class Mission implements MissionSlot {

    static final int diskRadius = 20;

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
        WorldImage dot = ImageFactory.diskImage(city.posn, diskRadius, new Color(255, 0, 0));
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

    public GameEvent onMouseClicked(Posn mouse) {
        boolean inDisk = Math.sqrt(((city.posn.x-mouse.x)*(city.posn.x-mouse.x)) +
                                   ((city.posn.y-mouse.y)*(city.posn.y-mouse.y)))
                                  <= diskRadius + 4.0;
        if (inDisk) {
            return new GameEvent(MapWorld.startMID, city.name);
        }
        return new GameEvent(-1); //temp
    }

}
