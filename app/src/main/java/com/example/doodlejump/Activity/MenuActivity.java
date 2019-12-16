package com.example.doodlejump.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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

        //initButtons();
    }

    public void playGame(View view){
        Constants.mediaPlayer.stop();
        Intent game = new Intent(Constants.context, GameActivity.class);
        finish();
        startActivity(game);
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
