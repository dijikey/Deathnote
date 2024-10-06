package org.plugin.deathnote.executes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

class DeathSystem {
    private final Player target;
    private final String command;
    private final String[] commandAttribute;

    DeathSystem(Player target, String command, String[] commandAttribute) {
        this.target = target;
        this.command = command;
        this.commandAttribute = commandAttribute;
    }

    void targetKill() {
        target.setHealth(0);
    }

    void performCommand() {
        target.performCommand(command.replaceFirst("execute ", ""));
    }

    void createExplosion() {
        target.setHealth(0.5);
        target.getWorld().createExplosion(target.getLocation(), Float.parseFloat(commandAttribute[2]), false);
    }

    void summonEntity(){
        new Summon(target, commandAttribute);
    }

    void teleportTarget() {
        Location location = new Location(Bukkit.getWorld(commandAttribute[1]),
                Double.parseDouble(commandAttribute[2]),
                Double.parseDouble(commandAttribute[3]),
                Double.parseDouble(commandAttribute[4]));
        target.teleport(location);
    }
}
