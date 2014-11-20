package XCOMGame;


import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

import java.awt.*;
import java.util.Random;

public class Continent {

    private int panic;
    private String name;
    private String[] cities;
    private Posn mapPos;
    private MissionSlot mission;
    private Random rng;

    public Continent(int panic, String name, String[] cities, Posn mapPos, MissionSlot mission) {
        this.panic = panic;
        this.name = name;
        this.cities = cities;
        this.mapPos = mapPos;
        this.mission = mission;
        rng = new Random();
    }

    public int panic() {
        return panic;
    }

    public String name() {
        return name;
    }

    public String randCity() {
        return cities[rng.nextInt(cities.length)];
    }

    public WorldImage getImage() {
        WorldImage back = ImageFactory.rectangleImage(mapPos, 100, 20, new Color(255,255,255));
        WorldImage fore = ImageFactory.rectangleImage(mapPos, panic * 10, 20,
                                                      new Color(panic*25, toNN(125 - panic*25) * 2, 250 - panic * 25));
        //return ImageFactory.overlayImages(ImageFactory.overlayImages(back, fore), mission.getImage());
        return ImageFactory.overlayImages(back, fore);
    }

    private static int toNN(int num) {
        if (num >= 0 ) {
            return num;
        } else {
            return 0;
        }
    }

    public Continent onTick() {
        int panic = this.panic;
        if (panic > 9) return new Continent(panic, name, cities, mapPos, new FailMSlot());
        if (mission.isEmpty()) {
            boolean makeMission = (rng.nextInt() % MapWorld.missionRate) == 0;
            if (mission.panicEvent()) {
                panic++;
            }
            if (makeMission) {
                return new Continent(panic, name, cities, mapPos,
                                     new Mission(MapWorld.missionDuration, randCity(), mapPos));
            }
        }
        return new Continent(panic, name, cities, mapPos, mission.onTick());
    }

    public boolean failure() {
        return mission.failure();
    }

}
