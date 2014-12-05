package XCOMGame;

import javalib.worldimages.WorldImage;

/**
 * Created by User on 12/3/2014.
 */
public class Cover implements PlayingSquare {

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
