package ru.vladimir.paletteballs.views;


import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import java.util.ArrayList;
import java.util.Random;

import ru.vladimir.paletteballs.MainActivity;
import ru.vladimir.paletteballs.WinActivity;
import ru.vladimir.paletteballs.shapes.Ball;
import ru.vladimir.paletteballs.shapes.Square;

public class Playground extends SurfaceView implements SurfaceHolder.Callback {

    private ArrayList<Ball> balls;
    private Square square;
    private boolean win = false;

    DrawThread drawThread;

    private int width;
    private int height;

    private float lastTouchX;
    private float lastTouchY;


    class DrawThread extends Thread {
        SurfaceHolder holder;

        public DrawThread(SurfaceHolder holder) {
            this.holder = holder;
        }


        @Override
        public void run() {
            while (true) {
                if (win) {
                    Intent intent = new Intent(getContext(), WinActivity.class);
                    getContext().startActivity(intent);
                    break;
                }
                Canvas canvas = holder.lockCanvas();

                if (canvas == null) {
                    continue;
                }

                drawBalls(canvas);
                drawSquare(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
        }

        private void drawBalls(Canvas canvas) {

            canvas.drawColor(Color.GRAY);
            for (Ball ball: balls) {
                float x_coord = ball.getX();
                float y_coord = ball.getY();
                float radius = ball.getRadius();

                if (x_coord + radius >= width) {
                    changeXDirection(ball);
                    checkWinCond();
                 }

                if (y_coord + radius >= height) {
                    changeYDirection(ball);
                    checkWinCond();
                }

                if (x_coord <= radius) {
                    changeXDirection(ball);
                    checkWinCond();
                }

                if (y_coord <= radius) {
                    changeYDirection(ball);
                    checkWinCond();
                }


                if (
                        x_coord + radius >= square.getLeft() &&
                        x_coord - radius <= square.getRight() &&
                        y_coord + radius >= square.getTop() &&
                        y_coord - radius <= square.getBottom()
                ) {
                    changeDirection(ball);
                    checkWinCond();
                }

                ball.setX(x_coord + ball.getDeltaX());
                ball.setY(y_coord + ball.getDeltaY());
                ball.draw(canvas);
            }
        }

        private void changeDirection(Ball ball) {
            int dx = ball.getDeltaX();
            int dy = ball.getDeltaY();

            ball.setDeltaX(dx * -1);
            ball.setDeltaY(dy * -1);
            ball.getPalette().setNextColor();
        }

        private void changeXDirection(Ball ball) {
            int dx = ball.getDeltaX();

            ball.setDeltaX(dx * -1);
            ball.getPalette().setNextColor();
        }

        private void changeYDirection(Ball ball) {
            int dy = ball.getDeltaY();

            ball.setDeltaY(dy * -1);
            ball.getPalette().setNextColor();
        }

        private void drawSquare(Canvas canvas) {
            float left = square.getLeft();
            float right = square.getRight();
            float top = square.getTop();
            float bottom = square.getBottom();
            Paint paint = square.getPaint();

            canvas.drawRect(left, top, right, bottom, paint);
        }

        private void checkWinCond() {
            int firstColor = balls.get(0).getPalette().getCurrentColor();
            for (int i = 1; i < balls.size(); i++) {
                int color = balls.get(i).getPalette().getCurrentColor();
                if (firstColor != color) {
                    return;
                }
            }
            win = true;

        }
    }


    public Playground(Context context, AttributeSet attrs) {
        super(context);
        getHolder().addCallback(this);
        initBalls();
    }


    /**
     * How many balls to generate.
     *
     * @param numberOfBalls
     */
    private void generateBalls(int numberOfBalls) {
        Random rand = new Random();
        float radius = 40;

        for (int i = 0; i < numberOfBalls; i++) {
            float initX = rand.nextInt(width);

            if (initX < radius) {
                initX = radius + 1;
            } else if (initX + radius >= width) {
                initX -= radius;
            } else if (initX >= square.getLeft() && initX <= square.getRight()) {
                // We have to make sure that x is not between square's one.
                initX = square.getRight() + radius;
            }

            float initY = rand.nextInt(height);

            if (initY < radius) {
                initY = radius + 1;
            } else if (initY + radius >= height) {
                initY -= radius;
            } else if (initY >= square.getTop() && initY <= square.getBottom()) {
                // We have to make sure that y is not between square's one.
                initY = square.getBottom() + radius;
            }

            Ball ball = new Ball(initX, initY, radius, i);

            balls.add(ball);
        }
    }

    public void initSquare() {
        float halfSize = 100;
        float left = width / 2f - halfSize;
        float top = height / 2f - halfSize;
        float right = width / 2f + halfSize;
        float bottom = height / 2f + halfSize;
        this.square = new Square(left, top, right, bottom);

    }

    public void initBalls() {
        balls = new ArrayList<>();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
       this.width = width;
       this.height = height;
       initSquare();
       generateBalls(3);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if (
                (x >= square.getLeft() && x <= square.getRight())
                && (y >= square.getTop() && y <= square.getBottom())
        ) {
            final int action = event.getAction();

            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    lastTouchX = x;
                    lastTouchY = y;
                    break;
                case MotionEvent.ACTION_MOVE:
                    final float dx = x - lastTouchX;
                    final float dy = y - lastTouchY;
                    float currentX = square.getX();
                    float currentY = square.getY();

                    square.setX(currentX + dx);
                    square.setY(currentY + dy);

                    lastTouchX = x;
                    lastTouchY = y;
                    break;
                default:
                    break;
            }
        }

        return true;
    }
}


