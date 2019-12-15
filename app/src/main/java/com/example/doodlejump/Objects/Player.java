package com.example.doodlejump.Objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.example.doodlejump.GameComponents.Constants;
import com.example.doodlejump.R;

public class Player implements GameObject {

    //Images
    private final Bitmap doodleLeft     = BitmapFactory.decodeResource(
            Constants.context.getResources(),R.drawable.left_doodle
    );
    private final Bitmap doodleRight    = BitmapFactory.decodeResource(
            Constants.context.getResources(),R.drawable.right_doodle
    );
    private final Bitmap doodleUp       = BitmapFactory.decodeResource(
            Constants.context.getResources(),R.drawable.up_doodle
    );
    private final Bitmap doodleNose     = BitmapFactory.decodeResource(
            Constants.context.getResources(),R.drawable.nose_doodle
    );
    private Bitmap currentImg;

    //ColiderBox
    private Rect playerColliderBox;
    private Point playerPosition;
    private int doodleImgWidth;
    private int doodleImgHeight;

    //Physic
    private int playerSpeed  = 25;
    private int jumpHeight   = playerSpeed* 20;
    private int jumpProgress = 0;

    //States
    private  boolean isJumping  = false;
    private  boolean isDead     = false;

    public Player(Point position){
        this.currentImg        = doodleLeft;
        this.doodleImgWidth    = currentImg.getWidth();
        this.doodleImgHeight   = currentImg.getHeight();

        this.playerPosition = position;
        this.setColliderBox(playerPosition);
    }

    public void moveLeft(){
        this.currentImg = doodleLeft;
        this.playerPosition.x -= playerSpeed;
    }

    public void moveRight(){
        this.currentImg = doodleRight;
        this.playerPosition.x += playerSpeed;
    }

    @Override
    public void draw(Canvas canvas) {
        //Paint myPaint = new Paint();
        //myPaint.setColor(Color.BLUE);
        //canvas.drawRect(playerColliderBox,myPaint);
        canvas.drawBitmap(currentImg,null,playerColliderBox,null);
    }

    @Override
    public void update() {
        //Check if player not reach maxPointOfJump
        if(this.jumpProgress >= jumpHeight){
            this.isJumping = false;
            this.jumpProgress = 0;
        }

        if(playerPosition.x <= 0){
            Log.d("mensie","0");
            playerPosition.x = Constants.SCREEN_WIDTH;
        }
        else if(playerPosition.x >= Constants.SCREEN_WIDTH){
            Log.d("vecsie","1");
            playerPosition.x = 0;
        }

        if(this.isJumping){
            this.playerPosition.y -= playerSpeed;
            jumpProgress += this.playerSpeed;
        }
        else{
            this.playerPosition.y += playerSpeed;
        }
        this.setColliderBox(playerPosition);
    }

    private void setColliderBox(Point position){
        this.playerColliderBox = new Rect(
                position.x - doodleImgWidth/2,
                position.y - doodleImgHeight/2,
                position.x + doodleImgWidth/2,
                position.y + doodleImgHeight/2
        );
    }

    public Rect getPlayerColliderBox(){
        return this.playerColliderBox;
    }

    public boolean checkIfIsDead(){
        int maxDeadPosition =  Constants.SCREEN_HEIGHT + doodleImgHeight;
        if(this.playerPosition.y > maxDeadPosition ){
            return true;
        }
        return false;
    }

    public boolean isPlayerJumping(){
        return this.isJumping;
    }

    public void setJumping(boolean jumping) {
        this.isJumping = jumping;
    }

    public int getY(){
        return  this.playerPosition.y;
    }

    public void minusY(int diff) {
        this.playerPosition.y += diff;
    }
}
