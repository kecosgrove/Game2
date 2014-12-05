package XCOMGame;

import javalib.funworld.World;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

import java.awt.*;

public class TransitionWorld extends World {

    String message;
    int duration;
    World next;

    public TransitionWorld(String message, int duration, World next) {
        this.message = message;
        this.duration = duration;
        this.next = next;
    }

    public WorldImage makeImage() {
        WorldImage text = ImageFactory.textImage(new Posn(640, 360), message, 30, 1, new Color(255,255,255));
        WorldImage background = ImageFactory.rectangleImage(new Posn(0, 0), 1280, 720, new Color(0,0,0));
        return ImageFactory.overlayImages(background, text);
    }

    public World onTick() {
        if (duration > 0) return new TransitionWorld(message, duration - 1, next);
        else return next;
    }

}
