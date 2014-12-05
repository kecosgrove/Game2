package XCOMGame;

import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

import java.awt.*;

public class Ticker {

    String left;
    String right;

    public Ticker(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public Ticker() {
        this.left = "";
        this.right = "";
    }
/*
    public WorldImage getImage() {
        Posn leftPosn = new Posn(MapWorld.tickerPos.x-320, MapWorld.tickerPos.y);
        WorldImage leftImage = ImageFactory.textImage(leftPosn, left, 28, 1, new Color(255, 0, 0));
        Posn rightPosn = new Posn(MapWorld.tickerPos.x+320, MapWorld.tickerPos.y);
        WorldImage rightImage = ImageFactory.textImage(rightPosn, right, 28, 1, new Color(200, 50, 50));
        return ImageFactory.overlayImages(leftImage, rightImage);
    }
*/
    public Ticker onTick(Continent[] continents) {
        Ticker newTicker = this;
        for (int i = 0; i < continents.length; i++) {
            if (continents[i].getMission().hasEvent() &&
                continents[i].getMission().getEvent().ID == MapWorld.tickerEID) {
                newTicker = newTicker.addString(continents[i].getMission().getEvent().message);
            }
        }
        return newTicker;
    }

    private Ticker addString(String string) {
        return new Ticker(string, left);
    }

}
