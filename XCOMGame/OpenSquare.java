package XCOMGame;

import javalib.worldimages.WorldImage;

/**
 * Created by User on 12/3/2014.
 */
public class OpenSquare implements PlayingSquare {

    public boolean hasImage() {
        return false;
    }

    public WorldImage getImage() {
        throw new RuntimeException("hasImage called illegally");
    }

    public boolean collides() {
        return false;
    }

}
