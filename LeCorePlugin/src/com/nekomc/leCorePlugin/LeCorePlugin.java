package com.nekomc.leCorePlugin;

import java.util.HashMap;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nekomc.leCorePlugin.event.player.PlayerJoin;
import com.nekomc.leCorePlugin.event.player.PlayerQuit;
import com.nekomc.leCorePlugin.randomStuff.GetStaticVars;

public class LeCorePlugin extends JavaPlugin {
	
	public static LeCorePlugin plugin;
	
	public int playerCount = 0;
	public int maxPlayers;
	
	public HashMap<String, String> worldAlias = new HashMap<String, String>();
	
	PluginManager pm = getServer().getPluginManager();
	
	public void onEnable() {
		
		plugin = this;
		
		maxPlayers = getServer().getMaxPlayers();
		
		registerEvents();
		registerConfig();
		
		new GetStaticVars().fromConfig();
		
	}
	
	private void registerEvents() {
		
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerQuit(), this);
		
	}
	
	private void registerConfig() {
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
	}
	
}
