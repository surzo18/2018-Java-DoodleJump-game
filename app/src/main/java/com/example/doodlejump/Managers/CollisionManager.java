package com.example.doodlejump.Managers;

import android.graphics.Rect;
import android.util.Log;

import com.example.doodlejump.Objects.JumpRow;
import com.example.doodlejump.Objects.Player;

public class CollisionManager {
    public CollisionManager(){

    }

    public boolean collisionPlayerJumpRow(Player player, JumpRow jumpRow){
        Rect playerBox = player.getPlayerColliderBox();
        Rect jumpRowBox = jumpRow.getColiderBox();

        if(
                (playerBox.bottom >= jumpRowBox.top && playerBox.bottom <= jumpRowBox.bottom) &&
                (
                        (playerBox.left <= jumpRowBox.right && playerBox.left >= jumpRowBox.left) ||
                       (playerBox.right <= jumpRowBox.right && playerBox.right >= jumpRowBox.left)
                ) &&
                player.isJumping() == false ){
            return true;
        }
        return false;
    }


}
