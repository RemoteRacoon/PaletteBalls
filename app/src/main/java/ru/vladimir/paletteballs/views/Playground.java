package ru.vladimir.paletteballs.views;


import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import ru.vladimir.paletteballs.ball.BallsSet;

public class Playground extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder holder;
    DrawThread drawThread;
    private int width;
    private int height;


    class DrawThread extends Thread {
        private BallsSet ballsSet;
        private float deltaX = 5f;
        private float deltaY = 6f;

        public DrawThread() {
            ballsSet = new BallsSet(2, width, height);
        }

        private void draw() {

        }

        @Override
        public void run() {
            while (true) {
                Canvas canvas = holder.lockCanvas();
                if (canvas == null) {
                    continue;
                }

            }
        }
    }

    public Playground(Context context) {
        super(context);
        getHolder().addCallback(this);
        drawThread = new DrawThread();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
       this.width = width;
       this.height = height;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public SurfaceHolder getHolder() {
        return super.getHolder();
    }
}


