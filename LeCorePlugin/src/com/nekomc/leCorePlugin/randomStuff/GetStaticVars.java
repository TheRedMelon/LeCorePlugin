package com.nekomc.leCorePlugin.randomStuff;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;

import com.nekomc.leCorePlugin.LeCorePlugin;

public class GetStaticVars {

	public void fromConfig() {
		
		List<?> nameAlias = LeCorePlugin.plugin.getConfig().getList("server-name-alias");
		
		if (!nameAlias.isEmpty()) {
			
			for (World world : Bukkit.getServer().getWorlds()) {
				
				if (nameAlias.contains(world.getName())) {
					
					String name = LeCorePlugin.plugin.getConfig().getString("server-name-alias." + world.getName());
					
					LeCorePlugin.plugin.worldAlias.put(world.getName(), name);
					
				}
				
			}
			
		}
		
	}
	
}
