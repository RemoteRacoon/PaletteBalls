package ru.vladimir.paletteballs.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

import ru.vladimir.paletteballs.ball.Ball;

public class Playground extends SurfaceView implements SurfaceHolder.Callback {

    private ArrayList<Ball> balls;
    DrawThread drawThread;
    private int width;
    private int height;
    private float viewDy;


    class DrawThread extends Thread {
        SurfaceHolder holder;

        public DrawThread(SurfaceHolder holder) {
            this.holder = holder;
        }


        @Override
        public void run() {
            while (true) {
                Canvas canvas = holder.lockCanvas();
                if (canvas == null) {
                    continue;
                }
                draw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
        }

        private void draw(Canvas canvas) {

            canvas.drawColor(Color.GRAY);
            for (Ball ball: balls) {
                float x_coord = ball.getX();
                float y_coord = ball.getY();
                if (x_coord + ball.getRadius() >= width) {
                    int deltaX_ = ball.getDeltaX();
                    ball.setDeltaX(-deltaX_);
                    ball.getPalette().setNextColor();
                }
                if (y_coord + ball.getRadius() >= height) {
                    int deltaY_ = ball.getDeltaY();
                    ball.setDeltaY(-deltaY_ );
                    ball.getPalette().setNextColor();
                }
                if (x_coord <= ball.getRadius()) {
                    int deltaX_ = ball.getDeltaX();
                    ball.setDeltaX(deltaX_ * -1);
                    ball.getPalette().setNextColor();
                }
                if (y_coord <= ball.getRadius()) {
                    int deltaY_ = ball.getDeltaY();
                    ball.setDeltaY(deltaY_ * -1);
                    ball.getPalette().setNextColor();
                }
                ball.setX(x_coord + ball.getDeltaX());
                ball.setY(y_coord + ball.getDeltaY());
                ball.draw(canvas);
            }
        }
    }


    public Playground(Context context, AttributeSet attrs) {
        super(context);
        getHolder().addCallback(this);
        balls = new ArrayList<>();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                viewDy = this.getY() - event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                this.animate()
                        .y(event.getRawY() + viewDy)
                        .setDuration(0)
                        .start();
                break;
            default:
                return false;
        }
        return true;
    }

    /**
     * How many balls to generate.
     *
     * @param numberOfBalls
     */
    private void generateBalls(int numberOfBalls) {
        Random rand = new Random();
        for (int i = 0; i < numberOfBalls; i++) {
            float radius = 40;
            float initX = rand.nextInt(width);
            if (initX < radius) {
                initX = radius + 1;
            } else if (initX + radius >= width) {
                initX -= radius;
            }
            float initY = rand.nextInt(height);
            if (initY < radius) {
                initY = radius + 1;
            } else if (initY + radius >= height) {
                initY -= radius;
            }
            Ball ball = new Ball(initX, initY, radius, i);
            balls.add(ball);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
       this.width = width;
       this.height = height;
       generateBalls(2);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(holder);
        drawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public SurfaceHolder getHolder() {
        return super.getHolder();
    }
}


