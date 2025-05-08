package com.zofrenic.serverengine.entities.player;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.zofrenic.serverengine.inputs.api.InputActionHandler;

public abstract class SPlayer implements InputActionHandler {

    protected final String name;
    protected final double maxHealth;
    protected Location location;

    protected Entity entity;
    protected Player player;

    protected double health;
    protected int level;
    protected int experience;
    protected int maxExperience;
    protected String teamName;

    @SuppressWarnings("deprecation")
    public SPlayer(Player player, Entity entity) {
        this.player = player;
        this.entity = entity;
        this.name = player.getName();
        this.location = player.getLocation();
        this.health = player.getHealth();
        this.maxHealth = player.getMaxHealth();
    }

    // Getters
    public String getName() {
        return name;
    }

    public Entity getEntity() {
        return entity;
    }

    public Location getLocation() {
        return location;
    }

    public double getHealth() {
        return health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getMaxExperience() {
        return maxExperience;
    }

    public String getTeamName() {
        return teamName;
    }

    // Setters

    public void setHealth(double health) {
        this.health = health;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setMaxExperience(int maxExperience) {
        this.maxExperience = maxExperience;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // Métodos de InputActionHandler
    @Override
    public abstract void moveForward();

    @Override
    public abstract void moveBackward();

    @Override
    public abstract void moveLeft();

    @Override
    public abstract void moveRight();

    @Override
    public abstract void jump();

    @Override
    public abstract void sneak();

    @Override
    public abstract void sprint();
}
