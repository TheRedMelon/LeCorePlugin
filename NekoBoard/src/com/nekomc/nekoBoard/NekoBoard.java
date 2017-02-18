package com.nekomc.nekoBoard;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nekomc.nekoBoard.event.custom.GeneralScoreboardUpdate;
import com.nekomc.nekoBoard.event.player.PlayerJoin;

public class NekoBoard extends JavaPlugin {

	public static NekoBoard plugin;
	
	PluginManager pm = getServer().getPluginManager();
	
	public void onEnable() {
		
		plugin = this;
		
		registerEvents();
		
	}
	
	private void registerEvents() {
		
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new GeneralScoreboardUpdate(), this);
		
	}
	
}
