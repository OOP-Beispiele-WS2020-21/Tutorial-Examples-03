package RandomBalls;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

/**
 * In dieser GraphicsApp-Anwendung werden 100 zufällig platzierte Kreise animiert. Ausgehend von ihrer Startposition
 * bewegen sich je 25% der Kreise nach oben rechts, unten rechts, unten links und oben links. Die Kreise werden in einem
 * Arrays verwaltet.
 */

public class RandomBalls extends GraphicsApp {

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
