package com.example.doodlejump.Managers;

import android.graphics.Rect;
import android.util.Log;

import com.example.doodlejump.Objects.Jumper;
import com.example.doodlejump.Objects.Player;

public class CollisionManager {

    public boolean isCollidePlayerJumer(Player player, Jumper jumper){
        Rect playerColliderBox = player.getPlayerColliderBox();
        Rect jumperCollderBox  = jumper.getJumperColliderBox();

        if(
            playerColliderBox.bottom <= jumperCollderBox.bottom &&
            playerColliderBox.bottom >= jumperCollderBox.top    &&
            (
                (
                    playerColliderBox.left  <= jumperCollderBox.right &&
                    playerColliderBox.left  >= jumperCollderBox.left
                ) ||
                (
                    playerColliderBox.right  <= jumperCollderBox.right &&
                    playerColliderBox.right  >= jumperCollderBox.left
                )
            )
        ){
            return true;
        }

        return false;
    }

    public boolean isCollideJumperJumperOnX(Jumper jumper1, Jumper jumper2){
        Rect jumperBox1 =  jumper1.getJumperColliderBox();
        Rect jumperBox2 =  jumper2.getJumperColliderBox();
        if(
                (
                jumperBox1.left >= jumperBox2.left &&
                jumperBox1.left <= jumperBox2.right
                )
                ||
                (
                jumperBox1.right >= jumperBox2.left &&
                jumperBox1.right <= jumperBox2.right
                )
        ){
            return true;
        }
        return false;
    };
}
