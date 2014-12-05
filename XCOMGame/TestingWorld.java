package XCOMGame;

import javalib.funworld.World;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

import java.awt.*;

/**
 * Created by User on 12/2/2014.
 */
public class TestingWorld extends World {

    WorldImage image;

    public TestingWorld(Continent[] continents, int continentIndex, int cityIndex) {
        Posn dotPos = continents[continentIndex].cities[cityIndex].posn;
        image = ImageFactory.diskImage(dotPos, 15, new Color(255, 0, 0));
        image = ImageFactory.overlayImages(MapWorld.image, image);
    }

    public WorldImage makeImage() {
        return image;
    }

}
