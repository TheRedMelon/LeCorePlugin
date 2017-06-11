package com.nekomc.nekoFundamentals;

import org.bukkit.plugin.java.JavaPlugin;

import com.nekomc.nekoFundamentals.commands.FeedCmd;
import com.nekomc.nekoFundamentals.commands.FlyCmd;
import com.nekomc.nekoFundamentals.commands.GamemodeCmd;
import com.nekomc.nekoFundamentals.commands.HatCmd;
import com.nekomc.nekoFundamentals.commands.HealCmd;
import com.nekomc.nekoFundamentals.commands.KillCmd;
import com.nekomc.nekoFundamentals.commands.SuicideCmd;

public class NekoFundamentals extends JavaPlugin{
	
	public void onEnable() {
		
		registerCommands();
		
	}
	
	private void registerCommands() {
		
		getCommand("gamemode").setExecutor(new GamemodeCmd());
		getCommand("suicide").setExecutor(new SuicideCmd());
		getCommand("kill").setExecutor(new KillCmd());
		getCommand("heal").setExecutor(new HealCmd());
		getCommand("fly").setExecutor(new FlyCmd());
		getCommand("feed").setExecutor(new FeedCmd());
		getCommand("hat").setExecutor(new HatCmd());
		
	}
	
}
