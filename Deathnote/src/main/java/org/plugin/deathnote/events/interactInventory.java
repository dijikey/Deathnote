package org.plugin.deathnote.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import org.plugin.deathnote.menus.menuDeathnote;
import org.plugin.deathnote.utilits.killPlayer;

public class interactInventory implements Listener {
    private final menuDeathnote mainMenu;
    private final Plugin plugin;

    public interactInventory(menuDeathnote mainMenu, Plugin plugin) {
        this.mainMenu = mainMenu;
        this.plugin = plugin;
    }

    @EventHandler
    public void onEnable(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        try {
            if (event.getClickedInventory().getItem(12).getItemMeta().getPersistentDataContainer().has(NamespacedKey.fromString("deathnote") ) ) {
                int currTimeID = mainMenu.getCurrTimeID();
                int currIncidentID = mainMenu.getCurrIncidentID();
                int currTargetID = mainMenu.getCurrTargetIndex();

                event.setCancelled(true);
                // Checks which object was the interaction with
                switch (event.getCurrentItem().getType())
                {
                    case CLOCK -> mainMenu.setCurrTimeID(setTimeId(currTimeID, mainMenu.getTimes().size()));
                    case NAME_TAG -> mainMenu.setCurrIncidentID(setIncidentId(currIncidentID, mainMenu.getIncidents().size()));
                    case PLAYER_HEAD -> mainMenu.setCurrTargetIndex(setTargetIndex(currTargetID));
                    case LIME_STAINED_GLASS_PANE -> {
                        // Creates a timer until death, according to the specified variables
                        new killPlayer(mainMenu.getTarget(), currIncidentID, currTimeID, mainMenu.getTimes(), plugin);
                        player.closeInventory();
                    }
                    case  RED_STAINED_GLASS_PANE -> player.closeInventory();
                }
                // Updates the menu if the closing item was not pressed
                if (event.getCurrentItem().getType() != Material.RED_STAINED_GLASS_PANE && event.getCurrentItem().getType() != Material.LIME_STAINED_GLASS_PANE)
                    player.openInventory(mainMenu.getInventory());
            }
        }catch (IllegalArgumentException | NullPointerException error){
            error.getStackTrace();
        }
    }

    // SETTERS

    public int setTimeId(int id, int length)
    {
        if (id == length - 1){ return 0; }
        return id + 1;
    }

    public int setIncidentId(int id, int length)
    {
        if (id == length - 1){ return 0; }
        return id + 1;
    }

    public int setTargetIndex(int index)
    {
        if (index == Bukkit.getOnlinePlayers().size() - 1){ return 0; }
        return index + 1;
    }
}

