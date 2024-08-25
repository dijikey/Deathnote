package org.plugin.deathnote.utilits;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class killPlayer implements Listener {
    public killPlayer(Player target, int incidentID, int timeIndex,int[] timeliest, Plugin plugin){
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                runTaskIncident(incidentID, target);
            }
        }, 20L*timeliest[timeIndex]);
    }

    public void runTaskIncident(int incidentID, Player target) {
        switch (incidentID){
            case 0 -> HeartAttack(target);
            case 1 -> BlowUp(target);
            case 2 -> Paralyzed(target);
        }
    }

    public void HeartAttack(Player target) { target.setHealth(0); }
    public void BlowUp(Player target) {
        target.setHealth(0.5);
        TNTPrimed tnt = (TNTPrimed) target.getWorld().spawnEntity(target.getLocation(), EntityType.TNT);
        tnt.setFuseTicks(0);
    }
    public void Paralyzed(Player target) {
        target.setHealth(0.5);
        target.getWorld().strikeLightning(target.getLocation());
    }
}
