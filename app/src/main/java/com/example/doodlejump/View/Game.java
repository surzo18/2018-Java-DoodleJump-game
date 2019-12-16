package com.example.doodlejump.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.doodlejump.Activity.MenuActivity;
import com.example.doodlejump.GameComponents.Constants;
import com.example.doodlejump.GameComponents.MainThread;
import com.example.doodlejump.Managers.CollisionManager;
import com.example.doodlejump.Managers.JumperManager;
import com.example.doodlejump.Objects.Jumper;
import com.example.doodlejump.Objects.Player;
import com.example.doodlejump.R;



public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Bitmap background;

    //Points
    private Point middleOfScreen;

    //GameObjects
    private Player player;
    private JumperManager jumperManager;
    private CollisionManager collisionManager;

    private int score;

    public Game(Context context) {
        super(context);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(),this);
        background = BitmapFactory.decodeResource(getResources(),R.drawable.game_background);


        setFocusable(true);
        initGame();
    }

    //InitGame, create players, rows
    private void initGame(){
        this.middleOfScreen = new Point(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2);
        this.score = 0;

        this.player = new Player(middleOfScreen);
        this.jumperManager = new JumperManager();
        this.collisionManager = new CollisionManager();

        //INIT START BOTTOM PLATFORM
        int jumperInitNumber = Constants.SCREEN_WIDTH / 160;
        int leftSpace        = Constants.SCREEN_WIDTH % 160;
        for(int i = 0; i < jumperInitNumber; i++){
            this.jumperManager.addJumper( new Jumper(new Point(i*160 + leftSpace, Constants.SCREEN_HEIGHT - 300)));
        }

        this.jumperManager.addJumper( new Jumper(new Point(middleOfScreen.x - 400, Constants.SCREEN_HEIGHT - 700)));
        this.jumperManager.addJumper( new Jumper(new Point(middleOfScreen.x, Constants.SCREEN_HEIGHT - 1000)));
        this.jumperManager.addJumper( new Jumper(new Point(middleOfScreen.x - 400, Constants.SCREEN_HEIGHT - 1300)));
        this.jumperManager.addJumper( new Jumper(new Point(middleOfScreen.x + 300, Constants.SCREEN_HEIGHT - 1700)));

        //Okrajove Jumpery na ukazku
        //this.jumperManager.addJumper( new Jumper(new Point(80, 20)));
        //this.jumperManager.addJumper( new Jumper(new Point(Constants.SCREEN_WIDTH - 80, 20)));

    }

    private void restartGame(){
        this.initGame();
        //this.thread.setRunning(false);
        //Intent menu = new Intent(Constants.context, MenuActivity.class);
        //((Activity) getContext()).startActivity(menu);
    }

    //WhenDidAction
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if (Constants.SCREEN_WIDTH/2 >= x)
                { player.moveLeft(); }
                else
                { player.moveRight(); }
                break;
        }
        return true;
    }

    public void update(){
        //UpdatePlayer

        player.update();

        if(player.getY() < Constants.SCREEN_HEIGHT/2){
            int diff =  Constants.SCREEN_HEIGHT/2 - player.getY();
               jumperManager.updatePlatformsY(diff);
               player.minusY(diff);
               score += diff;
        }

        jumperManager.update();

        //CheckCollision
        if(!player.isPlayerJumping()){
            for(int i = 0;i < jumperManager.getSize();i++){
                if(collisionManager.isCollidePlayerJumer(player,jumperManager.getJumper(i))){
                    player.setJumping(true);
                }
            }
        }

        if(player.checkIfIsDead()){
            this.restartGame();
        }
    }

    public void draw(Canvas canvas){
        super.draw(canvas);

        this.drawBackground(canvas);

        //Draw Center pool
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawRect(new Rect(0,Constants.SCREEN_HEIGHT/2, Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT/2 + 2),paint);

        jumperManager.draw(canvas);
        player.draw(canvas);

        paint.setTextSize(64);
        canvas.drawText("Score: " + String.valueOf(score), 0, 60, paint);

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(),this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //NOTHING
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(true ){
            try{
                thread.setRunning(false);
                thread.join();
            } catch (Exception e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    private void drawBackground(Canvas canvas) {
        canvas.drawBitmap(background, null,new Rect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT),null);
    }

    public void motionLeft(){
        this.player.moveLeft();
    }

    public void motionRight(){
        this.player.moveRight();
    }
}
