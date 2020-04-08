package ru.vladimir.paletteballs.shapes;

import android.graphics.Color;
import android.graphics.Paint;

public class Square {
    private float left;
    private float top;
    private float right;
    private float bottom;
    private float x;
    private float y;
    private float size;
    private boolean consistent;
    private Paint paint;

    public Square(float initLeft, float initTop, float initRight, float initBottom) {
        this.consistent = false;
        this.paint = new Paint();

        paint.setColor(Color.MAGENTA);

        this.left = initLeft;
        this.top = initTop;
        this.right = initRight;
        this.bottom = initBottom;

        this.size = initRight - initLeft;

        this.x = initRight - size / 2;
        this.y = initTop + size / 2;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        float dx = x - this.x;

        left += dx;
        right += dx;
    }

    public void setY(float y) {
        float dy = y - this.y;

        top += dy;
        bottom += dy;
    }


    public float getLeft() {
        return left;
    }

    public float getTop() {
        return top;
    }

    public float getRight() {
        return right;
    }

    public float getBottom() {
        return bottom;
    }

    public float getSize() {
        return size;
    }

    public Paint getPaint() {
        return paint;
    }
}
