package com.example.doodlejump;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends Activity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final ImageButton startButton = (ImageButton) findViewById(R.id.playButton);

        startButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Do something
                        startButton.setImageResource(R.drawable.play_clicked);
                        return true;
                    case MotionEvent.ACTION_UP:
                        startButton.setImageResource(R.drawable.play);
                        playGame(startButton.getRootView());
                        return true;
                }
                return false;
            }
        });

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.menu_music);
        mediaPlayer.isLooping();
        mediaPlayer.start();

    }

    public void playGame(View view){
        mediaPlayer.stop();
        Intent game = new Intent(this, GameActivity.class);
        startActivity(game);
    }
}
