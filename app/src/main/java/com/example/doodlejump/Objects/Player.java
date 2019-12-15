package com.example.doodlejump.Objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.example.doodlejump.GameComponents.Constants;
import com.example.doodlejump.R;

import java.util.Timer;
import java.util.TimerTask;


public class Player implements GameObject {

    //PlayerImg
    private Bitmap currentImage;
    private Bitmap playerImgLeft;
    private Bitmap playerImgRight;
    private Bitmap playerImgTop;
    private Bitmap playerImgNoose;

    //PlayerPhysic
    private Point position; //Center of image
    private Rect playerColliderBox = new Rect();

    boolean isJumping = false;
    boolean dead = false;
    private int speed = 35;
    private int miliseconds = 0;
    private int jumpHeight = 700;
    private int jumpProgress = 0;

    private int playerMaxInt = 0;
    /*
    private float miliseconds = 0;
    private int speed = 35;
    int jumpHeight = 200;
    int lastJump = 50;
    */

    public Player(Point position){

        this.position = position;

        //Init resources
        this.playerImgLeft  = BitmapFactory.decodeResource(Constants.context.getResources(),R.drawable.left_doodle);
        this.playerImgRight = BitmapFactory.decodeResource(Constants.context.getResources(),R.drawable.right_doodle);
        this.playerImgTop   = BitmapFactory.decodeResource(Constants.context.getResources(),R.drawable.up_doodle);
        this.playerImgNoose = BitmapFactory.decodeResource(Constants.context.getResources(),R.drawable.nose_doodle);
        this.currentImage   = playerImgLeft;

        //TIMER
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                miliseconds += 1;
            }
        }, 0, 1);
    }

    public void moveLeft(){
        this.currentImage = playerImgLeft;
        this.position.x -= speed;
    }

    public void moveRight(){
        this.currentImage = playerImgRight;
        this.position.x += speed;
    }


    @Override
    public void draw(Canvas canvas) {
        //canvas.drawBitmap(currentImage,null,playerColliderBox,new Paint(Color.RED));
        canvas.drawRect(playerColliderBox,new Paint(Color.RED));
        canvas.drawRect(playerColliderBox,new Paint(Color.BLUE));
    }

    @Override
    public void update() {
        if(position.x >Constants.SCREEN_WIDTH){
            position.x = 0;
        }
        if(position.x < 0){
            position.x = Constants.SCREEN_WIDTH;
        }

        if(isJumping){
            Log.d("JumppROGRESS", String.valueOf(jumpProgress));
            float second = miliseconds /1000;
            jumpProgress += speed + (9.81 * (second * second))/2;

            if(jumpProgress > jumpHeight){
                isJumping = false;
                miliseconds  = 0;
                jumpProgress = 0;
                return;
            }
            position.y -= speed + (9.81 * (second * second))/2;
            playerColliderBox.set(position.x - currentImage.getWidth()/2 - 60, position.y - currentImage.getHeight()/2, position.x + currentImage.getWidth()/2 - 60, position.y + currentImage.getHeight()/2);
        }
        else{
            float second = miliseconds /1000;
            position.y += speed + (9.81 * (second * second))/2;
            playerColliderBox.set(position.x - currentImage.getWidth()/2 - 60, position.y - currentImage.getHeight()/2, position.x + currentImage.getWidth()/2 - 60, position.y + currentImage.getHeight()/2);
        }
    }


    public boolean checkIfDead(){
        return Constants.SCREEN_HEIGHT  + currentImage.getHeight() < position.y;
    }

    public boolean isDead(){
        return dead;
    }

    public Rect getPlayerColliderBox() {
        return playerColliderBox;
    }

    public void setJumping(boolean jumping){
        this.isJumping = jumping;
    }

    public boolean isJumping(){
        return isJumping;
    }

    public int getJumpHeight(){
        return this.position.y;
    }
}
