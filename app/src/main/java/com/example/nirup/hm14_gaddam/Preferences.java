package com.example.nirup.hm14_gaddam;
//Niroop Reddy Gaddam
//L20393357
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Preferences extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high);

        TextView score = (TextView)findViewById(R.id.besthighscore);
        SharedPreferences sharedPref = getSharedPreferences("sp", Context.MODE_PRIVATE);
        final int highScore = sharedPref.getInt("HIGH_SCORE_KEY", 0);
        score.append(": "+highScore);
        TextView share = (TextView)findViewById(R.id.share_score);

        share.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(highScore>0)
                {
                    shareScore(String.valueOf(highScore));
                }
            }
        });
    }
    private void shareScore(String score)
    {
        String shareBody = "My Score is :"+score;
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent,"Score"));
    }


}
