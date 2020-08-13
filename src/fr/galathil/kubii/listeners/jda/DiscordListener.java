package fr.galathil.kubii.listeners.jda;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.vdurmont.emoji.EmojiParser;

import fr.galathil.kubii.Kubii;
import fr.galathil.kubii.KubiiConf;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

/**
 * Discord bot event listener
 * 
 */
public class DiscordListener {

	@SubscribeEvent
    public void onReady(ReadyEvent event){
        Kubii.clearRoles();
    }
	
	@SubscribeEvent
	public void onMessageReceived(MessageReceivedEvent event) {
		
		// Text channel not defined
		if(KubiiConf.DISCORD_TEXT_CHANNEL_ID<=0){
			return;
		}
		
		// Message sended by the bot
		if(event.getAuthor().isBot()) {
			return;
		}
		
		// Message not posted in the right channel
		if(!event.getChannel().equals(Kubii.discordBot.getTextChannelById(KubiiConf.DISCORD_TEXT_CHANNEL_ID))){
			return;
		}
		
		String name;
		if(event.getMember().getNickname()==null) {
			name = sanitizeForMinecraft(event.getMember().getUser().getName());
		} else {
			name = sanitizeForMinecraft(event.getMember().getNickname());
		}
		name = EmojiParser.removeAllEmojis(name);
		
		String message = sanitizeForMinecraft(event.getMessage().getContentStripped());
		message = EmojiParser.parseToAliases(message);
				
		Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "[Discord] " + ChatColor.WHITE + "<"+name+"> "+message);
	}
	
	private String sanitizeForMinecraft(String message) {
		return message.replace("§", "");
	}
}
