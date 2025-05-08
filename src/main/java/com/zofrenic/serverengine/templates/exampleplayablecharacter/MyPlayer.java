package com.zofrenic.serverengine.templates.exampleplayablecharacter;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.zofrenic.serverengine.entities.player.SPlayer;

public class MyPlayer extends SPlayer {

    public MyPlayer(Player player, Entity entity) {
        super(player, entity); // Si tu SPlayer original no tiene el parámetro `Entity`, mantenlo así
        this.entity = player.getWorld().spawn(player.getLocation(), ArmorStand.class);

        // Opcionales: puedes inicializarlos en SPlayer si deseas
        this.setLevel(1);
        this.setExperience(0);
        this.setMaxExperience(100);
        this.setTeamName("Default Team");
    }

    public Entity getEntity() {
        return entity;
    }

    // Implementaciones vacías (a completar según la lógica del juego)

    @Override
    public void moveForward() {
        System.out.println(getName() + " se mueve hacia adelante");
    }

    @Override
    public void moveBackward() {
        System.out.println(getName() + " se mueve hacia atrás");
    }

    @Override
    public void moveLeft() {
        System.out.println(getName() + " se mueve a la izquierda");
    }

    @Override
    public void moveRight() {
        System.out.println(getName() + " se mueve a la derecha");
    }

    @Override
    public void jump() {
        System.out.println(getName() + " salta");
    }

    @Override
    public void sneak() {
        System.out.println(getName() + " se agacha");
    }

    @Override
    public void sprint() {
        System.out.println(getName() + " corre");
    }

    @Override
    public void setMovingForward(boolean moving) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMovingForward'");
    }

    @Override
    public void setMovingBackward(boolean moving) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMovingBackward'");
    }

    @Override
    public void setMovingLeft(boolean moving) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMovingLeft'");
    }

    @Override
    public void setMovingRight(boolean moving) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMovingRight'");
    }
}
