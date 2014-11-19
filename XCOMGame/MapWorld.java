package XCOMGame;

import javalib.funworld.World;
import javalib.worldimages.*;

import java.awt.*;
import java.util.Random;

public class MapWorld extends World {

    private static final int numContinents = 6;
    private static final double timeRoC = 3.5;

    protected static final int missionRate = 10;
    protected static final int missionDuration = 10;
    protected static final int screenHeight = 1280;
    protected static final int screenWidth = 720;
    protected static final WorldImage image =
            ImageFactory.fromFileImage(new Posn(960, 540),
                    "C:\\Users\\User\\Documents\\CS-203\\game2\\src\\XCOMGame\\images\\map.png");
    protected static final Posn datePosn = new Posn(640, 680);
    protected static final Posn NAPos = new Posn(280, 310);
    protected static final Posn SAPos = new Posn(430, 550);
    protected static final Posn EUPos = new Posn(690, 250);
    protected static final Posn AFPos = new Posn(730, 500);
    protected static final Posn OCPos = new Posn(1110, 570);
    protected static final Posn ASPos = new Posn(950, 330);

    DateContainer date;
    Continent[] continents;

    public MapWorld(DateContainer date, Continent[] continents) {
        if (continents.length != numContinents) {
            throw new RuntimeException("Array incorrectly initialized");
        }
        this.date = date;
        this.continents = continents;
    }

    public World onTick() {
        Continent[] newContinents = continents;
        for (int i = 0; i < continents.length; i++) {
            newContinents[i] = newContinents[i].onTick();
        }
        return new MapWorld(date.hourAdvance(timeRoC), newContinents);
    }

    public WorldImage makeImage() {
        WorldImage image = this.image;
        image = ImageFactory.overlayImages(image, date.getImage());
        for (int i = 0; i < continents.length; i++) {
            image = ImageFactory.overlayImages(image, continents[i].getImage());
        }
        return image;
    }



}
