package XCOMGame;

import javalib.colors.White;
import javalib.funworld.World;
import javalib.worldimages.*;

import java.util.Random;

public class MapWorld extends World {

    public static final int numContinents = 6;
    public static final Double timeRoC = 3.0;
    public static final int transitionLength = 15;
    public static final int missionRate = 60;
    public static final int missionDuration = 20;
    public static final int screenHeight = 720;
    public static final int screenWidth = 1280;
    public static final WorldImage image =
            ImageFactory.fromFileImage(new Posn(960, 540), "images\\map.png");
    public static final Posn datePosn = new Posn(640, 680);
    public static final Posn woundedPosn = new Posn(220, 680);
    public static final Posn NAPos = new Posn(280, 310);
    public static final Posn SAPos = new Posn(430, 550);
    public static final Posn EUPos = new Posn(690, 250);
    public static final Posn AFPos = new Posn(730, 500);
    public static final Posn OCPos = new Posn(1110, 570);
    public static final Posn ASPos = new Posn(950, 330);
    public static final DateContainer start = new DateContainer(2015, 60, 0, 0);
    public static final int panicEID = 1;
    public static final int startMID = 2;
    public static final int tickerEID = 2;
    public static final int panicRate = 2;
    public static final int startUnits = 8;
    public static final int woundedMultiplier = 15;

    DateContainer date;
    Continent[] continents;
    int units;
    int wounded;

    //onTick || post-mission constructor
    public MapWorld(DateContainer date, Continent[] continents, int units, int wounded) {
        this.date = date;
        this.continents = continents;
        this.units = units;
        this.wounded = wounded;
    }

    //Initial constructor
    public MapWorld(DateContainer date, Continent[] continents) {
        if (continents.length != numContinents) {
            throw new RuntimeException("Array incorrectly initialized");
        }
        this.date = date;
        this.continents = continents;
        this.units = startUnits;
        this.wounded = 0;
    }

    public World onTick() {
        Continent[] newContinents = continents;
        boolean failure = true;
        for (int i = 0; i < continents.length; i++) {
            newContinents[i] = newContinents[i].onTick();
            failure = newContinents[i].failure() && failure;
        }
        if (failure) {
            return new GameOverWorld(date, MapWorld.start);
        }
        if (wounded > 0) return new MapWorld(date.hourAdvance(timeRoC), newContinents, units, wounded - 1);
        return new MapWorld(date.hourAdvance(timeRoC), newContinents, units, wounded);
    }

    public WorldImage makeImage() {
        WorldImage image = this.image;
        image = ImageFactory.overlayImages(image, date.getImage());
        if (wounded > 0) {
            WorldImage recovery = ImageFactory.textImage(woundedPosn, "Days until squad is ready: " +
                    (wounded * timeRoC.intValue()), 20, 1, new White());
            image = ImageFactory.overlayImages(image, recovery);
        }
        for (int i = 0; i < continents.length; i++) {
            image = ImageFactory.overlayImages(image, continents[i].getImage());
        }
        return image;
    }

    public World onMouseClicked(Posn mouse) {
        for (int i = 0; i < continents.length; i++) {
            GameEvent event = continents[i].onMouseClicked(mouse);
            if (event.ID == startMID) {
                if (wounded > 0) {
                    return new TransitionWorld("Your squad must take " + wounded*timeRoC.intValue() + " more hours to recover.",
                                               transitionLength, this);
                }
                continents[i] = continents[i].missionHandled();
                World mission = new MissionWorld(this, units, (new Random().nextInt() % 3) + 4);
                return new TransitionWorld(event.message, transitionLength, mission);
            }
        }
        return this;
    }

}
