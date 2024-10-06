package org.plugin.deathnote.executes;

import org.bukkit.entity.Player;

enum AdditionAttribute {
    setHealth(){
        @Override
        void init(Player target, String[] commandAttribute, byte i) {
            target.setHealth(Double.parseDouble(commandAttribute[1]));
        }
    };

    abstract void init(Player target, String[] commandAttribute, byte i);
}
