package CircleSimulator;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

import java.util.Random;


/**
 * In dieser Simulation werden n zufällig positioniert und eingefärbte Kreise im ersten Viertel
 * der Zeichenfläche positioniert. Die Kreise bewegen sich mit einer festen Geschwindigkeit auf der x-Achse
 * in Richtung des rechten Bildschirmrands. Sobald ein Kreis diesen erreicht haben, wird die Bewegung
 * gestoppt und die Farbe des Kreis wird auf Grau gesetzt.
 */
public class CircleSimulator extends GraphicsApp {

    /**
     * Wichtige Parameter der Simulation werden in Form von Konstanten angelegt und verwaltet. Innerhalb
     * des Codes werden nur diese Konstanten für die Parametrisierung der Simulation verwendet. Ablauf und
     * Erscheinungsbild können so zentral, über die Manipulation dieser Werte konfiguriert werden.
     */

    // Breite der Zeichenfläche
    private static final int CANVAS_WIDTH = 1280;
    // Höhe der Zeichenfläche
    private static final int CANVAS_HEIGHT = 720;
    // Rechte Grenze für den Bereich, in dem neue Kreise erscheinen
    private static final int MAX_X_POSITION_FOR_NEW_CIRCLES = CANVAS_WIDTH / 4;
    // Radius der neu erstellten Kreise
    private static final int DEFAULT_CIRCLE_RADIUS = 10;
    // Anzahl der zu simulierenden Kreise
    private static final int DEFAULT_CIRCLE_NUMBER = 50;
    // Bewegungsgeschwindigkeit der Kreise auf der X-Achse (in Pixel pro Frame)
    private static final int DEFAULT_CIRCLE_SPEED_X = 4;
    // Bewegungsgeschwindigkeit der Kreise auf der Y-Achse (in Pixel pro Frame)
    private static final int DEFAULT_CIRCLE_SPEED_Y = 0;
    // Hintergrundfarbe für die Zeichenfläche
    private static final Color BACKGROUND_COLOR = Colors.WHITE;
    // Farbe, mit der Kreise gekennzeichnet werden sollen, die den rechten Rand erreicht haben
    private static final Color STOPPED_CIRCLE_COLOR = Colors.GREY;

    // Array zur Verwaltung der verschiedenen Kreise
    private Circle[] circles;


    /**
     * Die initialize-Methode wird einmalig beim Programmstart aufgerufen. Hier richten wir die
     * Größe der Zeichenfläche ein und erstellen die zufällig parametrisierten Kreise.
     */
    @Override
    public void initialize() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        // Das Befüllen des Arrays ist in eine Methode ausgelagert
        initializeCircles();
    }

    /**
     * Erstellt und Befüllt das Array circles mit zufälligen Kreisen
     */
    private void initializeCircles() {
        // Initalisierung des Arrays für die Kreise (die Länge wird aus DEFAULT_CIRCLE_NUMBER ausgelesen)
        circles = new Circle[DEFAULT_CIRCLE_NUMBER];
        // Erstellen des Zufallsgenerators der für die randomisierte Generierung der Kreise verwendet wird
        Random randomGenerator = new Random();
        // Hier wird über alle Zellen des Arrays iteriert ...
        for (int i = 0; i < circles.length; i++) {
            // ... und an jeder Position ein neuer, zufällig generierter Kreis eingefügt
            circles[i] = createRandomCircle(randomGenerator, MAX_X_POSITION_FOR_NEW_CIRCLES, CANVAS_HEIGHT);
        }
    }

    /**
     * Erstellt auf Basis der übergebenen Parameter ein zufällig positioniertes Circle-Objekt und gibt dieses zurück
     * randomGenerator: Zufallsgenerator der für das zufällige Bestimmen der Position verwendet werden soll
     * maxXPosition: maximaler Wert für die X-Position des neuen Kreises
     * maxYPosition: maximaler Wert für die Y-Position des neuen Kreises
     */
    private Circle createRandomCircle(Random randomGenerator, int maxXPosition, int maxYPosition) {
        /** Zufällige Auswahl der X- und Y-Positionen durch Anfragen der jeweils nächsten Zufallszahl aus dem Generator:
         * Der Parameter der nextInt-Methode gibt die obere Grenze des Bereichs an, aus dem die Zufallszahl gezogen werden
         * soll. Dieser Wert ist exklusiv, d.h. diese Grenze kann bei der Auswahl NICHT erreicht werden.
         */
        int randomXPosition = randomGenerator.nextInt(maxXPosition + 1);
        int randomYPosition = randomGenerator.nextInt(maxYPosition + 1);
        // Zufällige Auswahl einer Farbe
        Color randomColor = Colors.getRandomColor();
        // Erstellen des Kreises mit den generierten Zufallswerten und Rückgabe des neuen Objekts als Ergebnis der Methode
        return new Circle(randomXPosition, randomYPosition, DEFAULT_CIRCLE_RADIUS, randomColor);
    }

    /**
     * Die draw-Methode wird in regelmäßigen Abständen (ca. 60x pro Sekunde) aufgerufen, während das Programm läuft.
     */
    @Override
    public void draw() {
        // Zeichnen des Hintergrunds
        drawBackground(BACKGROUND_COLOR);
        // Bewegen und Aktuallisieren aller Kreise
        drawAndUpdateAllCircles();
    }

    private void drawAndUpdateAllCircles() {
        /**
         * Wenn Sie über JEDES Element eines Arrays iterieren wollen, ohne einzelne Stellen neu belegen
         * zu müssen, können Sie statt einer klassischen for-Schleife diese for each-Schleife verwenden.
         * Wir iterieren über alle Kreise ...
         */
        for (Circle circle : circles) {
            /**
             * ... und übergeben jedes Kreis-Objekt als Parameter an die Methode, die das Bewegen und Zeichen
             * für einen einzelnen Kreis implementiert.
             */
            drawAndUpdateCircle(circle);
        }
    }

    /**
     * Diese Methode arbeitet mit einem als Parameter übergebenen Kreis und:
     * - prüft immer die Distanz zwischen dem Mittelpunkt des Kreises und der rechten Grenze der Zeichenfläche
     * - bewegt den Kreis nach Rechts, wenn diese Distanz noch größer als der Radius des Kreises ist
     * - ändert die Kreisfarbe zu grau, wenn dieser den rechten Rand erreicht hat
     * - zeichnet am Ende immer den Kreis an dessen aktueller Position und mit der aktuellen Farbe auf die Zeichenfläche
     */
    private void drawAndUpdateCircle(Circle circle) {
        // Berechnung der Distanz zwischen Mittelpunkt und rechtem Rand
        float distanceToRightBorder = CANVAS_WIDTH - circle.getXPos();
        // Wir prüfen, ob der Kreis den Rand bereits erreicht hat ...
        if (distanceToRightBorder <= circle.getRadius()) {
            // ... ändern ggf. die Farbe des Kreises ...
            circle.setColor(STOPPED_CIRCLE_COLOR);
        } else {
            // ... ODER bewegen ihn weiter in Richtung Rand.
            circle.move(DEFAULT_CIRCLE_SPEED_X, DEFAULT_CIRCLE_SPEED_Y);
        }
        // Der Kreis wird in jedem Fall am Ende der Methode gezeichnet
        circle.draw();
    }

    // Einstiegspunkt in unsere Program: Hier wird unsere GraphicsApp, die in dieser Klasse definiert wurde, gestartet.
    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}