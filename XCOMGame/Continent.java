package XCOMGame;


import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;

import java.awt.*;
import java.util.Random;

public class Continent {

    private int panic;
    private String name;
    private String[] cities;
    private Posn mapPos;
    private MissionSlot mission;

    public Continent(int panic, String name, String[] cities, Posn mapPos, MissionSlot mission) {
        this.panic = panic;
        this.name = name;
        this.cities = cities;
        this.mapPos = mapPos;
        this.mission = mission;
    }

    public int panic() {
        return panic;
    }

    public String name() {
        return name;
    }

    public String randCity() {
        Random rng = new Random();
        return cities[rng.nextInt(cities.length)];
    }

    public WorldImage getImage() {
        WorldImage back = ImageFactory.rectangleImage(mapPos, 100, 20, new Color(255,255,255));
        WorldImage fore = ImageFactory.rectangleImage(mapPos, panic * 10, 20,
                                                      new Color(panic*25, 250 - panic*25, 250 - panic*25));
        return ImageFactory.overlayImages(back, fore);
    }

    public Continent onTick() {
        return this;
    }

}
