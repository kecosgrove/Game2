package XCOMGame;

import javalib.funworld.World;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

import java.awt.*;
import java.io.IOError;
import java.util.Random;

public class Board {

    PlayingSquare[][] board;
    Posn[] teamOne;
    Posn[] teamTwo;
    Posn[] cover;
    Posn[] reachable;
    int selected;
    int target;
    int turn;

    public Board(PlayingSquare[][] board, Posn[] teamOne, Posn[] teamTwo, Posn[] cover, int selected, int target,
                 int turn) {
        if (turn == 0) {
            this.board = board;
            this.teamOne = teamOne;
            this.teamTwo = teamTwo;
            this.cover = cover;
            this.selected = selected;
            this.target = target;
            this.reachable = findReachable(teamOne[selected], board[teamOne[selected].x][teamOne[selected].y].moves());
            this.turn = turn;
        } else {
            this.board = board;
            this.teamOne = teamOne;
            this.teamTwo = teamTwo;
            this.cover = cover;
            this.selected = selected;
            this.target = target;
            this.reachable = findReachable(teamTwo[selected], board[teamTwo[selected].x][teamTwo[selected].y].moves());
            this.turn = turn;
        }
    }

    public WorldImage getImage() {
        WorldImage image = ImageFactory.rectangleImage(new Posn(640, 360), MapWorld.screenWidth, MapWorld.screenHeight,
                                                       new Color(200,200,200));
        for (int i = 0; i < teamOne.length; i++) {
            image = ImageFactory.overlayImages(image, board[teamOne[i].x][teamOne[i].y].getImage());
        }
        for (int i = 0; i < teamTwo.length; i++) {
            image = ImageFactory.overlayImages(image, board[teamTwo[i].x][teamTwo[i].y].getImage());
        }
        for (int i = 0; i < cover.length; i++) {
            image = ImageFactory.overlayImages(image, board[cover[i].x][cover[i].y].getImage());
        }
        if (turn == 0) {
            for (int i = 0; i < reachable.length; i++) {
                if (reachable[i].x != teamOne[selected].x || reachable[i].y != teamOne[selected].y) {
                    WorldImage dots = ImageFactory.diskImage(MissionWorld.arrayToPixelPos(reachable[i]), 4, new Color(0, 0, 0));
                    image = ImageFactory.overlayImages(image, dots);
                }
            }
        } else {
            for (int i = 0; i < reachable.length; i++) {
                if (reachable[i].x != teamTwo[selected].x || reachable[i].y != teamTwo[selected].y) {
                    WorldImage dots = ImageFactory.diskImage(MissionWorld.arrayToPixelPos(reachable[i]), 4, new Color(0, 0, 0));
                    image = ImageFactory.overlayImages(image, dots);
                }
            }
        }
        if (turn == 0) {
            image = ImageFactory.overlayImages(image, targetImage(teamTwo[target]));
        } else {
            image = ImageFactory.overlayImages(image, targetImage(teamOne[target]));
        }
        return image;
    }

    //returns how many of team 1 are alive
    public int teamOneAlive() {
        return teamOne.length;
    }

    public int teamOneDmg() {
        int count = 0;
        for (int i = 0; i < teamOne.length; i++) {
            count = count + (MissionWorld.unitHealth - board[teamOne[i].x][teamOne[i].y].damage());
        }
        return count * MapWorld.woundedMultiplier;
    }

    public Board rotateRight() {
        if (turn == 0) {
            int newSelected = (selected + 1) % teamOne.length;
            board[teamOne[selected].x][teamOne[selected].y] = board[teamOne[selected].x][teamOne[selected].y].selected(false);
            board[teamOne[newSelected].x][teamOne[newSelected].y] = board[teamOne[newSelected].x][teamOne[newSelected].y].selected(true);
            return new Board(board, teamOne, teamTwo, cover, newSelected, target, turn);
        } else {
            int newSelected = (selected + 1) % teamTwo.length;
            board[teamTwo[selected].x][teamTwo[selected].y] = board[teamTwo[selected].x][teamTwo[selected].y].selected(false);
            board[teamTwo[newSelected].x][teamTwo[newSelected].y] = board[teamTwo[newSelected].x][teamTwo[newSelected].y].selected(true);
            return new Board(board, teamOne, teamTwo, cover, newSelected, target, turn);
        }
    }

    public Board rotateLeft() {
        if (turn == 0) {
            int newSelected = selected - 1;
            if (newSelected < 0) newSelected = newSelected + teamOne.length;
            board[teamOne[selected].x][teamOne[selected].y] = board[teamOne[selected].x][teamOne[selected].y].selected(false);
            board[teamOne[newSelected].x][teamOne[newSelected].y] = board[teamOne[newSelected].x][teamOne[newSelected].y].selected(true);
            return new Board(board, teamOne, teamTwo, cover, newSelected, target, turn);
        } else {
            int newSelected = selected - 1;
            if (newSelected < 0) newSelected = newSelected + teamTwo.length;
            board[teamTwo[selected].x][teamTwo[selected].y] = board[teamTwo[selected].x][teamTwo[selected].y].selected(false);
            board[teamTwo[newSelected].x][teamTwo[newSelected].y] = board[teamTwo[newSelected].x][teamTwo[newSelected].y].selected(true);
            return new Board(board, teamOne, teamTwo, cover, newSelected, target, turn);
        }
    }

    public Board aimRight() {
        if (turn == 0) {
            int newTarget = (target + 1) % teamTwo.length;
            return new Board(board, teamOne, teamTwo, cover, selected, newTarget, turn);
        } else {
            int newTarget = (target + 1) % teamOne.length;
            return new Board(board, teamOne, teamTwo, cover, selected, newTarget, turn);
        }
    }

    public Board aimLeft() {
        if (turn == 0) {
            int newTarget = target - 1;
            if (newTarget < 0) newTarget = newTarget + teamTwo.length;
            return new Board(board, teamOne, teamTwo, cover, selected, newTarget, turn);
        } else {
            int newTarget = target - 1;
            if (newTarget < 0) newTarget = newTarget + teamOne.length;
            return new Board(board, teamOne, teamTwo, cover, selected, newTarget, turn);
        }
    }

    public double chanceToHit() {
        if (turn == 0) {
            return genChanceToHit(teamOne[selected], teamTwo[target], board);
        } else {
            return genChanceToHit(teamTwo[selected], teamOne[target], board);
        }
    }

    public static double genChanceToHit(Posn attacker, Posn attacked, PlayingSquare[][] board) {
        double max = Math.sqrt(31*31 + 17*17);
        double chance = Math.sqrt((attacker.x - attacked.x) * (attacker.x - attacked.x) +
                (attacker.y - attacked.y) * (attacker.y - attacked.y));
        chance = Math.floor(-100*((chance-max)/max));
        if (chance < 0) chance = 0;
        if (hasCover(attacker, attacked, board)) chance = chance / 2;
        return chance;
    }

    public static boolean hasCover(Posn attacker, Posn attacked, PlayingSquare[][] board) {
        boolean cover = false;
        if (attacker.x < attacked.x && board[attacked.x - 1][attacked.y].collides() &&
                !board[attacked.x - 1][attacked.y].unit()) cover = true;
        else if (attacker.x > attacked.x && board[attacked.x + 1][attacked.y].collides() &&
                !board[attacked.x + 1][attacked.y].unit()) cover = true;
        else if (attacker.y < attacked.y && board[attacked.x][attacked.y - 1].collides() &&
                !board[attacked.x][attacked.y - 1].unit()) cover = true;
        else if (attacker.y > attacked.y && board[attacked.x][attacked.y + 1].collides() &&
                !board[attacked.x][attacked.y + 1].unit()) cover = true;
        return cover;
    }

    public World attack(MissionWorld parent) {
        if (turn == 0) {
            if (board[teamOne[selected].x][teamOne[selected].y].canAttack()) {
                Random rng = new Random();
                WorldImage result = ImageFactory.rectangleImage(new Posn(640, 355), 100, 40, new Color(255, 255, 255));
                //Posn resultPos = new Posn(teamTwo[target].x -25, teamTwo[target].y);
                Posn resultPos = new Posn(640, 360);
                board[teamOne[selected].x][teamOne[selected].y] = board[teamOne[selected].x][teamOne[selected].y].attack();
                if (rng.nextInt(100) < chanceToHit()) {
                    int damage = rng.nextInt(3) + 1;
                    board[teamTwo[target].x][teamTwo[target].y] =
                            board[teamTwo[target].x][teamTwo[target].y].giveDamage(damage);
                    result = ImageFactory.overlayImages(result, ImageFactory.textImage(resultPos, "-" + damage, 30, 0,
                            new Color(100, 100, 255)));
                } else {
                    result = ImageFactory.overlayImages(result, ImageFactory.textImage(resultPos, "miss", 30, 0,
                            new Color(100, 100, 255)));
                }
                return new AttackWorld(parent, MissionWorld.pause, result);
            }
            WorldImage cantAttack = ImageFactory.rectangleImage(new Posn(640, 360), 600, 40, new Color(255, 255, 255));
            cantAttack = ImageFactory.overlayImages(cantAttack, ImageFactory.textImage(new Posn(640, 360),
                    "That unit already attacked this turn!", 30, 1, new Color(0, 0, 0)));
            return new AttackWorld(parent, MissionWorld.shortPause, cantAttack);
        } else {
            if (board[teamTwo[selected].x][teamTwo[selected].y].canAttack()) {
                Random rng = new Random();
                WorldImage result = ImageFactory.rectangleImage(new Posn(640, 355), 100, 40, new Color(255, 255, 255));
                //Posn resultPos = new Posn(teamTwo[target].x -25, teamTwo[target].y);
                Posn resultPos = new Posn(640, 360);
                board[teamTwo[selected].x][teamTwo[selected].y] = board[teamTwo[selected].x][teamTwo[selected].y].attack();
                if (rng.nextInt(100) < chanceToHit()) {
                    int damage = rng.nextInt(3) + 1;
                    board[teamOne[target].x][teamOne[target].y] =
                            board[teamOne[target].x][teamOne[target].y].giveDamage(damage);
                    result = ImageFactory.overlayImages(result, ImageFactory.textImage(resultPos, "-" + damage, 30, 0,
                            new Color(100, 100, 255)));
                } else {
                    result = ImageFactory.overlayImages(result, ImageFactory.textImage(resultPos, "miss", 30, 0,
                            new Color(100, 100, 255)));
                }
                return new AttackWorld(parent, MissionWorld.pause, result);
            }
            WorldImage cantAttack = ImageFactory.rectangleImage(new Posn(640, 360), 600, 40, new Color(255, 255, 255));
            cantAttack = ImageFactory.overlayImages(cantAttack, ImageFactory.textImage(new Posn(640, 360),
                    "That unit already attacked this turn!", 30, 1, new Color(0, 0, 0)));
            return new AttackWorld(parent, MissionWorld.shortPause, cantAttack);
        }
    }

    public boolean forceEnd() {
        if (turn == 0) {
            boolean end = true;
            for (int i = 0; i < teamOne.length; i++) {
                PlayingSquare unit = board[teamOne[i].x][teamOne[i].y];
                end = (unit.moves() == 0) && end;
                end = !unit.canAttack() && end;
            }
            return end;
        } else {
            boolean end = true;
            for (int i = 0; i < teamTwo.length; i++) {
                PlayingSquare unit = board[teamTwo[i].x][teamTwo[i].y];
                end = (unit.moves() == 0) && end;
                end = !unit.canAttack() && end;
            }
            return end;
        }
    }

    public Board end() {
        board[teamOne[0].x][teamOne[0].y] = board[teamOne[0].x][teamOne[0].y].reset(true);
        for (int i = 1; i < teamOne.length; i++) {
            board[teamOne[i].x][teamOne[i].y] = board[teamOne[i].x][teamOne[i].y].reset(false);
        }
        board[teamTwo[0].x][teamTwo[0].y] = board[teamTwo[0].x][teamTwo[0].y].reset(false);
        for (int i = 1; i < teamTwo.length; i++) {
            board[teamTwo[i].x][teamTwo[i].y] = board[teamTwo[i].x][teamTwo[i].y].reset(false);
        }
        return new Board(board, teamOne, teamTwo, cover, 0, 0, (turn + 1) % 2);
    }

    public boolean inBoard(Posn posn) {
        return (posn.x >= 0) && (posn.x < board.length) && (posn.y >= 0) && (posn.y < board[0].length);
    }

    public Board move(Posn posn) {
        if (turn == 0) {
            boolean legal = false;
            for (int i = 0; i < reachable.length; i++) {
                if (reachable[i].x == posn.x && reachable[i].y == posn.y) {
                    legal = true;
                    break;
                }
            }
            if (legal) {
                PlayingSquare moving = board[teamOne[selected].x][teamOne[selected].y].move(posn);
                board[teamOne[selected].x][teamOne[selected].y] = new OpenSquare(new Posn(teamOne[selected].x,
                        teamOne[selected].y));
                board[moving.getPosn().x][moving.getPosn().y] = moving;
                teamOne[selected] = moving.getPosn();
            }
            return new Board(board, teamOne, teamTwo, cover, selected, target, turn);
        } else {
            boolean legal = false;
            for (int i = 0; i < reachable.length; i++) {
                if (reachable[i].x == posn.x && reachable[i].y == posn.y) {
                    legal = true;
                    break;
                }
            }
            if (legal) {
                PlayingSquare moving = board[teamTwo[selected].x][teamTwo[selected].y].move(posn);
                board[teamTwo[selected].x][teamTwo[selected].y] = new OpenSquare(new Posn(teamTwo[selected].x,
                        teamTwo[selected].y));
                board[moving.getPosn().x][moving.getPosn().y] = moving;
                teamTwo[selected] = moving.getPosn();
            }
            return new Board(board, teamOne, teamTwo, cover, selected, target, turn);
        }
    }

    public Board updateDeath() throws BoardClearException {
        int teamOneCount = 0;
        int teamTwoCount = 0;
        for (int i = 0; i < teamOne.length; i++) {
            if (board[teamOne[i].x][teamOne[i].y].damage() <= 0) {
                board[teamOne[i].x][teamOne[i].y] = new OpenSquare(board[teamOne[i].x][teamOne[i].y].getPosn());
                teamOneCount++;
            }
        }
        for (int i = 0; i < teamTwo.length; i++) {
            if (board[teamTwo[i].x][teamTwo[i].y].damage() <= 0) {
                board[teamTwo[i].x][teamTwo[i].y] = new OpenSquare(board[teamTwo[i].x][teamTwo[i].y].getPosn());
                teamTwoCount++;
            }
        }
        Posn[] newOne = teamOne;
        if (teamOneCount > 0) {
            newOne = new Posn[teamOne.length-teamOneCount];
            int count = 0;
            for (int i = 0; i < newOne.length; i++) {
                if (board[teamOne[count].x][teamOne[count].y].unit()) {
                    newOne[i] = teamOne[count];
                    count++;
                } else {
                    count++;
                    i--;
                }
            }
        }
        Posn[] newTwo = teamTwo;
        if (teamTwoCount > 0) {
            newTwo = new Posn[teamTwo.length-teamTwoCount];
            int count = 0;
            for (int i = 0; i < newTwo.length; i++) {
                if (board[teamTwo[count].x][teamTwo[count].y].unit()) {
                    newTwo[i] = teamTwo[count];
                    count++;
                } else {
                    count++;
                    i--;
                }
            }
        }
        int newTarget = target;
        if (turn == 0) {
            if (target >= newTwo.length)
                newTarget = newTwo.length-1;
        } else {
            if (target >= newOne.length)
                newTarget = newOne.length-1;
        }
        if (newTarget < 0) {
            throw new BoardClearException();
        }
        return new Board(board, newOne, newTwo, cover, selected, newTarget, turn);
    }

    private WorldImage targetImage(Posn posn) {
        Posn pPosn = MissionWorld.arrayToPixelPos(posn);
        Color color = new Color(0, 0, 0);
        WorldImage image = ImageFactory.circleImage(pPosn, 8, color);
        image = ImageFactory.overlayImages(image, ImageFactory.rectangleImage(pPosn, 1, 16, color));
        image = ImageFactory.overlayImages(image, ImageFactory.rectangleImage(pPosn, 16, 1, color));
        return image;
    }

    private Posn[] findReachable(Posn start, int moves) {
        return removeDuplicates(recReachable(start, moves)).toArray();
    }

    private PosnList removeDuplicates(PosnList list) {
        return removeDuplicatesHelper(list, null);
    }

    private PosnList removeDuplicatesHelper(PosnList list, PosnList acc) {
        if (list == null) return acc;
        else if (acc != null && acc.member(list.first)) {
            return removeDuplicatesHelper(list.rest, acc);
        }
        else return removeDuplicatesHelper(list.rest, new PosnList(list.first, acc));
    }

    private PosnList recReachable(Posn start, int moves) {
        if (moves == 0) return new PosnList(start, null);
        else {
            PosnList list = new PosnList(start, null);
            Posn left = new Posn(start.x - 1, start.y);
            Posn right = new Posn(start.x + 1, start.y);
            Posn up = new Posn(start.x, start.y-1);
            Posn down = new Posn(start.x, start.y+1);
            if (start.x > 0 && !board[left.x][left.y].collides()) {
                list = list.append(recReachable(left, moves - 1));
            }
            if (start.x < board.length - 1 && !board[right.x][right.y].collides()) {
                list = list.append(recReachable(right, moves - 1));
            }
            if (start.y > 0 && !board[up.x][up.y].collides()) {
                list = list.append(recReachable(up, moves - 1));
            }
            if (start.y < board[0].length - 1 && !board[down.x][down.y].collides()) {
                list = list.append(recReachable(down, moves - 1));
            }
            return list;
        }
    }

}
