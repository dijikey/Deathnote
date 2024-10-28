package org.plugin.sexyClasses;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

public class SpecialItem {
    public final Material item;
    public final String hashCode;
    public String name;
    public final String fun;

    public SpecialItem(String item, String hashCode, String name, String fun) {
        this.item = Material.getMaterial(item);
        this.hashCode = hashCode;
        this.name = name;
        this.fun = fun;
    }

    public Player player;
    public int currIncidentID = 0,
            currTimeID = 0,
            currTargetIndex = 0;

    public void focus(Player player, Integer... args){
        this.player = player;
        this.currIncidentID = args[0];
        this.currTimeID = args[1];
        this.currTargetIndex = args[2];
    }

    public ItemStack getItem(String _name){
        if (item == Material.PLAYER_HEAD) return generateItemSkull(_name);
        ItemStack item = new ItemStack(this.item);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(_name));
        switch (this.fun){
            case "_target" -> meta.getPersistentDataContainer().set(NamespacedKey.fromString("key"), PersistentDataType.INTEGER, currTargetIndex);
            case "_incident" -> meta.getPersistentDataContainer().set(NamespacedKey.fromString("key"), PersistentDataType.INTEGER, currIncidentID);
            case "_time" -> meta.getPersistentDataContainer().set(NamespacedKey.fromString("key"), PersistentDataType.INTEGER, currTimeID);
        }
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getItem(){
        return getItem(this.name);
    }

    private ItemStack generateItemSkull(String _name){
        ItemStack item = new ItemStack(this.item);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.displayName(Component.text(_name));
        skullMeta.setOwningPlayer(player);
        item.setItemMeta(skullMeta);

        return item;
    }
}
