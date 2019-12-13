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

import com.example.doodlejump.MainThread;
import com.example.doodlejump.Objects.JumpRow;
import com.example.doodlejump.Objects.Player;
import com.example.doodlejump.R;

import java.util.ArrayList;
import java.util.List;


public class DoodleGameView extends SurfaceView implements SurfaceHolder.Callback {
    static Resources appResources;


    private MediaPlayer mediaPlayer;
    private MainThread thread;
    Bitmap[] bmp;
    private Player player;
    private List<JumpRow> jumpRows = new ArrayList<>();

    private Bitmap background;
    public DoodleGameView(Context context) {
        super(context);
        appResources = getResources();

        //ssetBackgroundResource(R.drawable.game_background);
        getHolder().addCallback(this);
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.jump);

        thread = new MainThread(getHolder(),this);

        background = BitmapFactory.decodeResource(getResources(),R.drawable.game_background);
        player = new Player(new Point(getScreenWidth()/2,getScreenHeight()/2));

        jumpRows.add(new JumpRow(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gametiles), 0, 0, 160, 50, null, true),230,getScreenHeight() - 60));
        jumpRows.add(new JumpRow(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gametiles), 0, 0, 160, 50, null, true),390,getScreenHeight() - 60));
        jumpRows.add(new JumpRow(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gametiles), 0, 0, 160, 50, null, true),550,getScreenHeight() - 60));
        jumpRows.add(new JumpRow(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gametiles), 0, 0, 160, 50, null, true),710,getScreenHeight() - 60));
        jumpRows.add(new JumpRow(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gametiles), 0, 0, 160, 50, null, true),870,getScreenHeight() - 60));

        jumpRows.add(new JumpRow(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gametiles), 0, 0, 160, 50, null, true),550,getScreenHeight() - 1000));
        setFocusable(true);

       // init(context);
    }

    private void init(Context context){
        bmp = new Bitmap[1];
        bmp[1] =  Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.gametiles), 0, 0, 200, 50, null, true);
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

    public boolean onTouchEvent(MotionEvent event){

        float xClicked = event.getX();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if(getScreenWidth()/2 >= xClicked){
                    player.moveLeft();
                }
                else{
                    player.moveRight();
                 }
        }

        return true;
    }

    public void update(){
        player.update();
        for(int i = 0; i < jumpRows.size(); i++){
            if(player.collisionPlayerJumpRow(jumpRows.get(i))){
                mediaPlayer.start();
                return;
            };
            jumpRows.get(i).update();
        }

        if(player.isDead()){
            Log.d("Ahoj","Skoncil si");
        }
    }

    public void draw(Canvas canvas){
        super.draw(canvas);

        canvas.drawBitmap(background,null, new Rect(0,0, getScreenWidth(),getScreenHeight()),null);
        player.draw(canvas);
        for(int i = 0; i < jumpRows.size(); i++){
            jumpRows.get(i).draw(canvas);
        }

    }

    //GET SCREEN WIDTH
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    //GET SCREEN HEIGHT
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    //GET RESOURCES
    public static Resources getAppResources() {
        return appResources;
    }

}
