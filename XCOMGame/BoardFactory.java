package XCOMGame;

import javalib.worldimages.Posn;

import java.util.Random;

public class BoardFactory {

    private static final int mapX = 31;
    private static final int mapY = 17;
    private static final int numCover = 40;

    public static Board makeBoard(int teamOne, int teamTwo) {
        PlayingSquare[][] squares = new PlayingSquare[mapX][mapY];
        Posn[] posOne = new Posn[teamOne];
        Posn[] posTwo = new Posn[teamTwo];
        Posn[] cover = new Posn[numCover];
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                squares[i][j] = new OpenSquare(new Posn(i, j));
            }
        }
        for (int i = 0; i < teamOne; i++) {
            posOne[i] = new Posn(0, i+5);
            squares[0][i + 5] = new Unit(MissionWorld.unitHealth, posOne[i], 0, false, MissionWorld.movesPerTurn, true);
        }
        squares[0][5] = new Unit(MissionWorld.unitHealth, posOne[0], 0, true, MissionWorld.movesPerTurn, true);
        for (int i = 0; i < teamTwo; i++) {
            posTwo[i] = new Posn(mapX-1, i+5);
            squares[mapX-1][i + 5] = new Unit(MissionWorld.unitHealth, posTwo[i], 1, false, MissionWorld.movesPerTurn,
                                              true);
        }
        squares[mapX-1][5] = new Unit(MissionWorld.unitHealth, posTwo[0], 1, true, MissionWorld.movesPerTurn, true);
        Random rng = new Random();
        for (int i = 0; i < numCover; i++) {
            int randX = rng.nextInt(mapX);
            int randY = rng.nextInt(mapY);
            if (squares[randX][randY].collides()) i--;
            else {
                cover[i] = new Posn(randX, randY);
                squares[randX][randY] = new Cover(cover[i]);
            }
        }
        return new Board(squares, posOne, posTwo, cover, 0, 0, 0);
    }

}
