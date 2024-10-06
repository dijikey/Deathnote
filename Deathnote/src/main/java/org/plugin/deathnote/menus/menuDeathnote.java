package org.plugin.deathnote.menus;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
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
    private int currIncidentID = 0, currTimeID = 0, currTargetIndex = 0;
    private final String title;
    // All incidents
    private final List<String> incidentName;
    // All times
    private final List<Integer> times;

    //Constructor
    public menuDeathnote(int rows, String title, FileConfiguration config) {
        this.size = 9*rows;
        this.title = title;
        this.incidentName = loadIncidentList(config);
        this.times = loadTimesList(config);
    }

    // Creates a book menu
    @Override
    public @NotNull Inventory getInventory() {
        Inventory menu = Bukkit.createInventory(player, size, Component.text(title));

        menu.setItem(12, generateItemSkull(Component.text("§r" + getTarget().getName())));
        menu.setItem(13, generateItem(Material.NAME_TAG, Component.text("§f"+ incidentName.get(currIncidentID)), currIncidentID));
        menu.setItem(14, generateItem(Material.CLOCK, Component.text("§fafter §l" + times.get(currTimeID) + "§f seconds"), currTimeID));

        menu.setItem(18, generateItem(Material.RED_STAINED_GLASS_PANE, Component.text("§cClose")));
        menu.setItem(26, generateItem(Material.LIME_STAINED_GLASS_PANE, Component.text("§aApply")));

        return menu;
    }

    // Creates a head object
    private ItemStack generateItemSkull(Component displayName){
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.displayName(displayName);
        skullMeta.getPersistentDataContainer().set(NamespacedKey.fromString("deathnote"), PersistentDataType.BOOLEAN, true);
        skullMeta.setOwningPlayer(getTarget());
        item.setItemMeta(skullMeta);

        return item;
    }


    // Creates an item without attributes
    private ItemStack generateItem(Material material, Component displayName){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(displayName);
        item.setItemMeta(meta);

        return item;
    }

    // Creates an item with attributes
    private ItemStack generateItem(Material material, Component displayName, int var){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(displayName);
        meta.getPersistentDataContainer().set(NamespacedKey.fromString("id"), PersistentDataType.INTEGER, var);
        item.setItemMeta(meta);

        return item;
    }

    // SETTERS

    public void setPlayer(Player player) {
        this.player = player;
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
        this.currTimeID = 0;
        this.currTargetIndex = 0;
    }

    public void setCurrTargetIndex(int id)
    {
        this.currTargetIndex = id;
    }

    // GETTERS

    private List<Integer> loadTimesList(FileConfiguration config){
        return config.getIntegerList("Times-list");
    }

    private List<String> loadIncidentList(FileConfiguration config){
        return config.getStringList("Incidents-list");
    }

    public int getCurrIncidentID() {
        return currIncidentID;
    }

    public int getCurrTimeID() {
        return currTimeID;
    }

    public int getCurrTargetIndex() {
        return currTargetIndex;
    }

    public List<Integer> getTimes() {
        return times;
    }
    public List<String> getIncidents() {
        return incidentName;
    }

    public Player getTarget(){
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        return players.get(currTargetIndex);
    }
}
