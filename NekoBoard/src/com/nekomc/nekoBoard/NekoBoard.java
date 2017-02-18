package com.nekomc.nekoBoard;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NekoBoard extends JavaPlugin {

	public static NekoBoard plugin;
	
	PluginManager pm = getServer().getPluginManager();
	
	public void onEnable() {
		
		plugin = this;
		
	}
	
}
