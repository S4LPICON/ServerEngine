package com.zofrenic.serverengine.camera.controller;

import java.util.HashMap;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import com.zofrenic.serverengine.camera.model.Camera;
import com.zofrenic.serverengine.camera.model.impl.ArmorStandCamera;
import com.zofrenic.serverengine.entities.player.SPlayer;

public class CameraHandler {

    HashMap<Player, ArmorStandCamera> cameras = new HashMap<>();

    public void enableCamera(String name, Player player, SPlayer playable, Camera camera1) {
        player.setGameMode(org.bukkit.GameMode.SPECTATOR);
        player.sendMessage("§aCámara iniciada: " + name);
        // entidad controlable
        ArmorStand target = player.getWorld().spawn(player.getLocation(), ArmorStand.class);
        // ---

        ArmorStand armorStand = player.getWorld().spawn(player.getLocation(), ArmorStand.class);
        armorStand.setGravity(false);
        ArmorStandCamera camera = new ArmorStandCamera(name, armorStand, 75, 0.1f, 500.0f);
        // ----
        camera.update(playable);

        camera.activate(player);

        cameras.put(player, camera);
    }

    public void disableCamera(Player player) {
        if (cameras.containsKey(player)) {
            ArmorStandCamera camera = cameras.get(player);
            camera.desactivate(player);
            cameras.remove(player);
            player.sendMessage("§cCámara detenida.");
        } else {
            player.sendMessage("§cNo tienes una cámara activa.");
        }
    }

}
