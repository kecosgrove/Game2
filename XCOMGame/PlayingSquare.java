package XCOMGame;

import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

/**
 * Created by User on 12/3/2014.
 */
public interface PlayingSquare {

    boolean hasImage();
    //getImage only defined if hasImage returns true
    WorldImage getImage();
    boolean collides();
    Posn getPosn();
    boolean unit();
    //below here defined only when unit() returns true.
    PlayingSquare giveDamage(int damage);
    PlayingSquare selected(boolean selected);
    int moves();
    int damage();
    boolean canAttack();
    PlayingSquare attack();
    PlayingSquare move(Posn posn);
    PlayingSquare reset(boolean selected);
}
