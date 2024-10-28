package org.plugin.deathnote.utilits;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.plugin.deathnote.executes.Executes;
import org.plugin.deathnote.menus.DeathnoteMenu;

import java.util.Objects;

public class KillPlayer implements Listener {
    private final Plugin plugin;
    private final DeathnoteMenu menu;

    // Starts a timer after which the player dies
    public KillPlayer(Plugin plugin, DeathnoteMenu menu){
        this.plugin = plugin;
        this.menu = menu;
    }

    public void start(Player target, int incidentID, int time){
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

        scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                executeIncidents(incidentID, target);
            }
        }, 20L* time);
    }

    // Checks which incident was selected and executes it
    private void executeIncidents(int incidentID, Player target) {
        String command = this.menu.incidents.get(incidentID).fun;
        String[] commandAttribute = command.trim().split(" ");
        for (Executes exe : Executes.values()){
            if (Objects.equals(commandAttribute[0], exe.name())){
                Bukkit.getLogger().info("Player use deathnote task %s on %s".formatted(exe.name(), target));
                exe.run(target, command, commandAttribute);
            }
        }
    }
}
