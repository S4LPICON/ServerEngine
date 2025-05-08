package com.zofrenic.serverengine.camera.model;

import org.bukkit.util.Vector;

import com.zofrenic.serverengine.ServerEngine;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public abstract class Camera {

    protected String name;
    protected Vector position;
    protected Vector rotation; // pitch, yaw, roll (X, Y, Z)
    protected float fov;
    protected float nearDistance;
    protected float farDistance;
    protected boolean active;
    protected static ServerEngine plugin;

    public Camera(String name, Vector position) {
        plugin = ServerEngine.getInstance();
        this.name = name;
        this.position = position;
        this.rotation = new Vector(45, 180, 0); // Default rotation
        this.fov = 75.0f;
        this.nearDistance = 0.1f;
        this.farDistance = 50.0f;
        this.active = false;
    }

    public abstract void activate(Player player);

    public abstract void desactivate(Player player);

    public abstract void update(Entity target);

    // Getters y setters

    public Vector getPosition() {
        return position;
    }

    public Vector getRotation() {
        return rotation;
    }

    public float getFov() {
        return fov;
    }

    public boolean isActive() {
        return active;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setRotation(Vector rotation) {
        this.rotation = rotation;
    }

    public void setFov(float fov) {
        this.fov = fov;
    }
}
