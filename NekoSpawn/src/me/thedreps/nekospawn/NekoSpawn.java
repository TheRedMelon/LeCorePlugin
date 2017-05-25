package me.thedreps.nekospawn;

import org.bukkit.plugin.java.JavaPlugin;

import me.thedreps.nekospawn.commands.SetSpawnCmd;

public class NekoSpawn extends JavaPlugin{
	
	public static NekoSpawn plugin;
	
	public void onEnable(){
		registerCommands();
		loadConfiguration();
		
		plugin = this;
	}
	
	private void registerCommands(){
		getCommand("setspawn").setExecutor(new SetSpawnCmd());
	}
	
	public void loadConfiguration(){
	     getConfig().options().copyDefaults(true);
	     saveConfig();
	}

}
