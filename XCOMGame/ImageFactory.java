package XCOMGame;

import javalib.colors.IColor;
import javalib.worldimages.*;

import java.awt.*;

/**
 * Created by User on 11/19/2014.
 */
public class ImageFactory {

    public static WorldImage rectangleImage(Posn pinhole, int width, int height, Color color) {
        return new RectangleImage(pinhole, width, height, color);
    }

    public static WorldImage rectangleImage(Posn pinhole, int width, int height, IColor color) {
        return new RectangleImage(pinhole, width, height, color);
    }

    public static WorldImage textImage(Posn pinhole, String text, float size, int style, Color color) {
        return new TextImage(pinhole, text, size, style, color);
    }

    public static WorldImage textImage(Posn pinhole, String text, int size, int style, Color color) {
        return new TextImage(pinhole, text, size, style, color);
    }

    public static WorldImage textImage(Posn pinhole, String text, float size, Color color) {
        return new TextImage(pinhole, text, size, color);
    }

    public static WorldImage textImage(Posn pinhole, String text, int size, Color color) {
        return new TextImage(pinhole, text, size, color);
    }

    public static WorldImage textImage(Posn pinhole, String text, Color color) {
        return new TextImage(pinhole, text, color);
    }

    public static WorldImage textImage(Posn pinhole, String text, float size, int style, IColor color) {
        return new TextImage(pinhole, text, size, style, color);
    }

    public static WorldImage textImage(Posn pinhole, String text, int size, int style, IColor color) {
        return new TextImage(pinhole, text, size, style, color);
    }

    public static WorldImage textImage(Posn pinhole, String text, float size, IColor color) {
        return new TextImage(pinhole, text, size, color);
    }

    public static WorldImage textImage(Posn pinhole, String text, int size, IColor color) {
        return new TextImage(pinhole, text, size, color);
    }

    public static WorldImage textImage(Posn pinhole, String text, IColor color) {
        return new TextImage(pinhole, text, color);
    }

    public static WorldImage fromFileImage(Posn pinhole, String fileName) {
        return new FromFileImage(pinhole, fileName);
    }

    public static WorldImage overlayImages(WorldImage bot, WorldImage top) {
        return new OverlayImages(bot, top);
    }

    public static WorldImage diskImage(Posn pinhole, int radius, Color color) {
        return new DiskImage(pinhole, radius, color);
    }

    public static WorldImage diskImage(Posn pinhole, int radius, IColor color) {
        return new DiskImage(pinhole, radius, color);
    }

}
