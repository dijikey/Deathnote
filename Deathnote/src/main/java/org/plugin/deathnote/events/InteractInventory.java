package org.plugin.deathnote.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.plugin.deathnote.menus.DeathnoteMenu;
import org.plugin.sexyClasses.ItemFunction;
import org.plugin.sexyClasses.SpecialItem;

import java.util.Arrays;
import java.util.Objects;

import static org.plugin.deathnote.menus.DeathnoteMenu.MENU_CONTENTS;

public class InteractInventory implements Listener {
    private final DeathnoteMenu menu;

    public InteractInventory(DeathnoteMenu menu) {
        this.menu = menu;
    }

    @EventHandler
    public void onEnable(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        try {
            if (Arrays.equals(event.getClickedInventory().getContents(), MENU_CONTENTS)) {
                event.setCancelled(true);
                for (SpecialItem item : menu.items){
                    if (Objects.equals(item.item, event.getCurrentItem().getType())){
                        for (ItemFunction fun : ItemFunction.values()){
                            if (Objects.equals(item.fun, fun.hashCode)){
                                fun.function(player, menu);
                                break;
                            }
                        }
                        break;
                    }
                }

                //I know about this, I’ll fix it somehow (*/ω＼*)
                if (!(event.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE || event.getCurrentItem().getType() == Material.LIME_STAINED_GLASS_PANE))
                {
                    player.openInventory(menu.getInventory());
                }
            }
        }catch (IllegalArgumentException | NullPointerException error){
            error.getStackTrace();
        }
    }
}

