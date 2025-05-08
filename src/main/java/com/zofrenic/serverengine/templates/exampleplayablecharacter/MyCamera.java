package com.zofrenic.serverengine.templates.exampleplayablecharacter;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.zofrenic.serverengine.camera.model.Camera;

public class MyCamera extends Camera {

    private Entity camera1;
    private Entity target;

    public MyCamera(String name, Entity armorStand, float fov, float near, float far) {
        super(name, armorStand.getLocation().toVector());
        this.camera1 = armorStand;
        this.active = true;
    }

    // Este método se llama cuando se activa la cámara
    // en este caso, se teletransporta al jugador a la ubicación del armorstand que
    // seria
    // la camara, y se genera otra entidad que seria nuestro playable character
    @Override
    public void activate(Player player) {
        // cambiar la pocision de la camara
        Location loc = camera1.getLocation();
        loc.setYaw((float) rotation.getY()); // yaw = rotación horizontal (Y)
        loc.setPitch((float) rotation.getX()); // pitch = rotación vertical (X)

        camera1.teleport(loc);

        // Set spectator target to this entity
        player.setSpectatorTarget(camera1);
        active = true;
    }

    // Actualiza la posición de la cámara cada tick en mi caso ya que se quiere
    // que la cámara siga al jugador, si no se quiere que la cámara siga al jugador
    // simplemente se puede dejar vacio el método update o no llamarlo

    @Override
    public void update(Entity target) {
        this.target = target;
        if (target == null || !target.isValid())
            return;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!active || target == null || !target.isValid() || !camera1.isValid()) {
                    this.cancel();
                    return;
                }

                // Obtienes la ubicación base
                Location base = target.getLocation();

                // Creas una nueva Location combinando
                Location targetLoc = new Location(
                        base.getWorld(),
                        base.getX(),
                        base.getY(),
                        base.getZ(),
                        (float) rotation.getY(), // yaw (rotación horizontal)
                        (float) rotation.getX() // pitch (rotación vertical)
                );

                targetLoc.add(0, 3.5, -2.5); // ajusta según tu preferencia

                camera1.teleport(targetLoc);
            }
        }.runTaskTimer(plugin, 0L, 1L); // ejecuta cada tick
    }

    // que cosas se deben hacer para desactivar la camara
    // en este caso, simplemente se elimina el armorstand y se teletransporta al
    // jugador a la ubicacion de el playable character
    @Override
    public void desactivate(Player player) {
        if (player == null || target == null || !target.isValid() || !camera1.isValid()) {
            return;
        }

        Location loc = target.getLocation().clone();
        player.setGameMode(org.bukkit.GameMode.SURVIVAL);
        player.teleport(loc);
        target.remove();
        camera1.remove();

    }

}
