package com.zofrenic.serverengine.templates.exampleplayablecharacter;

import org.bukkit.entity.Player;

//import com.zofrenic.serverengine.camera.controller.CameraHandler;
import com.zofrenic.serverengine.camera.model.impl.ArmorStandCamera;

public class MyPlayerExample {

    static ArmorStandCamera mycamera;
    static MyPlayer myplayer;

    public MyPlayerExample() {

    }

    public static void enabledMyCamera(Player player) {
        // myplayer = new MyPlayer(player);
        // CameraHandler cameraHandler = new CameraHandler();

        // cameraHandler.enableCamera("MyCamera", player.getPlayer(), myplayer,
        // mycamera);
    }

}
