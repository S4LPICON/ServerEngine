package com.zofrenic.serverengine.teams.model;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("deprecation")
public class Team {

    private final String name;
    private final ChatColor color;
    private final Set<UUID> members = new HashSet<>();

    public Team(String name, ChatColor color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }

    public Set<UUID> getMembers() {
        return Set.copyOf(members);
    }

    public boolean addPlayer(Player player) {
        return members.add(player.getUniqueId());
    }

    public boolean removePlayer(Player player) {
        return members.remove(player.getUniqueId());
    }

    public boolean isMember(Player player) {
        return members.contains(player.getUniqueId());
    }

    public void sendMessage(String message) {
        for (UUID uuid : members) {
            Player p = org.bukkit.Bukkit.getPlayer(uuid);
            if (p != null && p.isOnline()) {
                p.sendMessage(color + "[" + name + "] " + ChatColor.RESET + message);
            }
        }
    }
}
