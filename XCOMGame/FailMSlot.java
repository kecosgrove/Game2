package XCOMGame;

import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

import java.awt.*;

/**
 * Created by User on 11/21/2014.
 */
public class FailMSlot implements MissionSlot {

    Posn mapPos;

    public FailMSlot(Posn mapPos) {
        this.mapPos = mapPos;
    }

    public boolean failure() {
        return true;
    }

    public boolean hasImage() {
        return true;
    }

    public WorldImage getImage() {
        WorldImage text = ImageFactory.textImage(mapPos, "Panic!", 20, 1, new Color(255,25,25));
        return ImageFactory.overlayImages(ImageFactory.rectangleImage(new Posn(mapPos.x, mapPos.y - 3), 80, 26, new Color(0, 0, 0)), text);
    }

    public boolean hasEvent() {
        return false;
    }

    public GameEvent getEvent() {
        throw new RuntimeException("No Event");
    }

    public MissionSlot onTick() {
        return this;
    }

    public boolean isEmpty() {
        return false;
    }

    public GameEvent onMouseClicked(Posn mouse) {
        return new GameEvent(-1);
    }

}
