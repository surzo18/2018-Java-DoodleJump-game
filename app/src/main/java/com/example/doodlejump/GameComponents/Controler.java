package com.example.doodlejump.GameComponents;


import android.view.MotionEvent;

import com.example.doodlejump.Objects.Player;

public class Controler {

    Player player;
    public Controler(Player player){
        this.player = player;
    }

    public void makeMove(MotionEvent event) {
        float x = event.getX();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if (Constants.SCREEN_WIDTH / 2 >= x)
                    { player.moveLeft(); }
                else
                    { player.moveRight(); }
                break;
        }
    }

    public void moveLeft(){
        player.moveLeft();
    }

    public void moveRight(){
        player.moveRight();
    }
}
