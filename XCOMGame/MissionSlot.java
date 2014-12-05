package XCOMGame;

import javalib.worldimages.WorldImage;

public interface MissionSlot {

    //Allows for mission generation
    public boolean isEmpty();
    public boolean hasImage();
    public WorldImage getImage();
    public MissionSlot onTick();
    public boolean failure();
    public boolean hasEvent();
    public GameEvent getEvent();

}
