package com.nekomc.nekoFundamentals.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tree implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		String prefix = ChatColor.DARK_PURPLE + "Cheat" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		String noPerms = prefix + ChatColor.LIGHT_PURPLE + "Sorry you do not have the required permissions";
		String usage = prefix + ChatColor.LIGHT_PURPLE + "Usage: /tree [tree type] [true]";
		
		if (!sender.hasPermission("nf.tree")) {
			
			sender.sendMessage(noPerms);
			return false;
			
		}
		
		if (!(sender instanceof Player)) {
			
			sender.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Please run this commands as a " + ChatColor.DARK_PURPLE + "PLAYER" + ChatColor.LIGHT_PURPLE + "!");
			return false;
			
		}
		
		if (args.length <= 2) {
			
			Player p = (Player) sender;
			Location loc = p.getEyeLocation();
			TreeType tt = TreeType.TREE;
			
			if (args.length == 1) {
				
				if (args[0].equalsIgnoreCase("true")) {
					
					loc.getWorld().generateTree(loc, tt);
					
				} else {
					
					if (!loc.getBlock().getType().equals(Material.DIRT) || !loc.getBlock().getType().equals(Material.GRASS)) {
						
						p.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "A tree can't be placed here!  Try on grass or dirt or use: "  + ChatColor.DARK_PURPLE + "/tree [tree type] true");
						return false;
						
					}
					
					try {
						
						tt = TreeType.valueOf(args[0]);
					
					} catch (IllegalArgumentException e) {
						
						p.sendMessage(usage);
						return false;
						
					}
					
					loc.getWorld().generateTree(loc, tt);
							
				}
				
			} else {
				
				if (args.length == 2) {
				
					if (!args[1].equalsIgnoreCase("true")) {
						
						p.sendMessage(usage);
						return false;
						
					}
					
					try {
						
						tt = TreeType.valueOf(args[0]);
					
					} catch (IllegalArgumentException e) {
						
						p.sendMessage(usage);
						return false;
						
					}
					
					loc.getWorld().generateTree(loc, tt);
				
				} else {
					
					if (!loc.getBlock().getType().equals(Material.DIRT) || !loc.getBlock().getType().equals(Material.GRASS)) {
						
						p.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "A tree can't be placed here!  Try on grass or dirt or use: "  + ChatColor.DARK_PURPLE + "/tree [tree type] true");
						return false;
						
					}
					
					loc.getWorld().generateTree(loc, tt);
					
				}
				
			}
		
		}
		
		return true;
		
	}

}
