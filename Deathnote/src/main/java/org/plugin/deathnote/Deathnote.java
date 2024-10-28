package org.plugin.deathnote;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.plugin.deathnote.commands.GetDeathnote;
import org.plugin.deathnote.commands.OpenDeathnoteMenu;
import org.plugin.deathnote.events.InteractBook;
import org.plugin.deathnote.events.InteractInventory;
import org.plugin.deathnote.items.Book;
import org.plugin.deathnote.menus.DeathnoteMenu;
import org.plugin.deathnote.utilits.KillPlayer;

public final class Deathnote extends JavaPlugin {

    public static FileConfiguration config;
    public static KillPlayer kira;

    @Override
    public void onEnable() {
        loadConfig();
        DeathnoteMenu menu = new DeathnoteMenu(
                config.getInt("Menu.config.rows", 3),
                config.getString("Menu.config.title", "Deathnote"));

        Book Book = new Book(config.getInt("CustomModelData"),
                config.getString("Title-book"),
                Material.getMaterial(config.getString("MaterialDeathnote")));

        kira = new KillPlayer(this, menu);

        // Adds a command to receive a deathnote
        getCommand("getDeathnote").setExecutor(new GetDeathnote(Book.getItem()));
        getCommand("openDeathnote").setExecutor(new OpenDeathnoteMenu(menu));


        // Tracks whether the right click was pressed with a book in hand
        getServer().getPluginManager().registerEvents(new InteractBook(Book), this);
        // Tracks interactions with the book menu
        getServer().getPluginManager().registerEvents(new InteractInventory(menu), this);
    }

    private void loadConfig(){
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        config = getConfig();
    }
}
