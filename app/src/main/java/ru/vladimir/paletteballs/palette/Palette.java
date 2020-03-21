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
     * Takes the next color from the palette.
     * @return color.
     */
    public int getNextColor() {
        if (currentColor == this.colors.length) {
            this.currentColor = 0;
        }
        return this.colors[currentColor++];
    }

    /**
     *
     * @param numberOfColor - Number of color in the palette.
     */
    public void setCurrentColor(int numberOfColor) {
//        if (numberOfColor >= colors.length) {
//            numberOfColor %= colors.length;
//        }
        this.currentColor = numberOfColor;
    }
}
