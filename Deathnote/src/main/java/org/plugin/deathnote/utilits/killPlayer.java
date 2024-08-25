package org.plugin.deathnote.utilits;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;
import java.util.Objects;

public class killPlayer implements Listener {
    // Starts a timer after which the player dies
    public killPlayer(Player target, int incidentID, int timeIndex, List<Integer> timeliest, Plugin plugin){
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        FileConfiguration config = plugin.getConfig();
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                executeIncidents(incidentID, target, config);
            }
        }, 20L* timeliest.get(timeIndex));
    }

    // Checks which incident was selected and executes it
    private void executeIncidents(int incidentID, Player target, FileConfiguration config) {
        String incident = config.getStringList("Incidents-execute").get(incidentID);
        String[] varIncident = incident.split(" ");
        if (varIncident[0].equalsIgnoreCase("kill")){
            kill(target);
        }else if (varIncident[0].equalsIgnoreCase("summon")){

        }
        switch (varIncident[0]){
            case "kill" -> kill(target);
            case "summon" -> summon(getEntityType(varIncident[1]),target.getLocation(), target.getWorld(), varIncident, target);
            case "explosion" -> explosion(target.getWorld(), target.getLocation(), Float.parseFloat(varIncident[2]), target);
            case "teleport" -> teleport(target, varIncident);
            case "execute" -> execute(target, incident);
        }
    }
    // Getters
    private EntityType getEntityType(String entity){
        return EntityType.fromName(entity);
    }

    // Executes
    private void kill(Player target){
        target.setHealth(0);
    }
    private void summon(EntityType entity,Location location, World world, String[] varIncident, Player target){
        if (varIncident[2] != null) {
            switch (varIncident[2]) {
                case "setHealth" -> target.setHealth(Double.parseDouble(varIncident[3]));
            }
        }
        world.spawnEntity(location, entity);
    }
    private void explosion(World world, Location location, float strength, Player target){
        target.setHealth(0.5);
        world.createExplosion(location, strength, false);
    }
    private void teleport(Player target, String[] varIncident){
        Location location = new Location(Bukkit.getWorld(varIncident[1]), Double.parseDouble(varIncident[2]), Double.parseDouble(varIncident[3]), Double.parseDouble(varIncident[4]));
        target.teleport(location);
    }
    private void execute(Player target, String varCommand){
        target.performCommand(varCommand.replaceFirst("execute ", ""));
    }
}
