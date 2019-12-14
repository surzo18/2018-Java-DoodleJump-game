package com.example.doodlejump.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.doodlejump.View.Game;

import static com.example.doodlejump.GameComponents.Constants.context;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("aaa","bbb");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new Game(this));
    }

    public void switchScene(){
        Intent menu = new Intent(context, MainActivity.class);
        finish();
        startActivity(menu);
    }
}
