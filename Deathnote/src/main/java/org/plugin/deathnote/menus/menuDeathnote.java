package org.plugin.deathnote.menus;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class menuDeathnote implements InventoryHolder {
    private Player player;
    private final int size;
    private int currIncidentID, currTimeID, currTargetIndex;
    private final String title;
    private final String[] incidentName = {"will die of a heart attack", "will be blown up", "will be paralyzed by a lightning strike"};
    private final int[] times = {0, 5, 10, 20, 40, 80, 120, 200};

    public menuDeathnote(int rows, String title, int currIncidentID, int currTimeID, int currTargetID) {
        this.size = 9*rows;
        this.title = title;
        this.currIncidentID = currIncidentID;
        this.currTimeID = currTimeID;
        this.currTargetIndex = currTargetID;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public @NotNull Inventory getInventory() {
        Inventory menu = Bukkit.createInventory(player, size, Component.text(title));
        ItemStack item;
        ItemMeta meta;

        item = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        meta = item.getItemMeta();
        meta.displayName(Component.text(" "));
        item.setItemMeta(meta);
        for (int x = 0; x < size; x++)
        {
            menu.setItem(x, item);
        }

        item = new ItemStack(Material.PLAYER_HEAD);
        meta = item.getItemMeta();
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.displayName(Component.text(ChatColor.RESET + getTarget().getName()));
        skullMeta.getPersistentDataContainer().set(NamespacedKey.fromString("deathnote"), PersistentDataType.BOOLEAN, true);
        skullMeta.setOwningPlayer(getTarget());
        item.setItemMeta(skullMeta);
        menu.setItem(12, item);

        item = new ItemStack(Material.NAME_TAG);
        meta = item.getItemMeta();
        meta.displayName(Component.text(ChatColor.WHITE + incidentName[currIncidentID]));
        meta.getPersistentDataContainer().set(NamespacedKey.fromString("id"), PersistentDataType.INTEGER, currIncidentID);
        item.setItemMeta(meta);
        menu.setItem(13, item);

        item = new ItemStack(Material.CLOCK);
        meta = item.getItemMeta();
        meta.displayName(Component.text(ChatColor.WHITE + "after " + ChatColor.BOLD + times[currTimeID] + ChatColor.WHITE + " seconds"));
        meta.getPersistentDataContainer().set(NamespacedKey.fromString("id"), PersistentDataType.INTEGER, currTimeID);
        item.setItemMeta(meta);
        menu.setItem(14, item);

        item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        meta = item.getItemMeta();
        meta.displayName(Component.text(ChatColor.RED + "Close"));
        item.setItemMeta(meta);
        menu.setItem(18, item);

        item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        meta = item.getItemMeta();
        meta.displayName(Component.text(ChatColor.GREEN + "Apply"));
        item.setItemMeta(meta);
        menu.setItem(26, item);

        return menu;
    }

    public Player getTarget(){
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        return players.get(currTargetIndex);
    }

    public Player getPlayer() {
        return player;
    }

    public int getCurrIncidentID() {
        return currIncidentID;
    }

    public int getCurrTimeID() {
        return currTimeID;
    }

    public void setCurrIncidentID(int currIncidentID) {
        this.currIncidentID = currIncidentID;
    }

    public void setCurrTimeID(int currTimeID) {
        this.currTimeID = currTimeID;
    }

    public void setDefaultVar()
    {
        this.currIncidentID = 0;
        this.currTimeID = 4;
        this.currTargetIndex = 0;
    }

    public void setCurrTargetIndex(int id)
    {
        this.currTargetIndex = id;
    }

    public int getCurrTargetIndex() {
        return currTargetIndex;
    }

    public int[] getTimes() {
        return times;
    }
}
