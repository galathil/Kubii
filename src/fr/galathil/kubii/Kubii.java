package fr.galathil.kubii;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.security.auth.login.LoginException;

import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONArray;
import org.json.JSONObject;

import fr.galathil.kubii.listeners.jda.DiscordListener;
import fr.galathil.kubii.listeners.spigot.BlocksListener;
import fr.galathil.kubii.listeners.spigot.ChatListener;
import fr.galathil.kubii.listeners.spigot.EntitiesListener;
import fr.galathil.kubii.listeners.spigot.InventoryListener;
import fr.galathil.kubii.listeners.spigot.PlayersListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;

public class Kubii extends JavaPlugin {
	
	public static JDA discordBot;
	public static ArrayList<KubiiAccount> kubiiAccounts;
	public static Kubii instance;
	
	
    // Fired when plugin is first enabled
    @Override
    public void onEnable() { 
    	getServer().getPluginManager().registerEvents(new PlayersListener(), this);
    	getServer().getPluginManager().registerEvents(new ChatListener(), this);
    	getServer().getPluginManager().registerEvents(new EntitiesListener(), this);
    	getServer().getPluginManager().registerEvents(new BlocksListener(), this);
    	getServer().getPluginManager().registerEvents(new InventoryListener(), this);
 
    	if(!KubiiConf.loadConfig(this)) {
    		getLogger().log(Level.SEVERE, "Configuration error detected, abort.");
    		return;
    	}
    	
    	kubiiAccounts = new ArrayList<KubiiAccount>();
    	loadKubiiAccounts();
    	getLogger().info(String.format(" %s kubiiAccounts loaded.",kubiiAccounts.size()));
    	
    	if(KubiiConf.DISCORD_ENABLED) {
        	try {
    			discordBot = new JDABuilder(KubiiConf.DISCORD_TOKEN)
    					.setEventManager(new AnnotatedEventManager())
    		            .addEventListeners(new DiscordListener())
    					.build();
    		} catch (LoginException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	
    	instance=this;
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	getLogger().log(Level.INFO, "onDisable()");
    	clearRoles();
    	if(KubiiConf.DISCORD_ENABLED) {
    		discordBot.shutdown();
    	}
    }
    
    private static void loadKubiiAccounts() {
    	// Load cached news
		String sourceText;
		kubiiAccounts.clear();
		try {
			sourceText = new String(Files.readAllBytes(Paths.get("plugins/Kubii/kubiiAccounts.json")), StandardCharsets.UTF_8);
			JSONArray jsonArr = new JSONArray(sourceText);
			for(Object line : jsonArr) {
				JSONObject obj = (JSONObject) line;
				kubiiAccounts.add(new KubiiAccount(obj.getString("minecraft_uuid"),obj.getString("discord_id")));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void clearRoles() {
    	if(KubiiConf.DISCORD_ENABLED && KubiiConf.DISCORD_ROLE_ID>0) {
        	Role r = Kubii.discordBot.getRoleById(KubiiConf.DISCORD_ROLE_ID);
        	for (Member m : discordBot.getGuildById(KubiiConf.DISCORD_SERVER_ID).getMembersWithRoles(r)){
        		discordBot.getGuildById(KubiiConf.DISCORD_ROLE_ID).removeRoleFromMember(m, r).complete();
        	}
    	}
    }
}
