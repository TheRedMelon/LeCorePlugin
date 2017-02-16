package com.nekomc.leCorePlugin;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nekomc.leCorePlugin.event.player.PlayerJoin;

public class LeCorePlugin extends JavaPlugin {
	
	public static LeCorePlugin plugin;
	
	public int playerCount;
	public static int maxPlayers;
	
	PluginManager pm = getServer().getPluginManager();
	
	public void onEnable() {
		
		plugin = this;
		
		playerCount = getServer().getOnlinePlayers().size();
		maxPlayers = getServer().getMaxPlayers();
		
		registerEvents();
		
	}
	
	private void registerEvents() {
		
		pm.registerEvents(new PlayerJoin(), this);
		
	}
	
}
