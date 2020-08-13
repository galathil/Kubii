package fr.galathil.kubii.listeners.spigot;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import fr.galathil.kubii.KubiiConf;

public class EntitiesListener implements Listener {

	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e) {
        if (e.getEntity().getType() == EntityType.CREEPER) {
            if (KubiiConf.LIST_WORLDS_NO_GRIEF_CREEPER.contains(e.getEntity().getWorld().getName())) {
            	e.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, e.getEntity().getLocation(), 0);
            	e.getEntity().getWorld().playSound(e.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 0);
            	e.setCancelled(true);
            }
        } else if(e.getEntity().getType() == EntityType.FIREBALL) {
        	if (KubiiConf.LIST_WORLDS_NO_GRIEF_FIREBALL.contains(e.getEntity().getWorld().getName())) {
            	e.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, e.getEntity().getLocation(), 0);
            	e.getEntity().getWorld().playSound(e.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 0);
            	e.setCancelled(true);
        	}
        }
	}
	
	@EventHandler
    public void onEntityChangeBlock(final EntityChangeBlockEvent e) {
        if (e.getEntity().getType() == EntityType.ENDERMAN) {
        	if (KubiiConf.LIST_WORLDS_NO_GRIEF_ENDERMAN.contains(e.getEntity().getWorld().getName())) {
        		e.setCancelled(true);
        		return;
        	}
        }
	}
	
	@EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {
		if(e.getEntity().getType() == EntityType.SLIME) {
			if(KubiiConf.LIST_WORLDS_NO_SPAWN_SLIME.contains(e.getEntity().getWorld().getName())) {
				e.setCancelled(true);
				return;
			}
		} else if(e.getEntity().getType()==EntityType.WITHER) {
			if(KubiiConf.LIST_WORLDS_NO_SPAWN_WITHER.contains(e.getEntity().getWorld().getName())) {
				e.setCancelled(true);
				return;
			}
		} else if(e.getEntity().getType()==EntityType.GHAST) {
			if(KubiiConf.LIST_WORLDS_NO_SPAWN_GHAST.contains(e.getEntity().getWorld().getName())) {
				e.setCancelled(true);
				return;
			}
		} else if(e.getEntity().getType()==EntityType.ZOMBIFIED_PIGLIN) {
			if(KubiiConf.LIST_WORLDS_NO_SPAWN_ZOMBIFIED_PIGLIN.contains(e.getEntity().getWorld().getName())) {
				//Kubii.instance.getLogger().info("onCreatureSpawn() - cancel zombified piglin spawn on "+e.getEntity().getWorld().getName());
				e.setCancelled(true);
				return;
			}
		}
		// Kubii.instance.getLogger().info("onCreatureSpawn() "+e.getEntity().getWorld().getName()+" : "+e.getEntity().getName());
	}
}
