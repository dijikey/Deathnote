package org.plugin.deathnote.menus;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.plugin.sexyClasses.Incident;
import org.plugin.sexyClasses.Placeholders;
import org.plugin.sexyClasses.SpecialItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.plugin.deathnote.Deathnote.config;

public class DeathnoteMenu implements InventoryHolder {
    private Player player;
    public final String title;
    public final int size;
    public static ItemStack[] MENU_CONTENTS = null;

    public int currIncidentID = 0,
            currTimeID = 0,
            currTargetIndex = 0;
    // Incidents and times
    public final List<Incident> incidents = new ArrayList<>();
    public final List<Integer> times;
    public final List<SpecialItem> items = new ArrayList<>();

    public DeathnoteMenu(int rows, String title) {
        this.size = 9*rows;
        this.title = title;
        short i = 0;
        for (String el : config.getStringList("Incidents-list")){
            incidents.add(new Incident(el, config.getStringList("Incidents-fun").get(i)));
            i++;
        }
        this.times = config.getIntegerList("Times-list");

        loadItem();
    }

    // Creates a book menu
    @Override
    public @NotNull Inventory getInventory() {
        Inventory menu = Bukkit.createInventory(player, size, Component.text(title));
        loadItemInMenu(menu);
        MENU_CONTENTS = menu.getContents();

        return menu;
    }

    private void loadItem(){
        String substring;
        for (String K : config.getStringList("Menu.view")) {
            substring = K.substring(K.indexOf("%") + 1);
            items.add(new SpecialItem(
                    config.getString("Menu.items.%s.item".formatted(substring)),
                    substring,
                    config.getString("Menu.items.%s.name".formatted(substring)),
                    config.getString("Menu.items.%s.fun".formatted(substring))
            ));
        }
    }

    private void loadItemInMenu(Inventory menu) {
        SpecialItem item;
        List<String> s = config.getStringList("Menu.view");
        for (int i = 0; i < config.getStringList("Menu.view").size(); i++) {
            item = items.get(i);
            item.focus(getTarget().getPlayer(), currIncidentID, currTimeID, currTargetIndex);
            if (item.name.contains("%")){
                menu.setItem(Integer.parseInt( s.get(i).substring(0, s.get(i).indexOf("%") ) ),
                        item.getItem(replaceName(item.name))
                );
            }else {
                menu.setItem(Integer.parseInt( s.get(i).substring(0, s.get(i).indexOf("%") ) ),
                        item.getItem()
                );
            }


        }
    }

    private String replaceName(String name){
        String oldValue = name.substring(name.indexOf("%"), name.lastIndexOf("%") + 1);
        for (Placeholders el : Placeholders.values()){
            if (Objects.equals(el.placeholder, oldValue)){
                return el.replace(name, oldValue, this);
            }
        }

        return "1-1";
    }

    // SETTERS
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDefaultVar()
    {
        this.currIncidentID = 0;
        this.currTimeID = 0;
        this.currTargetIndex = 0;
    }

    public Player getTarget(){
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        return players.get(currTargetIndex);
    }
}
