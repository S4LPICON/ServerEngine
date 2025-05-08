package com.zofrenic.serverengine.camera.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.zofrenic.serverengine.camera.controller.CameraHandler;

public class CameraCommands implements CommandExecutor {

    private final CameraHandler cameraHandler;

    public CameraCommands(CameraHandler cameraHandler) {
        this.cameraHandler = cameraHandler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("§eUso: /serverengine camera <start|stop> <jugador>");
            return true;
        }

        String subcommand = args[0].toLowerCase();
        String action = args[1].toLowerCase();

        if (!subcommand.equals("camera")) {
            sender.sendMessage("§cComando no reconocido.");
            return true;
        }

        if (args.length < 3) {
            sender.sendMessage("§eDebes especificar un jugador.");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[2]);
        if (target == null) {
            sender.sendMessage("§cJugador no encontrado.");
            return true;
        }

        switch (action) {
            case "start":
                cameraHandler.enableCamera("test", target);
                sender.sendMessage("§aCámara iniciada para " + target.getName() + ".");
                return true;

            case "stop":
                cameraHandler.disableCamera(target);
                sender.sendMessage("§cCámara detenida para " + target.getName() + ".");
                return true;

            default:
                sender.sendMessage("§cSubcomando desconocido. Usa start o stop.");
                return true;
        }
    }
}
