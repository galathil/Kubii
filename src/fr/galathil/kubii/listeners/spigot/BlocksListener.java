package fr.galathil.kubii.listeners.spigot;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import fr.galathil.kubii.KubiiConf;

public class BlocksListener implements Listener{

	@EventHandler
    public void onSignChange(final SignChangeEvent e) {
		if(KubiiConf.ALLOW_CUSTOM_COLORS_ON_SIGNS) {
            e.setLine(0, cc(e.getLine(0)));
            e.setLine(1, cc(e.getLine(1)));
            e.setLine(2, cc(e.getLine(2)));
            e.setLine(3, cc(e.getLine(3)));
		}
    }
	
    private static String cc(final String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
