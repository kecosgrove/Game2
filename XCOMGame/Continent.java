package XCOMGame;


import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Continent {

    int panic;
    City[] cities;
    Posn mapPos;
    Posn textPos;
    MissionSlot mission;
    Random rng;

    public Continent(int panic, City[] cities, Posn mapPos, Posn textPos, MissionSlot mission) {
        this.panic = panic;
        this.cities = cities;
        this.mapPos = mapPos;
        this.textPos = textPos;
        this.mission = mission;
        rng = new Random();
    }

    public int panic() {
        return panic;
    }


    private City randCity() {
        return cities[rng.nextInt(cities.length)];
    }

    public WorldImage getImage() {
        WorldImage back = ImageFactory.rectangleImage(mapPos, 100, 20, new Color(255,255,255));
        WorldImage fore = ImageFactory.rectangleImage(mapPos, panic * 10, 20,
                                                      new Color(panic*25, toNN(125 - panic*25) * 2, 250 - panic * 25));
        if (mission.hasImage()) {
            return ImageFactory.overlayImages(mission.getImage(), ImageFactory.overlayImages(back, fore));
        }
        return ImageFactory.overlayImages(back, fore);
    }

    public Continent onTick() {
        int panic = this.panic;
        MissionSlot mission = this.mission.onTick();
        if (mission.hasEvent() && mission.getEvent().ID == MapWorld.panicEID) panic = panic + mission.getEvent().value;
        if (panic > 9)
            return new Continent(panic, cities, mapPos, textPos,
                                 new FailMSlot(new Posn(mapPos.x, mapPos.y - 50)));
        if (mission.isEmpty()) {
            boolean makeMission = (rng.nextInt() % MapWorld.missionRate) == 0;
            if (makeMission) {
                return new Continent(panic, cities, mapPos, textPos,
                                     new Mission(MapWorld.missionDuration, randCity(), textPos, true));
            }
        }
        return new Continent(panic, cities, mapPos, textPos, mission);
    }

    //determines game over
    public boolean failure() {
        return mission.failure();
    }

    public GameEvent onMouseClicked(Posn mouse) {
        return mission.onMouseClicked(mouse);
    }

    public Continent missionHandled() {
        if (panic > 0) return new Continent(panic - MapWorld.panicRate, cities, mapPos, textPos, new MTMSlot(false));
        else return new Continent(panic, cities, mapPos, textPos, new MTMSlot(false));
    }

    private static int toNN(int num) {
        if (num >= 0 ) {
            return num;
        } else {
            return 0;
        }
    }

}
