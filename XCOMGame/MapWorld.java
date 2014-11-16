package XCOMGame;

import javalib.funworld.World;
import javalib.worldimages.*;

public class MapWorld extends World {

    private static final int maxMissions = 4;
    private static final int numContinents = 6;
    private static final double timeRoC = 3.5;

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
                newContinents[newMissions[i].getEvent()] = newContinents[newMissions[i].getEvent()].processPanic();
            }
        }
        return new MapWorld(date.hourAdvance(timeRoC), newMissions, continents);
    }

    public WorldImage makeImage() {
        return null; //temp
    }



}
