package com.example.doodlejump.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.doodlejump.MainThread;
import com.example.doodlejump.Objects.JumpRow;
import com.example.doodlejump.Objects.RectPlayer;
import com.example.doodlejump.R;

import java.util.ArrayList;
import java.util.List;


public class DoodleGameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    Bitmap[] bmp;
    private RectPlayer player;
    private List<JumpRow> jumpRows = new ArrayList<>();


    public DoodleGameView(Context context) {
        super(context);
        //setBackgroundResource(R.drawable.gameBackground);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(),this);


        player = new RectPlayer(BitmapFactory.decodeResource(getResources(),R.drawable.right_doodle),getScreenWidth()/2,getScreenHeight()/2);
        jumpRows.add(new JumpRow(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gametiles), 0, 0, 160, 50, null, true),230,getScreenHeight() - 60));
        jumpRows.add(new JumpRow(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gametiles), 0, 0, 160, 50, null, true),390,getScreenHeight() - 60));
        jumpRows.add(new JumpRow(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gametiles), 0, 0, 160, 50, null, true),550,getScreenHeight() - 60));
        jumpRows.add(new JumpRow(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gametiles), 0, 0, 160, 50, null, true),710,getScreenHeight() - 60));
        jumpRows.add(new JumpRow(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gametiles), 0, 0, 160, 50, null, true),870,getScreenHeight() - 60));
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

    /*
    @Override
    protected void onDraw(Canvas canvas){

        for(int i = 0; i< jumps.length; i++){
            //canvas.drawBitmap(bmp[0], null, new Rect(jumps[i].getPosX(),jumps[0].getPosY(),200,50) , null);
        }
        //canvas.drawBitmap(bmp[0],null, new Rect(0,0,10,10),null);
    }
    */

    public boolean onTouchEvent(MotionEvent event){

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            break;
        }

        return true;
    }

    public void update(){
        player.update();
        for(int i = 0; i < jumpRows.size(); i++){
            player.collisionPlayerJumpRow(jumpRows.get(i));
            jumpRows.get(i).update();
        }
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        player.draw(canvas);
        for(int i = 0; i < jumpRows.size(); i++){
            jumpRows.get(i).draw(canvas);
        }

    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
