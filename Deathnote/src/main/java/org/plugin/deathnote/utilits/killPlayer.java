package org.plugin.deathnote.utilits;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.plugin.deathnote.executes.Executes;

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
        String command = config.getStringList("Incidents-execute").get(incidentID);
        String[] commandAttribute = command.trim().split(" ");

        for (Executes exe : Executes.values()){
            if (Objects.equals(commandAttribute[0], exe.name())){
                Bukkit.getLogger().info("Player use deathnote task %s on %s".formatted(exe.name(), target));
                exe.run(target, command, commandAttribute);
            }
        }
    }
}
