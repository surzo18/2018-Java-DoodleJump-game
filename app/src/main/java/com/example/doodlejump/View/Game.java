package com.example.doodlejump.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.doodlejump.GameComponents.Constants;
import com.example.doodlejump.GameComponents.Controler;
import com.example.doodlejump.GameComponents.MainThread;
import com.example.doodlejump.Managers.CollisionManager;
import com.example.doodlejump.Managers.EnemiesManager;
import com.example.doodlejump.Managers.JumpRowsManager;
import com.example.doodlejump.Objects.Player;
import com.example.doodlejump.R;



public class Game extends SurfaceView implements SurfaceHolder.Callback {
    static Resources appResources;

    private MediaPlayer mediaPlayer;
    private MainThread thread;
    private Bitmap background;

    private Player player;
    private EnemiesManager enemies;
    private JumpRowsManager jumpRows;
    private CollisionManager collisionManager;
    private Controler controler;

    private boolean endGame = false;
    private int lastUpdatePlatformY;

    public Game(Context context) {
        super(context);
        getHolder().addCallback(this);

        appResources = getResources();

        mediaPlayer = MediaPlayer.create(getContext(), R.raw.jump);
        thread = new MainThread(getHolder(),this);
        background = BitmapFactory.decodeResource(getResources(),R.drawable.game_background);

        setFocusable(true);
        initGame();
    }

    //InitGame, create players, rows
    private void initGame(){
        //Init Player
        player = new Player(new Point(Constants.SCREEN_WIDTH/2,Constants.SCREEN_HEIGHT/2));
        collisionManager = new CollisionManager();
        controler = new Controler(player);
        jumpRows = new JumpRowsManager();
        jumpRows.createObstacle(230,Constants.SCREEN_HEIGHT - 60);
        jumpRows.createObstacle(390,Constants.SCREEN_HEIGHT - 60);
        jumpRows.createObstacle(550,Constants.SCREEN_HEIGHT - 60);
        jumpRows.createObstacle(710,Constants.SCREEN_HEIGHT - 60);
        jumpRows.createObstacle(870,Constants.SCREEN_HEIGHT - 60);
        jumpRows.createObstacle(870,Constants.SCREEN_HEIGHT - 400);
        jumpRows.createObstacle(770,Constants.SCREEN_HEIGHT - 700);
        jumpRows.createObstacle(470,Constants.SCREEN_HEIGHT - 900);
        jumpRows.createObstacle(470,Constants.SCREEN_HEIGHT - 1400);
        jumpRows.createObstacle(470,Constants.SCREEN_HEIGHT - 1500);
    }

    private void restartGame(){
        this.initGame();
    }

    //WhenDidAction
    public boolean onTouchEvent(MotionEvent event){

        controler.makeMove(event);

        return true;
    }

    public void update(){
        jumpRows.generate();

        jumpRows.update();
        player.update();

        //CheckCollisions
        for(int i =0; i < jumpRows.getSize();i++){
            if( collisionManager.collisionPlayerJumpRow(player,jumpRows.getJumpRow(i))) {
                player.setJumping(true);
            }
        }

        if(player.getJumpHeight() < Constants.SCREEN_HEIGHT / 2 && player.isJumping()){

            jumpRows.movePlatform(Constants.SCREEN_HEIGHT / 2 - player.getJumpHeight() - this.lastUpdatePlatformY );
            this.lastUpdatePlatformY = Constants.SCREEN_HEIGHT / 2 - player.getJumpHeight();
        }
        else{
            this.lastUpdatePlatformY = 0;
        }

        endGame = player.checkIfDead();

        if(endGame){
           this.restartGame();
           endGame = false;
        }
    }

    public void draw(Canvas canvas){
        super.draw(canvas);

        //Background reset
        canvas.drawBitmap(background,null, new Rect(0,0, Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT),null);

        jumpRows.draw(canvas);
        player.draw(canvas);

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



}
