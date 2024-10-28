package org.plugin.deathnote.sexyClasses;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.plugin.deathnote.menus.DeathnoteMenu;

import static org.plugin.deathnote.Deathnote.kira;

public enum ItemFunction {
    close("$close"){
        public void function(Player player, DeathnoteMenu menu) {
            player.closeInventory();
        }
    },
    apply("$apply"){
        @Override
        public void function(Player player, DeathnoteMenu menu) {
            kira.start(menu.getTarget(), menu.currIncidentID, menu.times.get(menu.currTimeID));
            player.closeInventory();
        }
    },
    time("_time"){
        @Override
        public void function(Player player, DeathnoteMenu menu) {
            menu.currTimeID = cycle(menu.currTimeID, menu.times.size());
        }
    },
    incident("_incident"){
        @Override
        public void function(Player player, DeathnoteMenu menu) {
            menu.currIncidentID = cycle(menu.currIncidentID, menu.incidents.size());
        }
    },
    target("_target"){
        @Override
        public void function(Player player, DeathnoteMenu menu) {
            menu.currTargetIndex = cycleTarget(menu.currTargetIndex);
        }
    };

    public final String hashCode;

    ItemFunction(String hashCode) {
        this.hashCode = hashCode;
    }

    public abstract void function(Player player, DeathnoteMenu menu);

    private static int cycle(int id, int length){
        if (id == length - 1){ return 0; }
        return id + 1;
    }

    private static int cycleTarget(int index)
    {
        if (index == Bukkit.getOnlinePlayers().size() - 1){ return 0; }
        return index + 1;
    }
}
