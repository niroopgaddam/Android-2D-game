
package com.example.nirup.hm14_gaddam;
//Niroop Reddy Gaddam
//L20393357
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.media.SoundPool;

public class Assets {
    static Bitmap background;
    static Bitmap foodbar;
    static Bitmap roach1;
    static Bitmap roach2;
    static Bitmap roach3;
    static Bitmap roach4;
    static Bitmap bigroach1;
    static Bitmap bigroach2;
    static Bitmap bigroach3;
    static Bitmap bigroach4;

    static int scorebar;
    static Bitmap life;
    static Bitmap pause;
    static Paint textPaint,pausePaint;
    static Bitmap startgame;
    static Bitmap highscore_img;
    static int bigbugcount;



    // States of the Game Screen
    enum GameState {
        GettingReady,	// play "get ready" sound and start timer, goto next state
        Starting,		// when 3 seconds have elapsed, goto next state
        Running, 		// play the game, when livesLeft == 0 goto next state
        GameEnding,	    // show game over message
        GameOver,		// game is over, wait for any Touch and go back to title activity screen
        GamePause,
    };
    static GameState state;		// current state of the game
    static float gameTimer;	    // in seconds
    static int livesLeft;		// 0-3
    static int stroke;
    static float bigBugWakeup;

    static SoundPool soundPool;
    static int sound_getready;
    static int sound_squish;
    static int sound_thump;
    static int sound_squish1;
    static int sound_squish2;
    static int sound_squish_big;
    static int sound_gameover;

    static Bug bigbug;
    static Bug[] bug = new Bug[5] ; // try using an array of bugs instead of only 1 bug (so you can have more than 1 on screen at a time)
}
