package com.example.doodlejump.Objects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceView;

import com.example.doodlejump.R;
import com.example.doodlejump.View.DoodleGameView;

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
    private Point position = new Point(); //Center of image
    private Rect playerColliderBox = new Rect();
    private float miliseconds = 0;
    private int speed = 35;
    int jumpHeight = 800;
    int lastJump = 0;
    boolean isJumping = false;
    boolean dead = false;

    public Player(Point position){

        this.position = position;

        //Init resources
        this.playerImgLeft  = BitmapFactory.decodeResource(DoodleGameView.getAppResources(),R.drawable.left_doodle);
        this.playerImgRight = BitmapFactory.decodeResource(DoodleGameView.getAppResources(),R.drawable.right_doodle);
        this.playerImgTop   = BitmapFactory.decodeResource(DoodleGameView.getAppResources(),R.drawable.up_doodle);
        this.playerImgNoose = BitmapFactory.decodeResource(DoodleGameView.getAppResources(),R.drawable.nose_doodle);
        this.currentImage   = playerImgLeft;

        //TIMER
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                miliseconds += 1;
            }
        }, 0, 1);
    }

    public boolean collisionPlayerJumpRow(JumpRow jumpRow){
        if(!isJumping){
            if(playerColliderBox.intersect(jumpRow.position)){
                lastJump = jumpRow.position.top;
                isJumping = true;
                miliseconds  = 0;
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentImage,null,playerColliderBox,null);
    }

    @Override
    public void update() {
        if(isJumping){
            if(lastJump - jumpHeight >= playerColliderBox.bottom){
                isJumping = false;
                miliseconds  = 0;
            }
            float second = miliseconds /1000;
            position.y -= speed + (9.81 * (second * second))/2;
            playerColliderBox.set(position.x - currentImage.getWidth()/2, position.y - currentImage.getHeight()/2, position.x + currentImage.getWidth()/2, position.y + currentImage.getHeight()/2);
        }
        else{
            float second = miliseconds /1000;
            position.y += speed + (9.81 * (second * second))/2;
            playerColliderBox.set(position.x - currentImage.getWidth()/2, position.y - currentImage.getHeight()/2, position.x + currentImage.getWidth()/2, position.y + currentImage.getHeight()/2);
        }
        dead = checkIfDead();
    }

    public void moveLeft(){
        this.currentImage = playerImgLeft;
        this.position.x -= 20;
    }

    public void moveRight(){
        this.currentImage = playerImgRight;
        this.position.x += 20;
    }

    public boolean checkIfDead(){
        if(DoodleGameView.getScreenHeight() - currentImage.getHeight() > position.y){
            return true;
        }
        return false;
    }

    public boolean isDead(){
        return dead;
    }
}
