package com.nekomc.npcTutorial;

import java.io.File;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NPCTutorial extends JavaPlugin {

	PluginManager pm = getServer().getPluginManager();
	
	HashMap<String, YamlConfiguration> configFiles = new HashMap<String, YamlConfiguration>();
	HashMap<UUID, >
	
	public void onEnable() {
		
		registerConfig();
		
	}
	
	private void registerConfig() {
		
		File configFolder = new File(getDataFolder() + File.separator + "tutorials");
		
		if (!configFolder.exists()) {
			
			configFolder.mkdir();
			
		}
		
		for (File file : configFolder.listFiles()) {
			
			configFiles.put(file.getName(), YamlConfiguration.loadConfiguration(file));
			
		}
		
	}
	
}
