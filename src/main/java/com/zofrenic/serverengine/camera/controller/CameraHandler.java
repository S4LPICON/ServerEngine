package com.zofrenic.serverengine.camera.controller;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.zofrenic.serverengine.ServerEngine;
import com.zofrenic.serverengine.camera.model.Camera;
import com.zofrenic.serverengine.camera.model.impl.ArmorStandCamera;
import com.zofrenic.serverengine.camera.model.impl.ArmorStandPlayer;
//import com.zofrenic.serverengine.entities.player.SPlayer;

public class CameraHandler {

    private final Map<Player, Camera> activeCameras = new HashMap<>();
    ServerEngine plugin;

    public CameraHandler() {
        // Constructor vacío
        plugin = ServerEngine.getInstance();
    }

    /**
     * Activa una cámara personalizada para un jugador.
     *
     * @param name     nombre de la cámara
     * @param player   el jugador real (Bukkit)
     * @param playable entidad jugable personalizada (SPlayer)
     * @param camera   la instancia de cámara (debe tener el ArmorStand ya listo)
     */

    public void enableCamera(String name, Player player) {// , SPlayer playable, Camera camera) {
        ArmorStand entidadJugador = player.getWorld().spawn(player.getLocation(), ArmorStand.class);
        ArmorStand entidadCamara = player.getWorld().spawn(player.getLocation(), ArmorStand.class);
        ArmorStandPlayer jugador = new ArmorStandPlayer(player, entidadJugador); // Instancia de ArmorStandPlayer
        plugin.getInputListener().registerHandler(player.getUniqueId(), jugador);
        ArmorStandCamera camera = new ArmorStandCamera("MyCamera", entidadJugador, entidadCamara, jugador, 70, 0.1f,
                1000f);

        if (activeCameras.containsKey(player)) {
            disableCamera(player); // Desactiva cualquier cámara anterior
        }

        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage("§aCámara iniciada: " + name);

        Entity target = jugador.getEntity(); // entidad jugable (por ejemplo, ArmorStand)

        camera.activate(player);
        camera.update(target);
        activeCameras.put(player, camera);
    }

    /**
     * Detiene y elimina la cámara activa del jugador.
     *
     * @param player el jugador
     */
    public void disableCamera(Player player) {
        Camera camera = activeCameras.remove(player);
        if (camera != null) {
            camera.desactivate(player);
            plugin.getInputListener().unregisterHandler(player.getUniqueId());
            player.sendMessage("§cCámara detenida.");

        } else {
            player.sendMessage("§cNo tienes una cámara activa.");
        }
    }

    public boolean hasActiveCamera(Player player) {
        return activeCameras.containsKey(player);
    }
}
