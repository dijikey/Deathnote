package org.plugin.sexyClasses;

import org.plugin.deathnote.menus.DeathnoteMenu;

public enum Placeholders {
    target("%target%"){
        @Override
        public String replace(String name, String oldValue, DeathnoteMenu menu) {
            return name.replace(oldValue, menu.getTarget().getName());
        }
    },
    incident("%incident%"){
        @Override
        public String replace(String name, String oldValue, DeathnoteMenu menu) {
            return name.replace(oldValue, menu.incidents.get(menu.currIncidentID).name);
        }
    },
    time("%time%"){
        @Override
        public String replace(String name, String oldValue, DeathnoteMenu menu) {
            return name.replace(oldValue, menu.times.get(menu.currTimeID).toString());
        }
    };

    public final String placeholder;

    Placeholders(String placeholder) {
        this.placeholder = placeholder;
    }

    public abstract String replace(String name,String oldValue, DeathnoteMenu menu);
}
