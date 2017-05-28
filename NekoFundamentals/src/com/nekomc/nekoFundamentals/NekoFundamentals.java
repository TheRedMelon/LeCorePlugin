package com.nekomc.nekoFundamentals;

import org.bukkit.plugin.java.JavaPlugin;

import com.nekomc.nekoFundamentals.commands.Tree;

public class NekoFundamentals extends JavaPlugin{

	public void onEnable() {
		
		registerCommands();
		
	}
	
	private void registerCommands() {
		
		getCommand("tree").setExecutor(new Tree());
		
	}
	
}
