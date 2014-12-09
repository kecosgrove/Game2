package XCOMGame;

import javalib.worldimages.WorldImage;

/**
 * Created by User on 12/3/2014.
 */
public class Enemy {

    boolean[] moved;
    boolean[] shot;
    MissionWorld world;

    public Enemy(MissionWorld world) {
        this.world = world;
        moved = new boolean[world.board.teamTwo.length];
        shot = new boolean[world.board.teamTwo.length];
        for (int i = 0; i < moved.length; i++) {
            moved[i] = false;
        }
        for (int i = 0; i < shot.length; i++) {
            shot[i] = false;
        }
    }

    //Todo: Finish this. (Last thing?)
    public MissionWorld makeMove() {
        if (world.state != MissionWorld.enemyTurn) throw new RuntimeException("Enemy cannot make move!");
        return world; //temp
    }

}
