package org.plugin.deathnote.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.plugin.deathnote.menus.DeathnoteMenu;

public class OpenDeathnoteMenu implements CommandExecutor {
    private final DeathnoteMenu menu;
    public OpenDeathnoteMenu(DeathnoteMenu menu){
        this.menu = menu;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)) return true;

        menu.setPlayer(player);
        menu.setDefaultVar();
        player.openInventory(menu.getInventory());
        return false;
    }
}
