package XCOMGame;

import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

import java.awt.*;

public class Unit implements PlayingSquare {

    int health;
    Posn arrayPos;
    int team;
    boolean selected;
    int moves;
    boolean canAttack;

    public Unit(int health, Posn arrayPos, int team, boolean selected, int moves, boolean canAttack) {
        this.health = health;
        this.arrayPos = arrayPos;
        this.team = team;
        this.selected = selected;
        this.moves = moves;
        this.canAttack = canAttack;
    }

    public boolean hasImage() {
        return true;
    }

    public WorldImage getImage() {
        WorldImage image;
        Color color;
        Posn posn = MissionWorld.arrayToPixelPos(arrayPos);
        if (team == 0) {
            color = new Color(0,200,0);
        } else color = new Color(255,0,0);
        image = ImageFactory.rectangleImage(posn, 30, 30, color);
        if (selected) {
            WorldImage selectBox = ImageFactory.rectangleImage(posn, 32, 32,
                                                               new Color(0,0,0));
            image = ImageFactory.overlayImages(selectBox, image);
        }
        image = ImageFactory.overlayImages(image, ImageFactory.textImage(posn, "" + health, 12, 0, new Color(0,0,0)));
        return image;
    }

    public boolean collides() {
        return true;
    }

    public Posn getPosn() {
        return arrayPos;
    }

    public boolean unit() {
        return true;
    }

    public PlayingSquare giveDamage(int damage) {
        return new Unit(health - damage, arrayPos, team, selected, moves, canAttack);
    }

    public PlayingSquare selected(boolean selected) {
        return new Unit(health, arrayPos, team, selected, moves, canAttack);
    }

    public int moves() {
        return moves;
    }

    public int damage() {
        return health;
    }

    public boolean canAttack() {
        return canAttack;
    }

    public PlayingSquare attack() {
        return new Unit(health, arrayPos, team, selected, moves, false);
    }

    public PlayingSquare move(Posn posn) {
        return new Unit(health, posn, team, selected, 0, canAttack);
    }

    public PlayingSquare reset(boolean selected) {
        return new Unit(health, arrayPos, team, selected, MissionWorld.movesPerTurn, true);
    }

}
