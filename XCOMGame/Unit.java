package XCOMGame;

import javalib.worldimages.WorldImage;

public class Unit implements PlayingSquare{

    boolean alive;

    public boolean hasImage() {
        return true;
    }

    public WorldImage getImage() {
        return null; //temp
    }

    public boolean collides() {
        return true;
    }

}
