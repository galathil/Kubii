package fr.galathil.kubii.listeners.spigot;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.BookMeta;

import fr.galathil.kubii.Kubii;
import fr.galathil.kubii.KubiiAccount;
import fr.galathil.kubii.KubiiConf;

public class PlayersListener implements Listener{
	
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
    	if(KubiiConf.DISCORD_ENABLED && KubiiConf.DISCORD_ROLE_ID>0) {
        	for(KubiiAccount acc : Kubii.kubiiAccounts) {
        		if(acc.minecraftUuid.equals(event.getPlayer().getUniqueId().toString())) {
        			Kubii.discordBot.getGuildById(KubiiConf.DISCORD_SERVER_ID)
        	    	.addRoleToMember(Long.valueOf(acc.discordId), Kubii.discordBot.getRoleById(KubiiConf.DISCORD_ROLE_ID)).complete();
        			break;
        		}
        	}
    	}
    }
    
    
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
    	if(KubiiConf.DISCORD_ENABLED && KubiiConf.DISCORD_ROLE_ID>0) {
        	for(KubiiAccount acc : Kubii.kubiiAccounts) {
        		if(acc.minecraftUuid.equals(event.getPlayer().getUniqueId().toString())) {
        			Kubii.discordBot.getGuildById(KubiiConf.DISCORD_SERVER_ID)
        	    	.removeRoleFromMember(Long.valueOf(acc.discordId), Kubii.discordBot.getRoleById(KubiiConf.DISCORD_ROLE_ID)).complete();
        			break;
        		}
        	}
    	}
    }
    
    @EventHandler
    public void onPlayerEditBook(PlayerEditBookEvent e) {
    	if(KubiiConf.ALLOW_CUSTOM_COLORS_IN_BOOKS) {
    		if (!e.isSigning()){ 
    			return; 
    		}
    		
    		
    		// Get book
    		BookMeta book = e.getNewBookMeta();
    		
    		// Modify
    		List<String> pages = book.getPages();
    		List<String> newPages = new ArrayList<String>();
    		for (String page : pages){
    			newPages.add(ChatColor.translateAlternateColorCodes('&', page));
    		}
    		book.setPages(newPages);
    		
    		// Update
    		e.setNewBookMeta(book);
    	}

    }
}
