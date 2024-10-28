package org.plugin.deathnote.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Book {
    public final int CustomID;
    public final String NameBook;
    public final Material MaterialItem;

    public Book(int CustomID, String NameBook, Material MaterialItem) {
        this.CustomID = CustomID;
        this.NameBook = NameBook;
        this.MaterialItem = MaterialItem;
    }

    public ItemStack getItem(){
        ItemStack item = new ItemStack(MaterialItem);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(NameBook));
        meta.setCustomModelData(CustomID);
        item.setItemMeta(meta);
        return item;
    }
}
