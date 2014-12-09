package XCOMGame;

import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

public class OpenSquare implements PlayingSquare {

    Posn arrayPos;

    public OpenSquare(Posn arrayPos) {
        this.arrayPos = arrayPos;
    }

    public boolean hasImage() {
        return false;
    }

    public WorldImage getImage() {
        throw new RuntimeException("getImage called illegally");
    }

    public boolean collides() {
        return false;
    }

    public Posn getPosn() {
        return arrayPos;
    }

    public boolean unit() {
        return false;
    }

    public PlayingSquare onTick() {
        throw new RuntimeException("Illegal call to onTick()");
    }

    public PlayingSquare giveDamage(int damage) {
        throw new RuntimeException("Illegal call to giveDamage()");
    }

    public PlayingSquare selected(boolean selected) {
        throw new RuntimeException("Illegal call to selected()");
    }

    public int moves() {
        throw new RuntimeException("Illegal call to moves()");
    }

    public int damage() {
        throw new RuntimeException("Illegal call to damage()");
    }

    public PlayingSquare attack() {
        throw new RuntimeException("Illegal call to attack()");
    }

    public boolean canAttack() {
        throw new RuntimeException("Illegal call to canAttack()");
    }

    public PlayingSquare move(Posn posn) {
        throw new RuntimeException("Illegal call to move()");
    }

    public PlayingSquare reset(boolean selected) {
        throw new RuntimeException("Illegal call to reset()");
    }

}
