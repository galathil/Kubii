package fr.galathil.kubii.listeners.spigot;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.galathil.kubii.Kubii;
import fr.galathil.kubii.KubiiConf;

/**
 * In-game chat events listeners
 * 
 */
public class ChatListener implements Listener{
	
	
	// Events
	@EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
		sendToDiscordIfEnabled("<"+event.getPlayer().getName()+"> "+event.getMessage());
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		sendToDiscordIfEnabled(event.getPlayer().getName()+" joined the game");
	}
	
	@EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
		sendToDiscordIfEnabled(event.getPlayer().getName()+" left the game");
    }
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		if(KubiiConf.DEATH_COOS_IN_CHAT) {			
			Location deathLocation = event.getEntity().getLocation();
			event.setDeathMessage(event.getDeathMessage()+" ("+deathLocation.getWorld().getName()+" at x:"+deathLocation.getBlockX()+", y:"+deathLocation.getBlockY()+", z:"+deathLocation.getBlockZ()+")");
		}
		sendToDiscordIfEnabled(event.getDeathMessage());
	}
	
	
	
	// Tools
	
	private String sanitizeForDiscord(String messageToSanitize) {
		return messageToSanitize.replace("_", "\\_").replace("*", "\\*").replace("`","\\`").replace("~", "\\~");
	}
	
	private void sendToDiscordIfEnabled(String message, boolean sanitized) {
		
		if(!KubiiConf.DISCORD_ENABLED) {
			return;
		}
		
		if(sanitized) {
			message = sanitizeForDiscord(message);
		}
		
		if(KubiiConf.DISCORD_TEXT_CHANNEL_ID>0) {
			Kubii.discordBot.getTextChannelById(KubiiConf.DISCORD_TEXT_CHANNEL_ID).sendMessage(message).complete();
		}
	}
	
	private void sendToDiscordIfEnabled(String message) {
		sendToDiscordIfEnabled(message, true);
	}
}
