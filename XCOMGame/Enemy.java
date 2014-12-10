package XCOMGame;

import javalib.funworld.World;
import javalib.worldimages.WorldImage;

/**
 * Created by User on 12/3/2014.
 */
public class Enemy {

    private static int aim = -1;

    //Todo: Finish this. (Last thing?)
    public static World makeMove(MissionWorld world) {
        if (world.state != MissionWorld.enemyTurn) throw new RuntimeException("Enemy cannot make move!");
        Board board = world.board;
        PlayingSquare unit = board.board[board.teamTwo[board.selected].x][board.teamTwo[board.selected].y];
        if (unit.moves() > 0) {
            //move
            int[] moveScore = new int[board.reachable.length];
            for (int i = 0; i < moveScore.length; i++) {
                moveScore[i] = 0;
                for (int j = 0; j < board.teamOne.length; j++) {
                    if (Board.hasCover(board.teamOne[j], board.reachable[i], board.board)) {
                        moveScore[i] = moveScore[i] + 1;
                    }

                    if (Board.hasCover(board.reachable[i], board.teamOne[j], board.board)) {
                        moveScore[i] = moveScore[i] - 1;
                    }

                }
            }
            int bestScore = moveScore[0];
            for (int i = 1 ; i < moveScore.length; i++) {
                if (moveScore[i] > bestScore) {
                    bestScore = moveScore[i];
                }
            }
            int boardXCenter = board.board.length/2;
            int boardYCenter = board.board[0].length/2;
            for (int i = 0; i < moveScore.length; i++) {
                if (moveScore[i] == bestScore) {
                    moveScore[i] = moveScore[i] + (boardXCenter - Math.abs(board.reachable[i].x - boardXCenter));
                    moveScore[i] = moveScore[i] + (boardYCenter - Math.abs(board.reachable[i].y - boardYCenter));
                }
            }
            bestScore = moveScore[0];
            for (int i = 1 ; i < moveScore.length; i++) {
                if (moveScore[i] > bestScore) {
                    bestScore = moveScore[i];
                }
            }
            for (int i = 0; i < moveScore.length; i++) {
                if (moveScore[i] == bestScore) {
                    return new MissionWorld(world.map, board.move(board.reachable[i]), world.state);
                }
            }
        } else if (aim >= 0) {
            if (aim > 0) {
                aim--;
                return new MissionWorld(world.map, world.board.aimRight(), world.state);
            }
            else {
                aim--;
                return board.attack(world);
            }
        } else if (unit.canAttack()) {
            //attack
            double[] shootScore = new double[board.teamOne.length];
            for (int i = 0; i < shootScore.length; i++) {
                shootScore[i] = Board.genChanceToHit(unit.getPosn(), board.teamOne[i], board.board);
            }
            double bestScore = shootScore[0];
            for (int i = 1 ; i < shootScore.length; i++) {
                if (shootScore[i] > bestScore) {
                    bestScore = shootScore[i];
                }
            }
            for (int i = 0; i < shootScore.length; i++) {
                if (shootScore[i] == bestScore) {
                    aim = i;
                }
            }
            return world;
        } else {
            return new MissionWorld(world.map, world.board.rotateRight(), world.state);
        }
        return world;
    }

}
