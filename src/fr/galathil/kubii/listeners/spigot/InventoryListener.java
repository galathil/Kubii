package fr.galathil.kubii.listeners.spigot;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;

import fr.galathil.kubii.Kubii;
import fr.galathil.kubii.KubiiConf;

public class InventoryListener implements Listener {

	private static int MINECRAFT_MAX_REPAIR_ALLOWED = 39;
	
	@EventHandler
	public void onPrepareAnvilEvent(PrepareAnvilEvent e) {
		if(KubiiConf.NOT_TOO_EXPENSIVE && e.getInventory().getRepairCost()>MINECRAFT_MAX_REPAIR_ALLOWED) {
			
			// Only prepare the server, client don't care about it.
			e.getInventory().setMaximumRepairCost(Integer.MAX_VALUE);
			
			// Tricks from https://www.spigotmc.org/threads/set-anvil-cost.382966/
			// Note that AnvilInventory#setRepairCost also does not notify the client that the cost has changed. 
			// This is a Spigot issue that can be worked around by using a sync task to modify it after the event has completed but before the client is actually informed. 
			// There's no guarantee that this will continue to work in future versions, it's pretty hacky.
			Kubii.instance.getServer().getScheduler().runTask(Kubii.instance, () -> e.getInventory().setRepairCost(39));
		}
	}
}
