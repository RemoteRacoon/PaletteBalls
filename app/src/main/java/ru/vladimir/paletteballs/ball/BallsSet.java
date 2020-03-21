package ru.vladimir.paletteballs.ball;


import java.util.Random;

/**
 * Class describing set of balls to draw on canvas.
 */
public class BallsSet {
    public Ball[] balls;

    /**
     * @param numberOfBalls - How many balls to generate.
     * @param width - Canvas' width.
     * @param height  Canvas' height.
     */
    public  BallsSet(int numberOfBalls, int width, int height) {
        Random rand = new Random();
        for (int i = 0; i < numberOfBalls; i++) {
            int initX = rand.nextInt(width);
            int initY = rand.nextInt(height);

            Ball ball = new Ball(initX, initY, 40, i);
            balls[i] = ball;
        }
    }
}
