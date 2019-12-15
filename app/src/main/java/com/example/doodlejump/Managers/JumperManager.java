package com.example.doodlejump.Managers;

import android.graphics.Canvas;

import com.example.doodlejump.Objects.GameObject;
import com.example.doodlejump.Objects.Jumper;

import java.util.ArrayList;
import java.util.List;

public class JumperManager implements GameObject {

    private List<Jumper> jumpers;

    public JumperManager(){
        this.jumpers = new ArrayList<>();
    }

    @Override
    public void draw(Canvas canvas) {
        for(int i = 0; i < jumpers.size();i++){
            jumpers.get(i).draw(canvas);
        }
    }

    @Override
    public void update() {
        for(int i = 0; i < jumpers.size();i++){
            jumpers.get(i).update();
        }
    }

    public void addJumper(Jumper jumper){
        this.jumpers.add(jumper);
    }

    public Jumper getJumper(int i){
        return jumpers.get(i);
    }

    public int getSize(){
        return this.jumpers.size();
    }
}
