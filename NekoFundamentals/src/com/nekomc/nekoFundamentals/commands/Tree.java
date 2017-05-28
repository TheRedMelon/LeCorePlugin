package com.nekomc.nekoFundamentals.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tree implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
//		String prefix = ChatColor.DARK_PURPLE + "Cheat" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
//		String noPerms = prefix + "Sorry you do not have the required permissions";
//		String usage = prefix + "Usage: /gamemode <gamemode> [player]";
		
		
		if (!(sender instanceof Player)) {
			
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "Please run this commands as a " + ChatColor.DARK_PURPLE + "PLAYER" + ChatColor.LIGHT_PURPLE + "!");
			return true;
			
		}
		
		if (args.length <= 1) {
			
			Player p = (Player) sender;
			Location loc = p.getEyeLocation();
//			TreeType tt = TreeType.TREE;
			
			if (args.length == 1) {
				
				if (args[0].equalsIgnoreCase("true")) {
					
					
					
				} else {
				
					if (!(loc.getBlock().getType().equals(Material.DIRT)) && !(loc.getBlock().getType().equals(Material.GRASS))) {
						
						p.sendMessage(ChatColor.LIGHT_PURPLE + "Tree cannot be placed here, or use: " + ChatColor.DARK_PURPLE + "/tree true");
						return true;
						
					}
					
					try {
						
//						tt = TreeType.valueOf(args[0]);
					
					} catch (IllegalArgumentException e) {
						
						return false;
						
					}
							
				}
				
			}
		
		}
		
		return true;
		
	}

}
