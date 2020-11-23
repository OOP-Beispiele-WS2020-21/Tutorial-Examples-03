package ColorPalette;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

/**
 * Diese GraphicsApp-Anwendung stellt in jedem Frame ein Gitternetz aus mehreren farbigen Rechtecken dar. Jedes Rechteck
 * verfügt über eine zufällige Farbe. Im inneren des Rechteck wird eine aufsteigende Nummer, beginnend bei "1" angezeigt.
 */

public class ColorPalette extends GraphicsApp {

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
