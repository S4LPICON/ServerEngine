package com.zofrenic.serverengine.teams.manager;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.zofrenic.serverengine.teams.model.Team;

public class TeamsManager {

    private final Map<String, Team> teams;

    public TeamsManager() {
        this.teams = new HashMap<>();
    }

    // Add a new team
    @SuppressWarnings("deprecation")
    public boolean addTeam(String teamName) {
        if (teams.containsKey(teamName)) {
            return false; // Team already exists
        }
        teams.put(teamName, new Team(teamName, ChatColor.BLUE));
        return true;
    }

    // Remove a team
    public boolean removeTeam(String teamName) {
        if (!teams.containsKey(teamName)) {
            return false; // Team does not exist
        }
        teams.remove(teamName);
        return true;
    }

    // Add a player to a team
    public boolean addPlayerToTeam(String teamName, Player player) {
        Team team = teams.get(teamName);
        if (team == null) {
            return false; // Team does not exist
        }
        return team.addPlayer(player);
    }

    // Remove a player from a team
    public boolean removePlayerFromTeam(String teamName, Player player) {
        Team team = teams.get(teamName);
        if (team == null) {
            return false; // Team does not exist
        }
        return team.removePlayer(player);
    }

    // Get a team by name
    public Team getTeam(String teamName) {
        return teams.get(teamName);
    }

    // Check if a player is in a team
    public boolean isPlayerInTeam(Player player) {
        for (Team team : teams.values()) {
            if (team.isMember(player)) {
                return true;
            }
        }
        return false;
    }
}