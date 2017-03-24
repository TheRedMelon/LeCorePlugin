package com.nekomc.nekoBoard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.nekomc.leCorePlugin.customEvents.ScoreboardTeamsUpdateEvent;
import com.nekomc.nekoBoard.boards.Hub;
import com.nekomc.nekoBoard.commands.Main;
import com.nekomc.nekoBoard.event.custom.PlayerBoardUpdate;
import com.nekomc.nekoBoard.event.custom.ScoreboardTeamUpdate;
import com.nekomc.nekoBoard.event.player.PlayerJoin;

import net.milkbowl.vault.economy.Economy;

public class NekoBoard extends JavaPlugin {

	public static NekoBoard plugin;

	public static Economy economy = null;
	
	@SuppressWarnings("rawtypes")
	public HashMap<String, Class> worldBoards = new HashMap<String, Class>();
	private HashMap<Team, Set<String>> teamPlayers = new HashMap<Team, Set<String>>();
	
	PluginManager pm = getServer().getPluginManager();
	ScoreboardManager sbm = getServer().getScoreboardManager();
	
	public void onEnable() {
		
		plugin = this;
		
		registerConfig();
		registerEvents();
		registerCommands();
		
		setupEconomy();
		
		teamChanges();
		
	}
	
	private void teamChanges() {
	
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				
				Bukkit.getLogger().info(sbm.getMainScoreboard().getTeams().toString());
				
				for (Team t : sbm.getMainScoreboard().getTeams()) {
					
					Bukkit.getLogger().info("1) Loop");
					Bukkit.getLogger().info(teamPlayers.get(t).toString());
					
					if (!teamPlayers.get(t).equals(t.getEntries())) {
						
						Bukkit.getLogger().info("2) If");
						
						List<Object> savedPlayers = Arrays.asList(teamPlayers.get(t).toArray().clone());
						List<Object> currentPlayers = Arrays.asList(t.getEntries().toArray().clone());
						
						Bukkit.getLogger().info(savedPlayers.toString());
						Bukkit.getLogger().info(currentPlayers.toString());
						
						if (savedPlayers.size() < currentPlayers.size()) {
							
							Bukkit.getLogger().info("3) If #2");
							
							savedPlayers.removeAll(currentPlayers);
							
							Bukkit.getLogger().info(savedPlayers.toString());
							
							pm.callEvent(new ScoreboardTeamsUpdateEvent(t, (String) savedPlayers.get(0)));
							
							teamPlayers.put(t, t.getEntries());
							
							Bukkit.getLogger().info(teamPlayers.get(t).toString());
							break;
							
						}
						
					}
					
				}
				
			}
			
		}, 0L, 1L);
		
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
		pm.registerEvents(new ScoreboardTeamUpdate(), this);
		
	}
	
	private boolean setupEconomy() {
		
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		
		if (economyProvider != null) {
			
			economy = economyProvider.getProvider();
			
		}
		
		return (economyProvider != null);
		
	}
	
}
