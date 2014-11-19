package XCOMGame;

import javalib.worldimages.WorldImage;

public interface MissionSlot {

    public boolean isEmpty();
    public WorldImage getImage();
    public MissionSlot onTick();
    public boolean panicEvent();

}
