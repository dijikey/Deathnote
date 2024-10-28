package org.plugin.deathnote.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.plugin.deathnote.items.Book;

public class InteractBook implements Listener {
    private final Book Book;

    public InteractBook(Book book) {
        Book = book;
    }

    // Opens the menu if you have a book in your hand
    @EventHandler
    public void onEnable(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItem();
        try {
            if (!item.getItemMeta().hasCustomModelData()) { return; }

            if (item.getItemMeta().getCustomModelData() == Book.CustomID){
                if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR) ))
                {
                    player.performCommand("openDeathnote");
                }
            }
        }catch (NullPointerException err){
            err.getStackTrace();
        }
    }
}
