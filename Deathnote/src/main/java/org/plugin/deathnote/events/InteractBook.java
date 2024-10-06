package org.plugin.deathnote.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.plugin.deathnote.items.Book;
import org.plugin.deathnote.menus.menuDeathnote;

public class InteractBook implements Listener {
    private final Book Book;
    private final menuDeathnote mainMenu;

    public InteractBook(Book book, menuDeathnote MainMenu) {
        Book = book;
        mainMenu = MainMenu;
    }

    // Opens the menu if you have a book in your hand
    @EventHandler
    public void onEnable(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItem();
        try {
            if (!item.getItemMeta().hasCustomModelData()) { return; }
            if (item.getItemMeta().getCustomModelData() == Book.getCustomID()){
                if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) && player.hasPermission("deathnote.command.use")){
                    mainMenu.setPlayer(player);
                    mainMenu.setDefaultVar();
                    player.openInventory(mainMenu.getInventory());
                }
            }
        }catch (NullPointerException err){
            err.getStackTrace();
        }
    }
}
