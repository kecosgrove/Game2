package XCOMGame;

import javalib.worldimages.WorldImage;

/**
 * Created by User on 12/3/2014.
 */
public interface PlayingSquare {

    boolean hasImage();
    //getImage only defined if hasImage returns true
    WorldImage getImage();
    boolean collides();

}
