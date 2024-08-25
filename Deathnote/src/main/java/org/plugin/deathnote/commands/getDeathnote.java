package org.plugin.deathnote.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class getDeathnote implements CommandExecutor {
    private final ItemStack itemDeathnote;

    public getDeathnote(ItemStack itemDeathnote) {
        this.itemDeathnote = itemDeathnote;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)) return true;

        player.getInventory().addItem(itemDeathnote);

        return false;
    }
}
