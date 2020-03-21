package ru.vladimir.paletteballs.ball;


import android.graphics.Canvas;
import android.graphics.Paint;

import ru.vladimir.paletteballs.palette.Palette;

public class Ball {
    private float x;
    private float y;
    private float radius;
    private Paint paint;
    private Palette palette;
    public Ball(float initX, float initY, float radius, int color) {
        this.palette = new Palette();
        this.x = initX;
        this.y = initY;
        this.radius = radius;
        this.palette.setCurrentColor(color);
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
}
