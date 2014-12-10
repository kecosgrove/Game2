package XCOMGame;

import javalib.funworld.World;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

import java.awt.*;

public class MissionWorld extends World {

    public static final int unitHealth = 5;
    public static final int pause = 5;
    public static final int shortPause = 3;
    public static final int movesPerTurn = 6;
    public static final Posn chancePos = new Posn(1150, 700);
    public static final int yourMove = 0;
    public static final int yourAttack = 1;
    public static final int enemyTurn = 2;

    public static boolean go = false;

    Board board;
    int state;
    MapWorld map;

    //constructor 1: initial constructor
    public MissionWorld(MapWorld map, int teamOne, int teamTwo) {
        board = BoardFactory.makeBoard(teamOne, teamTwo);
        state = yourMove;
        this.map = map;
    }

    //constructor 2: onTick constructor
    public MissionWorld(MapWorld map, Board board, int state) {
        this.map = map;
        this.board = board;
        this.state = state;
    }

    public static Posn arrayToPixelPos(Posn arrayPos) {
        return new Posn((arrayPos.x*40) + 40, (arrayPos.y*-40) + 720 - 40);
    }

    public static Posn pixeltoArrayPos(Posn pPos) {
        Double x = Math.floor(Math.abs(pPos.x - 20)/40.0);
        Double y = Math.floor(Math.abs(pPos.y - 20) / -40.0) + 17;
        return new Posn(x.intValue(), y.intValue());
    }

    public WorldImage makeImage() {
        if (state == yourAttack) {
            WorldImage chance = ImageFactory.textImage(chancePos, board.chanceToHit() + "%", 30, 0, new Color(0,0,255));
            return ImageFactory.overlayImages(board.getImage(), chance);
        } else if (state == enemyTurn) {
            WorldImage chance = ImageFactory.textImage(chancePos, "Enemy Turn", 30, 0, new Color(0,0,255));
            return ImageFactory.overlayImages(board.getImage(), chance);
        }
        return board.getImage();
    }

    public World onTick() {
        if (board.forceEnd()) {
            return this.changeTurn();
        }
        if (state == enemyTurn && go) {
            return Enemy.makeMove(this);
        } else if (state == enemyTurn) {
            go = !go;
        }
        return this;
    }

    public World onKeyEvent(String s) {
        if (state == yourMove) {
            if (s.compareTo("z") == 0) {
                return new MissionWorld(map, board, yourAttack);
            }
            if (s.compareTo("e") == 0) {
                return this.changeTurn();
            }
            if (s.compareTo("left") == 0) {
                return new MissionWorld(map, board.rotateLeft(), state);
            } else if (s.compareTo("right") == 0) {
                return new MissionWorld(map, board.rotateRight(), state);
            }
        } else if (state == yourAttack) {
            if (s.compareTo("z") == 0) {
                return new MissionWorld(map, board, yourMove);
            }
            if (s.compareTo("e") == 0) {
                return this.changeTurn();
            }
            if (s.compareTo("x") == 0) {
                return board.attack(this);
            }
            if (s.compareTo("left") == 0) {
                return new MissionWorld(map, board.aimLeft(), state);
            } else if (s.compareTo("right") == 0) {
                return new MissionWorld(map, board.aimRight(), state);
            }
        }
        return this;
    }

    public World onMouseClicked(Posn mouse) {
        if (state == yourMove) {
            Posn arrayPos = pixeltoArrayPos(mouse);
            if (board.inBoard(arrayPos)) {
                return new MissionWorld(map, board.move(arrayPos), state);
            } else return this;
        } else return this;
    }

    public MissionWorld changeTurn() {
        int newTurn;
        if (state == yourAttack || state == yourMove) newTurn = enemyTurn;
        else newTurn = yourMove;
        return new MissionWorld(map, board.end(), newTurn);
    }

    public World completeMission() {
        World newMap = new MapWorld(map.date, map.continents, board.teamOneAlive(), board.teamOneDmg());
        return new TransitionWorld("Mission Complete", MapWorld.transitionLength, newMap);
    }

    public World failMission() {
        return new GameOverWorld(map.date, map.start);
    }

}
