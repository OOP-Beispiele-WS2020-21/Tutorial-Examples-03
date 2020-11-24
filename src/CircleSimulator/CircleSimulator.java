package CircleSimulator;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

import java.util.Random;

public class CircleSimulator extends GraphicsApp {

    private static final int CANVAS_WIDTH = 1280;
    private static final int CANVAS_HEIGHT = 720;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;
    private static final Color STOPPED_CIRCLE_COLOR = Colors.GREY;
    private static final int DEFAULT_NUMBER_OF_CIRCLES = 10;
    private static final int DEFAULT_CIRCLE_RADIUS = 25;
    private static final int DEFAULT_CIRCLE_SPEED_X = 5; // pixel per frame
    private static final int DEFAULT_CIRCLE_SPEED_Y = 0; // pixel per frame

    private Circle[] circles;

    @Override
    public void initialize() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        initalizeCircles();
    }

    private void initalizeCircles() {
        circles = new Circle[DEFAULT_NUMBER_OF_CIRCLES];
        for (int i = 0; i < circles.length; i++) {
            circles[i] = createRandomCircle(CANVAS_WIDTH / 2);
        }
    }

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        for (int i = 0; i < circles.length; i++) {
            updateAndDrawCircle(circles[i]);
        }
    }

    private void updateAndDrawCircle(Circle circle) {
        boolean collisionDetected = doesCollideWithWall(circle);
        if (collisionDetected) {
            circle.setColor(STOPPED_CIRCLE_COLOR);
        } else {
            circle.move(DEFAULT_CIRCLE_SPEED_X, DEFAULT_CIRCLE_SPEED_Y);
        }
        circle.draw();
    }

    private Circle createRandomCircle(int rightBound) {
        Random random = new Random();
        int x = random.nextInt(rightBound);
        int y = random.nextInt(CANVAS_HEIGHT);
        Color color = Colors.getRandomColor();
        Circle randomCircle = new Circle(x, y, DEFAULT_CIRCLE_RADIUS, color);
        return randomCircle;
    }

    private boolean doesCollideWithWall(Circle circle) {
        float xDelta = CANVAS_WIDTH - circle.getXPos();
        return (xDelta < circle.getRadius());
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}
