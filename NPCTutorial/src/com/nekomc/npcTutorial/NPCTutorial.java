package com.nekomc.npcTutorial;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nekomc.npcTutorial.commands.Main;

public class NPCTutorial extends JavaPlugin {

	public static NPCTutorial plugin;
	
	PluginManager pm = getServer().getPluginManager();
	
	public HashMap<String, YamlConfiguration> configFiles = new HashMap<String, YamlConfiguration>();
	public HashMap<String, List<Object>> tutSetup = new HashMap<String, List<Object>>();
	public List<String> tuts = new ArrayList<String>();
	
	public void onEnable() {
		
		plugin = this;
		
		registerConfig();
		
		getCommand("npctutorial").setExecutor(new Main());
		
	}
	
	private void registerConfig() {
		
		File configFolder = new File(getDataFolder() + File.separator + "tutorials");
		
		if (!configFolder.exists()) {
			
			configFolder.mkdir();
			
		}
		
		for (File file : configFolder.listFiles()) {
			
			configFiles.put(file.getName(), YamlConfiguration.loadConfiguration(file));
			tuts.add(file.getName());
			
		}
		
	}
	
}
