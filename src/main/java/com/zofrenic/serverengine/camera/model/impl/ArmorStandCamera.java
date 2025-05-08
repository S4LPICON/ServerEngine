package com.zofrenic.serverengine.camera.model.impl;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.zofrenic.serverengine.camera.model.Camera;
import com.zofrenic.serverengine.entities.player.SPlayer;

public class ArmorStandCamera extends Camera {

    @SuppressWarnings("unused")
    private SPlayer playable;
    private Entity armorStandPlayer;
    private Entity cameraStand;
    private BukkitRunnable trackingTask;

    public ArmorStandCamera(String name, Entity armorStandPlayer, Entity cameraStand, SPlayer playable, float fov,
            float near, float far) {
        super(name, armorStandPlayer.getLocation().toVector());

        if (playable == null || armorStandPlayer == null || cameraStand == null) {
            throw new IllegalArgumentException("Ningún parámetro puede ser null");
        }

        this.playable = playable;
        this.armorStandPlayer = armorStandPlayer;
        this.cameraStand = cameraStand;
        this.active = true;
    }

    public SPlayer getPlayable() {
        return this.playable;
    }

    @Override
    public void activate(Player player) {
        if (!cameraStand.isValid() || !armorStandPlayer.isValid()) {
            player.sendMessage("§cLa cámara no es válida.");
            return;
        }

        // Posiciona la cámara encima del armorStandPlayer
        Location base = armorStandPlayer.getLocation();
        Location camLocation = base.clone().add(0, 3.5, -2.5);
        camLocation.setYaw((float) rotation.getY());
        camLocation.setPitch((float) rotation.getX());

        cameraStand.teleport(camLocation);
        player.setSpectatorTarget(cameraStand);
        active = true;
    }

    @Override
    public void update(Entity ignored) {
        if (trackingTask != null) {
            trackingTask.cancel();
        }

        if (!cameraStand.isValid() || !armorStandPlayer.isValid()) {
            return;
        }

        trackingTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (!active || !cameraStand.isValid() || !armorStandPlayer.isValid()) {
                    cancel();
                    return;
                }

                // Mantén la cámara encima del armorStandPlayer
                Location base = armorStandPlayer.getLocation();
                Location camLocation = base.clone().add(0, 3.5, 2.5);
                camLocation.setYaw((float) rotation.getY());
                camLocation.setPitch((float) rotation.getX());

                cameraStand.teleport(camLocation);
            }
        };
        trackingTask.runTaskTimer(plugin, 0L, 1L);
    }

    @Override
    public void desactivate(Player player) {
        active = false;

        if (trackingTask != null) {
            trackingTask.cancel();
            trackingTask = null;
        }

        if (player != null && armorStandPlayer.isValid()) {
            player.setGameMode(org.bukkit.GameMode.SURVIVAL);
            player.teleport(armorStandPlayer.getLocation());
        }

        if (cameraStand != null && cameraStand.isValid()) {
            cameraStand.remove();
        }

        if (armorStandPlayer != null && armorStandPlayer.isValid()) {
            armorStandPlayer.remove();
        }
    }
}
