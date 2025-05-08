package com.zofrenic.serverengine;

import org.bukkit.plugin.java.JavaPlugin;
//import com.comphenix.protocol.ProtocolLibrary;
import com.zofrenic.serverengine.camera.command.CameraCommands;
import com.zofrenic.serverengine.camera.controller.CameraHandler;
//import com.zofrenic.serverengine.inputs.model.Input;
import com.zofrenic.serverengine.inputs.model.InputListener;

public final class ServerEngine extends JavaPlugin {

    private static ServerEngine instance;
    private final InputListener inputListener = new InputListener();

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic

        // Registrar el listener de eventos
        getServer().getPluginManager().registerEvents(inputListener, this);

        // Inicializar el controlador de cámara
        CameraHandler cameraHandler = new CameraHandler();
        getCommand("serverengine").setExecutor(new CameraCommands(cameraHandler));

        // Registrar el listener de paquetes para capturar los paquetes de movimiento
        // ProtocolLibrary.getProtocolManager().addPacketListener(new Input(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ServerEngine getInstance() {
        return instance;
    }

    public InputListener getInputListener() {
        return inputListener;
    }
}
