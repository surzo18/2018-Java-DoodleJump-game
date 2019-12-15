package com.example.doodlejump.Managers;

import android.graphics.Rect;

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
}
