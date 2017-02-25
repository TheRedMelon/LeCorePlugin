package com.nekomc.nekoBoard.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import com.nekomc.leCorePlugin.customEvents.PlayerBoardUpdateEvent;
import com.nekomc.leCorePlugin.randomStuff.BoardSection;

public class Update implements CommandExecutor {

	PluginManager pm = Bukkit.getPluginManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		if (args.length < 1) {
			
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				
				for (BoardSection section : BoardSection.values()) {
					
					pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), section));
					
				}
				
			}
			
		} else if (args.length == 1) {
			
			if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayerExact(args[0]))) {
				
				Player p = Bukkit.getPlayerExact(args[0]);
				
				for (BoardSection section : BoardSection.values()) {
				
					pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), section));
				
				}
					
			} else {
			
				try {
					
					BoardSection section = BoardSection.valueOf(args[0].toUpperCase());
					
					for (Player p : Bukkit.getServer().getOnlinePlayers()) {
						
						pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), section));
						
					}
					
				} catch (IllegalArgumentException e) {
					
					sender.sendMessage(ChatColor.DARK_RED + "No user or board section " + args[0]);
					return false;
					
				}
				
			}
			
		} else if (args.length == 2) {
			
			if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayerExact(args[0]))) {
				
				Player p = Bukkit.getPlayerExact(args[0]);
				
				try {
					
					pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), BoardSection.valueOf(args[1].toUpperCase())));
				
				} catch (IllegalArgumentException e) {
					
					sender.sendMessage(ChatColor.DARK_RED + "No board section " + args[1]);
					return false;
					
				}
					
			} else {
				
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					
					for (String str : args) {
						
						try {
							
							pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), BoardSection.valueOf(str.toUpperCase())));
							
						} catch (IllegalArgumentException e) {
							
							sender.sendMessage(ChatColor.DARK_RED + "No player name " + args[0] + " and no board section " + args.toString());
							return false;
							
						}
						
					}
					
				}
				
			}
			
		} else {
			
			if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayerExact(args[0]))) {
				
				Player p = Bukkit.getPlayerExact(args[0]);
				List<String> argsL = new ArrayList<String>(Arrays.asList(args));
				
				argsL.remove(0);
				
				for (String str : argsL) {
					
					try {
					
						pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), BoardSection.valueOf(str.toUpperCase())));
					
					} catch (IllegalArgumentException e) {
						
						sender.sendMessage(ChatColor.DARK_RED + "No board section " + str);
						return false;
						
					}
						
				}
				
			} else {
				
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					
					for (String str : args) {
						
						try {
							
							pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), BoardSection.valueOf(str)));
							
						} catch (IllegalArgumentException e) {
							
							sender.sendMessage(ChatColor.DARK_RED + "No player " + args[0] + " and no section " + str);
							
						}
						
					}
					
				}
				
			}
			
		}
		
		return true;
	}

}
