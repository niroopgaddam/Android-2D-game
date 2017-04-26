package com.example.nirup.hm14_gaddam;
//Niroop Reddy Gaddam
//L20393357
import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

public class Bug {

    // States of a Bug
    enum BugState {
        Dead,
        ComingBackToLife,
        Alive, 			    // in the game
        DrawDead,			// draw dead body on screen
    };

    int array[] = {140,160,180,200,220,240,260,280,300,320,340,360,380,400,420,440,460,480,500,520,540,560,580,600,620,640,660,680,700,720,740};

    static int select;
    BugState state;			// current state of bug
    int x,y;				// location on screen (in screen coordinates)
    double speed;			// speed of bug (in pixels per second)
    // All times are in seconds
    float timeToBirth;		// # seconds till birth
    float startBirthTimer;	// starting timestamp when decide to be born
    float deathTime;		// time of death
    float animateTimer;		// used to move and animate the bug
    Random rand;


    // Bug starts not alive
    public Bug () {
        state = BugState.Dead;
        rand = new Random();
    }

    // Bug birth processing
    public void birth (Canvas canvas) {
        // Bring a bug to life?
        if (state == BugState.Dead) {
            // Set it to coming alive
            state = BugState.ComingBackToLife;
            // Set a random number of seconds before it comes to life
            timeToBirth = (float)Math.random () * 5;
            // Note the current time
            startBirthTimer = System.nanoTime() / 1000000000f;
        }
        // Check if bug is alive yet
        else if (state == BugState.ComingBackToLife) {
            float curTime = System.nanoTime() / 1000000000f;
            // Has birth timer expired?
            if (curTime - startBirthTimer >= timeToBirth) {
                // If so, then bring bug to life
                state = BugState.Alive;
                // Set bug starting location at top of screen
                x = (int)(Math.random() * canvas.getWidth());
                // Keep entire bug on screen
                if (x < Assets.roach1.getWidth()/2)
                    x = Assets.roach1.getWidth()/2;
                else if (x > canvas.getWidth() - Assets.roach1.getWidth()/2)
                    x = canvas.getWidth() - Assets.roach1.getWidth()/2;
                y = 0;
                // Set speed of this bug
               // Random rand = new Random();
                speed = canvas.getHeight() / 4; // no faster than 1/4 a screen per second


              speed = speed +  rand.nextInt(array[Assets.bigbugcount]);
                Log.e("==","==="+Assets.bigbugcount);
                // subtract a random amount off of this so some bugs are a little slower
                // ADD CODE HERE
                // Record timestamp of this bug being born
                animateTimer = curTime;
            }
        }
    }

    // Bug movement processing
    public void move(Canvas canvas, Boolean big, float pausetime) {
        // Make sure this bug is alive
        if (state == BugState.Alive) {
            // Get elapsed time since last call here
            float curTime = System.nanoTime() / 1000000000f;
            float elapsedTime = curTime - animateTimer - pausetime;

            animateTimer = curTime;
            // Compute the amount of pixels to move (vertically down the screen)
            y += (speed * elapsedTime);

            // Draw bug on screen

                movingrouch(canvas, y,big);

            // ADD CODE HERE - Draw each frame of animation as appropriate - don't just draw 1 frame

            // Has it reached the bottom of the screen?
            if (y >= canvas.getHeight()) {
                // Kill the bug
                if(big){
                   Assets.stroke = 0;
                }
                state = BugState.Dead;
                // Subtract 1 life
                Assets.livesLeft--;
            }
        }
    }

    // Process touch to see if kills bug - return true if bug killed
    public boolean touched(Canvas canvas, int touchx, int touchy, boolean big) {
        boolean touched = false;
        // Make sure this bug is alive
        if (state == BugState.Alive) {
            // Compute distance between touch and center of bug
            float dis = (float)(Math.sqrt ((touchx - x) * (touchx - x) + (touchy - y) * (touchy - y)));
            // Is this close enough for a kill?
            if (dis <= Assets.roach1.getWidth()*0.75f) {
                if(big){
                    Assets.stroke++;
                    if(Assets.stroke <4){
                        return false;
                    }
                    Assets.stroke=0;

                }

                state = BugState.DrawDead;	// need to draw dead body on screen for a while
                touched = true;
                // Record time of death
                deathTime = System.nanoTime() / 1000000000f;

            }
        }
        return (touched);
    }

    // Draw dead bug body on screen, if needed
    public void drawDead (Canvas canvas,Boolean big) {
        if (state == BugState.DrawDead) {
            if(big){
                canvas.drawBitmap(Assets.bigroach4, x-Assets.bigroach4.getWidth()/2,  y-Assets.bigroach4.getHeight()/2, null);
            }
            else{
                canvas.drawBitmap(Assets.roach4, x-Assets.roach1.getWidth()/2,  y-Assets.roach1.getHeight()/2, null);
            }
            // Get time since death
            float curTime = System.nanoTime() / 1000000000f;
            float timeSinceDeath = curTime - deathTime;
            // Drawn dead body long enough (4 seconds) ?
            if (timeSinceDeath > 4)
                state = BugState.Dead;
        }
    }

    public void movingrouch(Canvas canvas,int y , Boolean big){

        if(big){
            if(select <= 5 ) {
                canvas.drawBitmap(Assets.bigroach1, x-Assets.bigroach1.getWidth()/2,  y-Assets.bigroach1.getHeight()/2, null);
            }
            else  if(select > 5 && select <= 10) {
                canvas.drawBitmap(Assets.bigroach2, x-Assets.bigroach2.getWidth()/2,  y-Assets.bigroach2.getHeight()/2, null);
            }
            else  if(select > 10 ) {
                canvas.drawBitmap(Assets.bigroach3, x-Assets.bigroach3.getWidth()/2,  y-Assets.bigroach3.getHeight()/2, null);
            }
            select++;

            if(select > 15){
                select=0;
            }

        }

        else{
            if(select <= 5 ) {
                canvas.drawBitmap(Assets.roach1, x-Assets.roach1.getWidth()/2,  y-Assets.roach1.getHeight()/2, null);
            }
            else  if(select > 5 && select <= 10) {
                canvas.drawBitmap(Assets.roach2, x-Assets.roach2.getWidth()/2,  y-Assets.roach2.getHeight()/2, null);
            }
            else  if(select > 10 ) {
                canvas.drawBitmap(Assets.roach3, x-Assets.roach3.getWidth()/2,  y-Assets.roach3.getHeight()/2, null);
            }
            select++;

            if(select > 15){
                select=0;
            }
        }

        }

    }


