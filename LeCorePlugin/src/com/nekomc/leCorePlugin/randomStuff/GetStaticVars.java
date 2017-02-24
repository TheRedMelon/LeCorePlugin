package com.nekomc.leCorePlugin.randomStuff;

import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.World;

import com.nekomc.leCorePlugin.LeCorePlugin;

public class GetStaticVars {

	public void fromConfig() {
		
		try {
			
			Set<String> keys = LeCorePlugin.plugin.getConfig().getConfigurationSection("world-alias").getKeys(false);
			Map<String, Object> valueMap = LeCorePlugin.plugin.getConfig().getConfigurationSection("world-alias").getValues(false);
			
			for (World w : Bukkit.getServer().getWorlds()) {
				
				if (keys.contains(w.getName())) {
					
					LeCorePlugin.plugin.worldAlias.put(w.getName(), (String) valueMap.get(w.getName()));
					
				} else {
					
					LeCorePlugin.plugin.worldAlias.put(w.getName(), Bukkit.getServer().getServerName());
					
				}
				
			}
			
		} catch (NullPointerException e) {
			
			for (World w : Bukkit.getServer().getWorlds()) {
				
				LeCorePlugin.plugin.worldAlias.put(w.getName(), Bukkit.getServer().getName());
				
			}
		
		}
		
	}
	
}
