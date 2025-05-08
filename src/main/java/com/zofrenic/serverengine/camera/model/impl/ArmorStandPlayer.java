package com.zofrenic.serverengine.camera.model.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.zofrenic.serverengine.entities.player.SPlayer;

public class ArmorStandPlayer extends SPlayer {

    private boolean movingForward = false;
    private boolean movingBackward = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    private BukkitRunnable movementTask;

    private static final double SPEED = 0.21585;
    private static final double JUMP_HEIGHT = 0.52; // Altura del salto

    public ArmorStandPlayer(Player player, Entity entity) {
        super(player, entity);
    }

    @Override
    public void moveForward() {
        if (entity instanceof LivingEntity living) {
            Vector direction = entity.getLocation().getDirection();
            direction.setY(0); // Eliminar componente vertical
            living.setVelocity(direction.normalize().multiply(SPEED));
            super.player.sendMessage("⬆");
        }
    }

    @Override
    public void moveBackward() {
        if (entity instanceof LivingEntity living) {
            Vector direction = entity.getLocation().getDirection();
            direction.setY(0);
            living.setVelocity(direction.normalize().multiply(-SPEED));
            super.player.sendMessage("⬇");
        }
    }

    @Override
    public void moveLeft() {
        if (entity instanceof LivingEntity living) {
            Vector direction = entity.getLocation().getDirection();
            direction.setY(0);
            Vector left = new Vector(direction.getZ(), 0, -direction.getX()).normalize();
            living.setVelocity(left.multiply(SPEED));
            super.player.sendMessage("⬅");
        }
    }

    @Override
    public void moveRight() {
        if (entity instanceof LivingEntity living) {
            Vector direction = entity.getLocation().getDirection();
            direction.setY(0);
            Vector right = new Vector(-direction.getZ(), 0, direction.getX()).normalize();
            living.setVelocity(right.multiply(SPEED));
            super.player.sendMessage("➡");
        }
    }

    @Override
    public void jump() {
        if (entity instanceof LivingEntity living) {
            Vector velocity = living.getVelocity();
            velocity.setY(JUMP_HEIGHT); // Salto típico de Minecraft
            living.setVelocity(velocity);
            super.player.sendMessage("⤴");
        }
    }

    @Override
    public void sneak() {
        if (entity instanceof ArmorStand armorStand) {
            armorStand.setSmall(true);
        }
        super.player.sendMessage("⤵");
    }

    @Override
    public void sprint() {
        super.player.sendMessage("🏃");
    }

    @Override
    public void setMovingForward(boolean moving) {
        this.movingForward = moving;
        updateMovement();
    }

    @Override
    public void setMovingBackward(boolean moving) {
        this.movingBackward = moving;
        updateMovement();
    }

    @Override
    public void setMovingLeft(boolean moving) {
        this.movingLeft = moving;
        updateMovement();
    }

    @Override
    public void setMovingRight(boolean moving) {
        this.movingRight = moving;
        updateMovement();
    }

    private void updateMovement() {
        if (movementTask != null) {
            movementTask.cancel();
        }

        if (movingForward || movingBackward || movingLeft || movingRight) {
            movementTask = new BukkitRunnable() {
                @Override
                public void run() {
                    if (!(entity instanceof LivingEntity living))
                        return;

                    if (movingForward) {
                        moveForward();
                    } else if (movingBackward) {
                        moveBackward();
                    } else if (movingLeft) {
                        moveLeft();
                    } else if (movingRight) {
                        moveRight();
                    } else {
                        living.setVelocity(new Vector(0, 0, 0));
                    }
                }
            };
            movementTask.runTaskTimer(Bukkit.getPluginManager().getPlugin("ServerEngine"), 0L, 1); // Cada tick
        }
    }
}
