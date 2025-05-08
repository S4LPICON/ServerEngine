package com.zofrenic.serverengine.teams.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeamCommands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
            @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cEste comando solo puede ser usado por jugadores.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§eUso: /serverengine teams<command>");
            return true;
        }

        String action = args[0].toLowerCase();

        // Este sqwich se encarga de losw subcomandos de la cámara

        switch (action) {
            case "create":
                player.sendMessage("Se creo el equipo.");
                return true;

            case "add":
                player.sendMessage("Añadir jugador.");
                return true;

            case "remove":
                player.sendMessage("Eliminar Jugador.");
                return true;

            default:
                player.sendMessage("§cSubcomando desconocido.");
                return true;
        }
    }
}
