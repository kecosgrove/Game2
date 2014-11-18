package XCOMGame;

import javalib.funworld.World;
import javalib.worldimages.*;

import java.util.Random;

public class MapWorld extends World {

    private static final int maxMissions = 4;
    private static final int numContinents = 6;
    private static final double timeRoC = 3.5;
    private static final int missionRate = 100;
    private static final int missionDuration = 500;

    protected static final int screenHeight = 1280;
    protected static final int screenWidth = 720;
    protected final WorldImage image = new FromFileImage(new Posn(0, 0), "images/bigbomb.png");

    Random rng = new Random();

    DateContainer date;
    MissionSlot[] missions;
    Continent[] continents;

    public MapWorld(DateContainer date, MissionSlot[] missions, Continent[] continents) {
        if (missions.length != maxMissions || continents.length != numContinents) {
            throw new RuntimeException("Arrays incorrectly initialized");
        }
        this.date = date;
        this.missions = missions;
        this.continents = continents;
    }

    public World onTick() {
        MissionSlot[] newMissions = missions;
        Continent[] newContinents = continents;
        for (int i = 0; i < missions.length; i++) {
            newMissions[i] = newMissions[i].onTick();
            if (newMissions[i].isEmpty()) {
                if (newMissions[i].getEvent() >= 0) {
                    newContinents[newMissions[i].getEvent()] = newContinents[newMissions[i].getEvent()].processPanic();
                    newMissions[i] = new MTMSlot(-1);
                }
                if (rng.nextInt() % missionRate == 0) {
                    int continent = Math.abs(rng.nextInt() % continents.length);
                    newMissions[i] = new Mission(missionDuration, continent, continents[continent].randCity());
                }
            }
        }
        return new MapWorld(date.hourAdvance(timeRoC), newMissions, newContinents);
    }

    public WorldImage makeImage() {
        return image;
    }



}
