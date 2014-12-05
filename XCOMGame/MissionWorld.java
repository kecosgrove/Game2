package XCOMGame;

import javalib.funworld.World;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

public class MissionWorld extends World {

    Posn[] enemies;
    Posn[] units;
    PlayingSquare[][] board;
    Posn selection;
    Posn target;
    boolean turn;
    World map;

    public MissionWorld() {
    }

    public WorldImage makeImage() {
        return null; //temp
    }

    public World onTick() {
        return this; //temp
    }

}
