package com.example.doodlejump.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.doodlejump.View.DoodleGameView;

import java.util.Timer;
import java.util.TimerTask;

public class RectPlayer implements GameObject {

    private Point selfPosition = new Point();
    private Rect playerPosition = new Rect();

    private Bitmap playerImg;

    boolean isJumping = false;
    int jumpHeight = 800;
    int lastJump = 0;
    private int fallingSpeed = 20;
    private float miliseconds = 0;

    public RectPlayer( Bitmap playerImg, int posX, int posY){
        this.playerImg = playerImg;
        this.selfPosition.x = posX;
        this.selfPosition.y = posY;


        //TIMER
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                miliseconds += 100;
            }
        }, 0, 100);
    }

    public void collisionPlayerJumpRow(JumpRow jumpRow){
        if(!isJumping){
            if(playerPosition.intersect(jumpRow.position)){
                lastJump = jumpRow.position.top;
                isJumping = true;
                miliseconds  = 0;
            }
        }
    }



    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(playerImg,null,playerPosition,null);
    }

    @Override
    public void update() {
        if(isJumping){
            Log.d("LastJump ", String.valueOf(lastJump));
            Log.d("JumpHeight ", String.valueOf(jumpHeight));
            Log.d("PlayerBottom ", String.valueOf(playerPosition.bottom));

            if(lastJump - jumpHeight >= playerPosition.bottom){
                isJumping = false;
                miliseconds  = 0;
            }
            float second = miliseconds /1000;
            selfPosition.y -= fallingSpeed + (9.81 * (second * second))/2;
            playerPosition.set(selfPosition.x - playerImg.getWidth()/2, selfPosition.y - playerImg.getHeight()/2, selfPosition.x + playerImg.getWidth()/2, selfPosition.y + playerImg.getHeight()/2);
        }
        else{
            float second = miliseconds /1000;
            selfPosition.y += fallingSpeed + (9.81 * (second * second))/2;
            playerPosition.set(selfPosition.x - playerImg.getWidth()/2, selfPosition.y - playerImg.getHeight()/2, selfPosition.x + playerImg.getWidth()/2, selfPosition.y + playerImg.getHeight()/2);
        }

    }

    public void update(Point point){
    }
}
