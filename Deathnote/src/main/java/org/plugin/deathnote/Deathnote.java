package org.plugin.deathnote;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.plugin.deathnote.commands.GetWorldName;
import org.plugin.deathnote.commands.getDeathnote;
import org.plugin.deathnote.events.interactBook;
import org.plugin.deathnote.events.interactInventory;
import org.plugin.deathnote.items.book;
import org.plugin.deathnote.menus.menuDeathnote;

public final class Deathnote extends JavaPlugin {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        FileConfiguration config = getConfig();

        menuDeathnote menuDN = new menuDeathnote(3, "Deathnote", config);
        book Book = new book(config.getInt("CustomModelData"), config.getString("TileBook"), Material.getMaterial(config.getString("MaterialDeathnote")));

        // Adds a command to receive a deathnote
        getCommand("getDeathnote").setExecutor(new getDeathnote(Book.getItem()));
        getCommand("getWorldName").setExecutor(new GetWorldName());
        // Tracks whether the right click was pressed with a book in hand
        getServer().getPluginManager().registerEvents(new interactBook(Book, menuDN), this);
        // Tracks interactions with the book menu
        getServer().getPluginManager().registerEvents(new interactInventory(menuDN, this), this);
    }
}
