package org.plugin.deathnote.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class book {
    private final int CustomID;
    private final String NameBook;
    private final Material MaterialItem;

    public book(int CustomID, String NameBook, Material MaterialItem) {
        this.CustomID = CustomID;
        this.NameBook = NameBook;
        this.MaterialItem = MaterialItem;
    }

    public int getCustomID() {
        return CustomID;
    }

    public String getNameBook() {
        return NameBook;
    }

    public Material getMaterialItem() {
        return MaterialItem;
    }

    public ItemStack getItem(){
        ItemStack item = new ItemStack(getMaterialItem());
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(getNameBook()));
        meta.setCustomModelData(getCustomID());
        item.setItemMeta(meta);
        return item;
    }
}
