package fr.galathil.kubii;

import java.util.ArrayList;
import java.util.logging.Level;

public class KubiiConf {

	public static boolean DISCORD_ENABLED				= false;
	public static String DISCORD_TOKEN					= "";
	public static long DISCORD_SERVER_ID				= 0;
	public static long DISCORD_ROLE_ID					= 0;
	public static long DISCORD_TEXT_CHANNEL_ID			= 0;

	public static ArrayList<String> LIST_WORLDS_NO_GRIEF_CREEPER 			= new ArrayList<String>();
	public static ArrayList<String> LIST_WORLDS_NO_GRIEF_ENDERMAN 			= new ArrayList<String>();
	public static ArrayList<String> LIST_WORLDS_NO_GRIEF_FIREBALL 			= new ArrayList<String>();
	public static ArrayList<String> LIST_WORLDS_NO_SPAWN_WITHER 			= new ArrayList<String>();
	public static ArrayList<String> LIST_WORLDS_NO_SPAWN_GHAST 				= new ArrayList<String>();
	public static ArrayList<String> LIST_WORLDS_NO_SPAWN_ZOMBIFIED_PIGLIN 	= new ArrayList<String>();
	public static ArrayList<String> LIST_WORLDS_NO_SPAWN_SLIME 				= new ArrayList<String>();
	
	public static boolean ALLOW_CUSTOM_COLORS_ON_SIGNS	= true;
	public static boolean ALLOW_CUSTOM_COLORS_IN_BOOKS	= true;
	
	public static boolean NOT_TOO_EXPENSIVE		= false;
	public static boolean DEATH_COOS_IN_CHAT	= false;

	public static boolean loadConfig(Kubii pluginInstance) {
		
		// Discord
		DISCORD_ENABLED = pluginInstance.getConfig().getBoolean("kubii.discord.enable", false);
		if(DISCORD_ENABLED) {
			DISCORD_TOKEN = pluginInstance.getConfig().getString("kubii.discord.token", "");
			if(DISCORD_TOKEN.equals("")){
				pluginInstance.getLogger().log(Level.SEVERE, "No discord token provided.");
				return false;
			}
			
			DISCORD_SERVER_ID = pluginInstance.getConfig().getLong("kubii.discord.server-id", 0);
			if(DISCORD_SERVER_ID==0){
				pluginInstance.getLogger().log(Level.SEVERE, "No discord server ID provided.");
				return false;
			}
			
			DISCORD_ROLE_ID = pluginInstance.getConfig().getLong("kubii.discord.role-id", 0);
			DISCORD_TEXT_CHANNEL_ID = pluginInstance.getConfig().getLong("kubii.discord.text-channel-id", 0);
		} else {
			pluginInstance.getLogger().log(Level.INFO, "Discord features disabled.");
		}
		
		// no-grief
		LIST_WORLDS_NO_GRIEF_CREEPER = (ArrayList<String>) pluginInstance.getConfig().getStringList("kubii.no-grief.creeper");
		LIST_WORLDS_NO_GRIEF_ENDERMAN = (ArrayList<String>) pluginInstance.getConfig().getStringList("kubii.no-grief.enderman");
		LIST_WORLDS_NO_GRIEF_FIREBALL = (ArrayList<String>) pluginInstance.getConfig().getStringList("kubii.no-grief.fireball");
		
		// Custom colors
		ALLOW_CUSTOM_COLORS_ON_SIGNS = pluginInstance.getConfig().getBoolean("kubii.custom-colors.on-signs", true);
		ALLOW_CUSTOM_COLORS_IN_BOOKS = pluginInstance.getConfig().getBoolean("kubii.custom-colors.in-books", true);
		
		// no-spawn
		LIST_WORLDS_NO_SPAWN_SLIME.clear();
		LIST_WORLDS_NO_SPAWN_SLIME = (ArrayList<String>) pluginInstance.getConfig().getStringList("kubii.no-spawn.slime");
		
		LIST_WORLDS_NO_SPAWN_WITHER.clear();
		LIST_WORLDS_NO_SPAWN_WITHER = (ArrayList<String>) pluginInstance.getConfig().getStringList("kubii.no-spawn.wither");
		
		LIST_WORLDS_NO_SPAWN_GHAST.clear();
		LIST_WORLDS_NO_SPAWN_GHAST = (ArrayList<String>) pluginInstance.getConfig().getStringList("kubii.no-spawn.ghast");
		
		LIST_WORLDS_NO_SPAWN_ZOMBIFIED_PIGLIN.clear();
		LIST_WORLDS_NO_SPAWN_ZOMBIFIED_PIGLIN = (ArrayList<String>) pluginInstance.getConfig().getStringList("kubii.no-spawn.zombified-piglin");

		NOT_TOO_EXPENSIVE = pluginInstance.getConfig().getBoolean("kubii.misc.not-too-expensive", false);
		DEATH_COOS_IN_CHAT = pluginInstance.getConfig().getBoolean("kubii.misc.death-coos-in-chat", false);
		
		return true;
	}
}
