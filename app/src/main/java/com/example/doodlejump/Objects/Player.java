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
    private int playerSpeed = 25;

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

    public void moveLeft(int speed){
        this.currentImg = doodleLeft;
    }

    public void moveRight(int speed){
        this.currentImg = doodleRight;
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

        if(this.isJumping){
            this.playerPosition.y -= playerSpeed;
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

    public boolean checkIfIsDead(){
        int maxDeadPosition =  Constants.SCREEN_HEIGHT + doodleImgHeight;
        if(this.playerPosition.y > maxDeadPosition ){
            return true;
        }
        return false;
    }
}
