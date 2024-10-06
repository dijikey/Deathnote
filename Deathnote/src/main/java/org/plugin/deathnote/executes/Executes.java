package org.plugin.deathnote.executes;

import org.bukkit.entity.Player;

public enum Executes {
    kill(){
        @Override
        public void run(Player target, String command, String[] commandAttribute) {
            super.run(target, command, commandAttribute);
            sys.targetKill();
        }
    },
    teleport(){
        @Override
        public void run(Player target, String command, String[] commandAttribute) {
            super.run(target, command, commandAttribute);
            sys.teleportTarget();
        }
    },
    summon(){
        @Override
        public void run(Player target, String command, String[] commandAttribute) {
            super.run(target, command, commandAttribute);
            sys.summonEntity();
        }
    },
    explosion(){
        @Override
        public void run(Player target, String command, String[] commandAttribute) {
            super.run(target, command, commandAttribute);
            sys.createExplosion();
        }
    },
    execute(){
        @Override
        public void run(Player target, String command, String[] commandAttribute) {
            super.run(target, command, commandAttribute);
            sys.performCommand();
        }
    };

    private static DeathSystem sys;

    public void run(Player target, String command, String[] commandAttribute) {
        sys = new DeathSystem(target, command, commandAttribute);
    }
}
