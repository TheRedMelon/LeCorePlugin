package com.nekomc.leCorePlugin;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nekomc.leCorePlugin.event.player.PlayerJoin;

public class LeCorePlugin extends JavaPlugin {
	
	public static LeCorePlugin plugin;
	
	public int playerCount;
	public int maxPlayers;
	
	PluginManager pm = getServer().getPluginManager();
	
	public void onEnable() {
		
		plugin = this;
		
		playerCount = getServer().getOnlinePlayers().size();
		maxPlayers = getServer().getMaxPlayers();
		
		registerEvents();
		registerConfig();
		
	}
	
	private void registerEvents() {
		
		pm.registerEvents(new PlayerJoin(), this);
		
	}
	
	private void registerConfig() {
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		Set<String> cfs = getConfig().getConfigurationSection("world-alias").getKeys(false);
		
		Bukkit.getLogger().info(cfs.toString());
		
	}
	
}
