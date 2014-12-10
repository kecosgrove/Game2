package XCOMGame;

import javalib.funworld.World;
import javalib.worldimages.WorldImage;

/**
 * Created by User on 12/7/2014.
 */
public class AttackWorld extends World {

    MissionWorld world;
    int duration;
    WorldImage image;

    public AttackWorld(MissionWorld world, int duration ,WorldImage image) {
        this.world = world;
        this.duration = duration;
        this.image = image;
    }

    public WorldImage makeImage() {
        return ImageFactory.overlayImages(world.makeImage(), image);
    }

    public World onTick() {
        if (world.state < 2) {
            try {
                if (duration == 0) return new MissionWorld(world.map, world.board.updateDeath(), MissionWorld.yourMove);
            } catch (BoardClearException e) {
                return new MissionWorld(world.map, world.board, MissionWorld.yourMove).completeMission();
            }
            return new AttackWorld(world, duration - 1, image);
        } else {
            try {
                if (duration == 0) return new MissionWorld(world.map, world.board.updateDeath(), MissionWorld.enemyTurn);
            } catch (BoardClearException e) {
                return new MissionWorld(world.map, world.board, MissionWorld.enemyTurn).failMission();
            }
            return new AttackWorld(world, duration - 1, image);
        }
    }

}
