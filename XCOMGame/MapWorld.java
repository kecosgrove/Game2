package XCOMGame;

import javalib.funworld.World;
import javalib.worldimages.*;

public class MapWorld extends World {

    private static final int numContinents = 6;
    private static final double timeRoC = 3.5;

    protected static final int missionRate = 10;
    protected static final int missionDuration = 50;
    protected static final int screenHeight = 1280;
    protected static final int screenWidth = 720;
    protected static final WorldImage image =
            ImageFactory.fromFileImage(new Posn(960, 540), "images\\map.png");
    protected static final Posn datePosn = new Posn(640, 680);
    protected static final Posn NAPos = new Posn(280, 310);
    protected static final Posn SAPos = new Posn(430, 550);
    protected static final Posn EUPos = new Posn(690, 250);
    protected static final Posn AFPos = new Posn(730, 500);
    protected static final Posn OCPos = new Posn(1110, 570);
    protected static final Posn ASPos = new Posn(950, 330);
    protected static final DateContainer start = new DateContainer(2015, 60, 0, 0);

    protected static final int panicEID = 1;
    protected static final int tickerEID = 2;
    protected static final int panicRate = 2;

    DateContainer date;
    Continent[] continents;

    public MapWorld(DateContainer date, Continent[] continents) {
        if (continents.length != numContinents) {
            throw new RuntimeException("Array incorrectly initialized");
        }
        this.date = date;
        this.continents = continents;
    }

    //Todo: Test the GameOverWorld world
    public World onTick() {
        Continent[] newContinents = continents;
        boolean failure = true;
        for (int i = 0; i < continents.length; i++) {
            newContinents[i] = newContinents[i].onTick();
            failure = newContinents[i].failure() && failure;
        }
        if (failure) {
            return new GameOverWorld(date, MapWorld.start);
        }
        return new MapWorld(date.hourAdvance(timeRoC), newContinents);
    }

    public WorldImage makeImage() {
        WorldImage image = this.image;
        image = ImageFactory.overlayImages(image, date.getImage());
        //image = ImageFactory.overlayImages(image, ticker.getImage());
        for (int i = 0; i < continents.length; i++) {
            image = ImageFactory.overlayImages(image, continents[i].getImage());
        }
        return image;
    }

}
