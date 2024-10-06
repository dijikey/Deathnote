package org.plugin.deathnote.executes;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Objects;

class Summon {
    Summon(Player target, String[] commandAttribute) {
        target.getWorld().spawnEntity(target.getLocation(), EntityType.fromName(commandAttribute[1]));
        setAdditionAttribute(commandAttribute, target);
    }

    private void setAdditionAttribute(String[] commandAttribute, Player target){
        for (AdditionAttribute add : AdditionAttribute.values()){
            for (byte i = 2; i < commandAttribute.length; i++){
                if (Objects.equals(commandAttribute[i], add.name())) {
                    add.init(target, commandAttribute, i);
                }
            }
        }
    }
}
