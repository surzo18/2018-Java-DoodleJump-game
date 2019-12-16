package com.example.doodlejump.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.example.doodlejump.GameComponents.Constants;
import com.example.doodlejump.R;

public class MenuActivity extends Activity {

    private ImageButton startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        Constants.mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.menu_music);

        boolean isSound = Constants.options.getBoolean("sound",true);
        if(isSound == true){
            Constants.mediaPlayer.setLooping(true);
            Constants.mediaPlayer.start();
        }

    }

    public void playGame(View view){
        Constants.mediaPlayer.stop();
        Intent game = new Intent(Constants.context, GameActivity.class);
        finish();
        startActivity(game);
    }

    public void goToHighScore(View view){
        Intent hs = new Intent(Constants.context, HighScoreActivity.class);
        startActivity(hs);
    }

    public void goToSettings(View view){
        Intent s = new Intent(Constants.context, SettingsActivity.class);
        startActivity(s);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initButtons(){

        //INIT PLAY BUTTON
        startButton = (ImageButton) findViewById(R.id.playButton);

        startButton.setOnTouchListener((v, event) -> {
            Log.d("Click","ME");
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
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        initButtons();
        Log.d("resume","test");
    }

}
