package me.thedreps.nekospawn;

import org.bukkit.plugin.java.JavaPlugin;

import me.thedreps.nekospawn.commands.SetSpawnCmd;
import me.thedreps.nekospawn.commands.SpawnCmd;

public class NekoSpawn extends JavaPlugin{
	
	public static NekoSpawn plugin;
	
	public void onEnable(){
		registerCommands();
		loadConfiguration();
		
		plugin = this;
	}
	
	private void registerCommands(){
		getCommand("setspawn").setExecutor(new SetSpawnCmd());
		getCommand("spawn").setExecutor(new SpawnCmd());
	}
	
	public void loadConfiguration(){
	     getConfig().options().copyDefaults(true);
	     saveConfig();
	}

}
