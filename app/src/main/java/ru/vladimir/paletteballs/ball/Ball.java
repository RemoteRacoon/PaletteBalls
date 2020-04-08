package ru.vladimir.paletteballs.ball;


import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

import ru.vladimir.paletteballs.palette.Palette;

public class Ball {
    private float x;
    private float y;
    private int deltaX;
    private int deltaY;
    private float radius;
    private Paint paint;
    private Palette palette;


    public Ball(float initX, float initY, float radius, int color) {
        Random rand = new Random();
        this.paint = new Paint();
        this.palette = new Palette();
        this.x = initX;
        this.y = initY;
        this.deltaX = 4 + rand.nextInt(10);
        this.deltaY = 4 + rand.nextInt(10);
        this.radius = radius;
        this.palette.setCurrentColor(color);
    }


    /**
     * Draw ball on the canvas.
     * @param canvas
     */
    public void draw(Canvas canvas) {
        this.paint.setColor(palette.getCurrentColor());
        canvas.drawCircle(this.x, this.y, this.radius, paint);
    }


    public Palette getPalette() {
        return this.palette;
    }


    /**
     * Get x coordinate.
     * @return Current x coordinate
     */
    public float getX() {
        return this.x;
    }


    /**
     * Get y coordinate.
     *
     * @return Current y coordinate
     */
    public float getY() {
        return this.y;
    }


    /**
     *
     * @return
     */
    public int getDeltaX() {
        return this.deltaX;
    }

    /**
     *
     * @return
     */
    public int getDeltaY() {
        return this.deltaY;
    }


    /**
     *
     * @return
     */
    public float getRadius() {
        return this.radius;
    }


    /**
     * Set x coordinate.
     *
     * @param x - X coordinate
     */
    public void setX(float x) {
        this.x = x;
    }


    /**
     * Set y coordinate.
     *
     * @param y - Y coordianate
     */
    public void setY(float y) {
        this.y = y;
    }


    /**
     *
     * @param deltaX
     */
    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }


    /**
     *
     * @param deltaY
     */
    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }
}
