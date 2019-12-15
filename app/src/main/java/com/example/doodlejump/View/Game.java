package com.example.doodlejump.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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

        this.player = new Player(middleOfScreen);
        this.jumperManager = new JumperManager();
        this.collisionManager = new CollisionManager();

        //INIT START BOTTOM PLATFORM
        int jumperInitNumber = Constants.SCREEN_WIDTH / 160;
        int leftSpace        = Constants.SCREEN_WIDTH % 160;
        for(int i = 0; i < jumperInitNumber; i++){
            this.jumperManager.addJumper( new Jumper(new Point(i*160 + leftSpace, Constants.SCREEN_HEIGHT - 300)));
        }
    }

    private void restartGame(){
        this.initGame();
    }

    //WhenDidAction
    public boolean onTouchEvent(MotionEvent event){
        return true;
    }

    public void update(){
        //UpdatePlayer

        player.update();

        //todo: update all platforms HERE ADRIAN!

        //CheckCollision
        if(!player.isPlayerJumping()){
            Log.d("TSize:", String.valueOf( jumperManager.getSize()));
            for(int i = 0;i < jumperManager.getSize();i++){
                Log.d("Tu som", String.valueOf(i));
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

        player.draw(canvas);

        jumperManager.draw(canvas);

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

}
