package XCOMGame;

import javalib.funworld.World;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

import java.awt.*;
import java.util.Date;

/**
 * Created by User on 12/3/2014.
 */
public class GameOverWorld extends World {

    DateContainer date;
    DateContainer start;

    public GameOverWorld(DateContainer date, DateContainer start) {
        this.date = date;
        this.start = start;
    }

    public WorldImage makeImage() {
        Color textColor = new Color(255, 255, 255);
        WorldImage text = ImageFactory.textImage(new Posn(640, 300), "Game Over", 50, 1, textColor);
        int length = (date.year-start.year)*356 + (date.day-start.day) + ((date.year-2)-start.year)/4;
        String summary = "Earth held out against the aliens for " + length + " days and surrendered on " +
                          date.simpleString() + ".";
        text = ImageFactory.overlayImages(text, ImageFactory.textImage(new Posn(640, 360), summary, 20, 1, textColor));
        WorldImage background = ImageFactory.rectangleImage(new Posn(640, 360), 1280, 720, new Color(0,0,0));
        return ImageFactory.overlayImages(background, text);
    }

    public World onTick() {
        return this;
    }

}
