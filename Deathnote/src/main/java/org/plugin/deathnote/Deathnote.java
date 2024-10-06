package org.plugin.deathnote;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.plugin.deathnote.commands.GetWorldName;
import org.plugin.deathnote.commands.GetDeathnote;
import org.plugin.deathnote.events.InteractBook;
import org.plugin.deathnote.events.InteractInventory;
import org.plugin.deathnote.items.Book;
import org.plugin.deathnote.menus.menuDeathnote;

public final class Deathnote extends JavaPlugin {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        FileConfiguration config = getConfig();

        menuDeathnote menuDN = new menuDeathnote(3, "Deathnote", config);
        Book Book = new Book(config.getInt("CustomModelData"), config.getString("TileBook"), Material.getMaterial(config.getString("MaterialDeathnote")));

        // Adds a command to receive a deathnote
        getCommand("getDeathnote").setExecutor(new GetDeathnote(Book.getItem()));
        getCommand("getWorldName").setExecutor(new GetWorldName());
        // Tracks whether the right click was pressed with a book in hand
        getServer().getPluginManager().registerEvents(new InteractBook(Book, menuDN), this);
        // Tracks interactions with the book menu
        getServer().getPluginManager().registerEvents(new InteractInventory(menuDN, this), this);
    }
}
