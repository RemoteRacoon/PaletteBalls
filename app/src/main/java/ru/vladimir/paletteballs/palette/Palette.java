package ru.vladimir.paletteballs.palette;

import android.graphics.Color;


/**
 * Cyclic palette to assign color to a ball.
 */
public class Palette {
    private int[] colors;
    private int currentColor = 0;

    /**
     * Initialize palette.
     */
    public Palette() {
        this.colors = new int[]{
                Color.RED,
                Color.rgb(233, 90, 2),
                Color.YELLOW,
                Color.GREEN,
                Color.rgb(163, 168,229),
                Color.BLUE,
                Color.MAGENTA
        };
    }


    /**
     * Get current color in the palette.
     * @return Current color.
     */
    public int getCurrentColor() {
        return this.colors[this.currentColor];
    }


    /**
     *
     * @param numberOfColor - Number of color in the palette.
     */
    public void setCurrentColor(int numberOfColor) {
        this.currentColor = numberOfColor;
    }


    /**
     * Set next color in the palette.
     */
    public void setNextColor() {
        this.currentColor++;
        if (this.currentColor >= this.colors.length) {
            this.currentColor = 0;
        }
    }
}
