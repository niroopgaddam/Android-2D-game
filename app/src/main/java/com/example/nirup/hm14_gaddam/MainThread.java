package com.example.nirup.hm14_gaddam;
//Niroop Reddy Gaddam
//L20393357
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

import java.util.Random;

public class MainThread extends Thread {
    private SurfaceHolder holder;
    private Handler handler;        // required for running code in the UI thread
    private boolean isRunning = false;
    Context context;
    float pausetime;
    Paint paint;
    int touchx, touchy;    // x,y of touch event
    boolean touched;    // true if touch happened
    boolean data_initialized;
    private static final Object lock = new Object();
    float currentTime;
    Random rand;
    MainView mainView;

    public MainThread(SurfaceHolder surfaceHolder, Context context) {
        holder = surfaceHolder;
        this.context = context;
        handler = new Handler();
        data_initialized = false;
        touched = false;
      //  this.mainView=mainView;

        rand = new Random();
    }

    public void setRunning(boolean b) {
        isRunning = b;    // no need to synchronize this since this is the only line of code to writes this variable
    }

    // Set the touch event x,y location and flag indicating a touch has happened
    public void setXY(int x, int y) {
        synchronized (lock) {
            touchx = x;
            touchy = y;
            this.touched = true;
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            // Lock the canvas before drawing
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                // Perform drawing operations on the canvas
                render(canvas);
                // After drawing, unlock the canvas and display it
                holder.unlockCanvasAndPost(canvas);
            }


        }
    }

    // Loads graphics, etc. used in game
    private void loadData(Canvas canvas) {
        Bitmap bmp;
        int newWidth, newHeight;
        float scaleFactor;

        // Create a paint object for drawing vector graphics
        paint = new Paint();

        // ADD CODE HERE

        // Load food_bar
        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.food_bar);
        // Compute size of bitmap needed (suppose want height = 10% of screen height)
        newHeight = (int) (canvas.getHeight() * 0.1f);
        // Scale it to a new size
        Assets.foodbar = Bitmap.createScaledBitmap(bmp, canvas.getWidth(), newHeight, false);
        // Delete the original
        bmp = null;

        // Load roach1
        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bug5_0);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int) (canvas.getWidth() * 0.28f);
        // What was the scaling factor to get to this?
        scaleFactor = (float) newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int) (bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.roach1 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        // Load roach1
        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bug5_1);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int) (canvas.getWidth() * 0.28f);
        // What was the scaling factor to get to this?
        scaleFactor = (float) newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int) (bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.roach2 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        Assets.bigbugcount = 0;

        // Load roach1
        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bug5_2);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int) (canvas.getWidth() * 0.28f);
        // What was the scaling factor to get to this?
        scaleFactor = (float) newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int) (bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.roach3 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;


        // Load roach3 (dead bug)
        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bug5_4);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int) (canvas.getWidth() * 0.28f);
        // What was the scaling factor to get to this?
        scaleFactor = (float) newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int) (bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.roach4 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bug5_1);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int) (canvas.getWidth() * 0.45f);
        // What was the scaling factor to get to this?
        scaleFactor = (float) newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int) (bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.bigroach1 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bug5_2);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int) (canvas.getWidth() * 0.45f);
        // What was the scaling factor to get to this?
        scaleFactor = (float) newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int) (bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.bigroach2 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bug5_3);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int) (canvas.getWidth() * 0.45f);
        // What was the scaling factor to get to this?
        scaleFactor = (float) newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int) (bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.bigroach3 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bug5_4);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int) (canvas.getWidth() * 0.45f);
        // What was the scaling factor to get to this?
        scaleFactor = (float) newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int) (bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.bigroach4 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.rounded_pause_button);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int) (canvas.getWidth() * 0.1f);
        // What was the scaling factor to get to this?
        scaleFactor = (float) newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int) (bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.pause = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;


        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.life_icon);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int) (canvas.getWidth() * 0.05f);
        // What was the scaling factor to get to this?
        scaleFactor = (float) newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int) (bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.life = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        Assets.textPaint = new Paint();
        Assets.textPaint.setARGB(255, 102, 54, 0);
        Assets.textPaint.setTextAlign(Paint.Align.LEFT);
        Assets.textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Assets.textPaint.setFakeBoldText(true);
        Assets.textPaint.setTextSize(30);
        Assets.textPaint.setStrokeWidth(3);

        Assets.pausePaint = new Paint();
        Assets.pausePaint.setARGB(255, 102, 54, 0);
        Assets.pausePaint.setTextAlign(Paint.Align.CENTER);
        Assets.pausePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Assets.pausePaint.setFakeBoldText(true);
        Assets.pausePaint.setTextSize(30);
        Assets.pausePaint.setStrokeWidth(3);

        // Create a bug
        for(int i =0; i < Assets.bug.length;i++){
            Assets.bug[i] = new Bug();
        }


        Assets.bigbug = new Bug();
    }

    // Load specific background screen
    private void loadBackground(Canvas canvas, int resId) {
        // Load background
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), resId);
        // Scale it to fill entire canvas
        Assets.background = Bitmap.createScaledBitmap(bmp, canvas.getWidth(), canvas.getHeight(), false);
        // Delete the original
        bmp = null;
    }



    private void render(Canvas canvas) {
        int i, x, y;

        if (!data_initialized) {
            loadData(canvas);
            data_initialized = true;
        }

        switch (Assets.state) {
            case GettingReady:
                loadBackground(canvas, R.drawable.background);
                // Draw the background screen
                canvas.drawBitmap(Assets.background, 0, 0, null);
                // Play a sound effect
                Assets.soundPool.play(Assets.sound_getready, 1, 1, 1, 0, 1);

                // Start a timer
                Assets.gameTimer = System.nanoTime() / 1000000000f;
                // Go to next state
                Assets.state = Assets.GameState.Starting;
                break;
            case Starting:
                // Draw the background screen
                canvas.drawBitmap(Assets.background, 0, 0, null);
                // Has 3 seconds elapsed?
                currentTime = System.nanoTime() / 1000000000f;
                if (currentTime - Assets.gameTimer >= 3){
                    // Goto next state
                    Assets.state = Assets.GameState.Running;
                    Assets.bigBugWakeup =System.nanoTime() / 1000000000f;
                }

                break;
            case Running:
                // Draw the background screen
                canvas.drawBitmap(Assets.background, 0, 0, null);
                // Draw the score bar at top of screen

                canvas.drawBitmap(Assets.pause, canvas.getWidth() / 2 - Assets.pause.getWidth()/2, (canvas.getHeight() * 0.02f), null);


                canvas.drawText("SCORE : " + Assets.scorebar, canvas.getWidth() * 0.05f, (canvas.getHeight() * 0.05f) + 5, Assets.textPaint);


                // Draw the foodbar at bottom of screen
                canvas.drawBitmap(Assets.foodbar, 0, canvas.getHeight() - Assets.foodbar.getHeight(), null);
                // Draw one circle for each life at top right corner of screen
                // Let circle radius be 5% of width of screen
                int radius = (int) (canvas.getWidth() * 0.05f);
                int spacing = 5; // spacing in between circles
                x = canvas.getWidth() - radius - spacing;    // coordinates for rightmost circle to draw
                y = radius + spacing;
                for (i = 0; i < Assets.livesLeft; i++) {

                    canvas.drawBitmap(Assets.life, x, y, null);
                    x -= (radius * 2 + spacing);
                }

                // Process a touch
                if (touched) {
                    // Set touch flag to false since we are processing this touch now
                    touched = false;
                    // See if this touch killed a bug
                    boolean bugKilled = Assets.bug[0].touched(canvas, touchx, touchy, false);
                    boolean bugKilled1 = Assets.bug[1].touched(canvas, touchx, touchy, false);
                    boolean bugKilled2 = Assets.bug[2].touched(canvas, touchx, touchy, false);
                    boolean bugKilled3 = Assets.bug[3].touched(canvas, touchx, touchy, false);
                    boolean bugKilled4 = Assets.bug[4].touched(canvas, touchx, touchy, false);


                    boolean bugKilledB = Assets.bigbug.touched(canvas, touchx, touchy, true);
                    if (bugKilled || bugKilled1 || bugKilled2 || bugKilled3 || bugKilled4)
                    {

                        int value = rand.nextInt(3);


                        Assets.scorebar++;
                        if(value <= 1)
                        {
                            Assets.soundPool.play(Assets.sound_squish, 1, 1, 1, 0, 1);

                        }

                        else if( value == 2)
                        {
                            Assets.soundPool.play(Assets.sound_squish1, 1, 1, 1, 0, 1);

                        }
                        else if( value == 3)
                        {
                            Assets.soundPool.play(Assets.sound_squish2, 1, 1, 1, 0, 1);
                        }


                    } else if (bugKilledB)
                    {
                        Assets.soundPool.play(Assets.sound_squish_big, 1, 1, 1, 0, 1);
                        Assets.scorebar = Assets.scorebar + 10;
                    } else
                    {
                        float dis = (float) (Math.sqrt((touchx - canvas.getWidth() / 2 - Assets.pause.getWidth()/2) * (touchx - canvas.getWidth() / 2 - Assets.pause.getWidth()/2) + (touchy - (canvas.getHeight() * 0.02f) - Assets.pause.getHeight()/2) * (touchy - (canvas.getHeight() * 0.02f)- Assets.pause.getHeight()/2)));
                        if (dis < Assets.pause.getWidth() * 0.5f) {
                            Assets.state = Assets.GameState.GamePause;
                            currentTime = System.nanoTime() / 1000000000f;
                            break;

                        } else
                        {
                            Assets.soundPool.play(Assets.sound_thump, 1, 1, 1, 0, 1);
                        }
                    }

                }

                for(int j =0;Assets.bug.length > j;j++){
                    // Draw dead bugs on screen
                    Assets.bug[j].drawDead(canvas, false);
                    // Move bugs on screen
                    Assets.bug[j].move(canvas, false, pausetime);
                    // Bring a dead bug to life?
                    Assets.bug[j].birth(canvas);
                }


                Assets.bigbug.drawDead(canvas, true);
                Assets.bigbug.move(canvas, true, pausetime);
                if( System.nanoTime()/ 1000000000f -Assets.bigBugWakeup -pausetime  > 10){
                    Assets.bigbug.birth(canvas);
                    if(Assets.bigbugcount < 30)
                    Assets.bigbugcount++;
                    Assets.bigBugWakeup = System.nanoTime()/1000000000f;
                }
                int val = (int)(System.nanoTime()/ 1000000000f - Assets.bigBugWakeup) ;
            //    Log.e("===="," "+val);

                pausetime = 0;
                // Are no lives left?
                if (Assets.livesLeft <= 0)
                    // Goto next state
                    Assets.state = Assets.GameState.GameEnding;
                break;
            case GameEnding:
                // Show a game over message

                handler.post(new Runnable() {
                    public void run()
                    {

                        Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT).show();
                        MainView.gameOnsound.stop();
                        MainView.gameOnsound.release();
                        Assets.soundPool.play(Assets.sound_gameover, 1, 1, 1, 0, 1);
                        SharedPreferences sharedPref = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
                        int highScore = sharedPref.getInt("HIGH_SCORE_KEY", 0);
                        if (highScore < Assets.scorebar) {
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putInt("HIGH_SCORE_KEY", Assets.scorebar);
                            editor.commit();
                            Toast.makeText(context, "NEW HIGH SCORE " + Assets.scorebar, Toast.LENGTH_SHORT).show();
                        }
                        Assets.scorebar = 0;
                    }
                });
                // Goto next state
                Assets.state = Assets.GameState.GameOver;
                break;
            case GameOver:
                // Fill the entire canvas' bitmap with 'black'

                canvas.drawColor(Color.BLACK);
                break;
            case GamePause:

                canvas.drawText("----- PAUSED ----- ", canvas.getWidth() / 2, canvas.getHeight() / 2, Assets.pausePaint);

                while(true){

                    if (touched) {
                        touched = false;
                        float dis = (float) (Math.sqrt((touchx - canvas.getWidth() / 2 - (Assets.pause.getWidth()/2)) * (touchx - canvas.getWidth() / 2 - (Assets.pause.getWidth()/2)) + (touchy - (canvas.getHeight() * 0.02f) - (Assets.pause.getHeight()/2)) * (touchy - (canvas.getHeight() * 0.02f) - (Assets.pause.getHeight()/2))));
                        if (dis < Assets.pause.getWidth() * 0.5f) {
                            Assets.state = Assets.GameState.Running;

                            float ready = System.nanoTime() / 1000000000f;

                            while (true) {

                                if ((System.nanoTime() / 1000000000f) - ready < 1) {
                                    Log.e("==second 1","==");
                                 //   canvas.drawColor(Color.RED);
                                } else if ((System.nanoTime() / 1000000000f) - ready < 2) {

                                    Log.e("==second 2","==");
                                 //   canvas.drawColor(Color.YELLOW);
                                } else if ((System.nanoTime() / 1000000000f) - ready < 3) {

                                    Log.e("==second 3","==");
                                 //   canvas.drawColor(Color.GREEN);
                                } else {
                                    Assets.state = Assets.GameState.Running;
                                    pausetime = System.nanoTime() / 1000000000f - currentTime;
                                    Log.e("=====","--running");
                                    break;
                                }

                            }

                        }

                    }
                    if(!touched)
                    break;
                }

        }

    }
}
