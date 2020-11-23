package MovingCar;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

/**
 * Diese GraphicsApp-Anwendung stellt ein Auto aus der Top-Down-Perspektive dar, das sich über den Bildschirm bewegt
 * und zufällig seine Richtung ändert.
 */

public class MovingCar extends GraphicsApp {

    private static final int CANVAS_WIDTH = 1280;
    private static final int CANVAS_HEIGHT = 720;

    @Override
    public void initialize() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    @Override
    public void draw() {
        drawBackground(Colors.WHITE);
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }

}
