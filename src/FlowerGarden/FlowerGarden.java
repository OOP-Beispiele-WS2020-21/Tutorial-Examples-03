package FlowerGarden;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

/**
 * Diese GraphicsApp stellt eine Szene aus 10 wachsenden Blumen auf einer Wiese dar. Die Positionen (Wurzeln) der Blumen
 * werden als Point-Instanzen in einem Array gespeichert. Ausgehend von dieser Position aus werden in jedem Frame die
 * eigentlichen Blumen, bestehend aus verschiedenen graphischen Elementen, gezeichnet. Die Größe der Blumen wird aus einer
 * Variable ausgelesen, die mit jedem Aufruf der draw-Methode leicht inkrementiert wird.
 */

public class FlowerGarden extends GraphicsApp {

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
