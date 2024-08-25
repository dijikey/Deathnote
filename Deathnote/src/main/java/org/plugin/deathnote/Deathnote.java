package org.plugin.deathnote;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.plugin.deathnote.commands.getDeathnote;
import org.plugin.deathnote.events.interactBook;
import org.plugin.deathnote.events.interactInventory;
import org.plugin.deathnote.items.book;
import org.plugin.deathnote.menus.menuDeathnote;

public final class Deathnote extends JavaPlugin {

    @Override
    public void onEnable() {
        menuDeathnote menuDN = new menuDeathnote(3, "Deathnote", 0, 4, 0);
        book Book = new book(1110111, "Deathnote", Material.BOOK);

        // Adds a command to receive a deathnote
        getCommand("getDeathnote").setExecutor(new getDeathnote(Book.getItem()));
        // Tracks whether the right click was pressed with a book in hand
        getServer().getPluginManager().registerEvents(new interactBook(Book, menuDN), this);
        // Tracks interactions with the book menu
        getServer().getPluginManager().registerEvents(new interactInventory(menuDN, this), this);
    }
}
