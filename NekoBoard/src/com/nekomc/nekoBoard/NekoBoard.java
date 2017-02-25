package com.nekomc.nekoBoard;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.nekomc.nekoBoard.boards.Hub;
import com.nekomc.nekoBoard.commands.Main;
import com.nekomc.nekoBoard.event.custom.PlayerBoardUpdate;
import com.nekomc.nekoBoard.event.player.PlayerJoin;

import net.milkbowl.vault.economy.Economy;

public class NekoBoard extends JavaPlugin {

	public static NekoBoard plugin;
	
	public static Economy economy = null;
	
	@SuppressWarnings("rawtypes")
	public HashMap<String, Class> worldBoards = new HashMap<String, Class>();
	
	PluginManager pm = getServer().getPluginManager();
	
	public void onEnable() {
		
		plugin = this;
		
		registerConfig();
		registerEvents();
		registerCommands();
		
		setupEconomy();
		
	}
	
	private void registerCommands() {
		
		getCommand("nb").setExecutor(new Main());
		
	}
	
	private void registerConfig() {
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		try {
			
			Set<String> worlds = getConfig().getConfigurationSection("world-boards").getKeys(false);
			Map<String, Object> boards = getConfig().getConfigurationSection("world-boards").getValues(false);
		
			for (World w : Bukkit.getWorlds()) {
			
				if (worlds.contains(w.getName())) {
						
					try {
						
						worldBoards.put(w.getName(), Class.forName("com.nekomc.nekoBoard.boards." + (String) boards.get(w.getName())));
						
					} catch (ClassNotFoundException e) {
						
						e.printStackTrace();
						
					}
					
				} else {
					
					worldBoards.put(w.getName(), new Hub().getClass());
					
				}
			
			}
			
		} catch (NullPointerException e) {
			
			for (World world : Bukkit.getServer().getWorlds()) {
				
				worldBoards.put(world.getName(), new Hub().getClass());
				
			}
		
		}
			
	}
	
	private void registerEvents() {
		
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerBoardUpdate(), this);
		
	}
	
	private boolean setupEconomy() {
		
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		
		if (economyProvider != null) {
			
			economy = economyProvider.getProvider();
			
		}
		
		return (economyProvider != null);
		
	}
	
}
