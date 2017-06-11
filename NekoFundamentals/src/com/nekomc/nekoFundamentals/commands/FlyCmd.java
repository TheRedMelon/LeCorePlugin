package com.nekomc.nekoFundamentals.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		String prefix = ChatColor.DARK_PURPLE + "Cheats" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		String noPerms = prefix + "Sorry you do not have the required permissions";
		String usage = prefix + "Usage: /fly [player] [enable/disable]";
		
		if (!sender.hasPermission("nf.fly")) {
			
			sender.sendMessage(noPerms);
			return true;
			
		}
		
		if (args.length < 1) {
			
			if (!(sender instanceof Player)) {
				
				sender.sendMessage(prefix + "Please run the command as a player if you do not use any arguments!");
				
			}
			
			Player p = (Player) sender;
			
			if (p.getAllowFlight()) {
				
				p.setAllowFlight(false);
				p.sendMessage(prefix + "You're falling through the air...  You're plummeting through the moonlit sky....");
				
			} else {
			
				p.setAllowFlight(true);
				p.sendMessage(prefix + "You're walking in the air...  You're floating in the moonlit sky....");
			
			}
			
		} else if (args.length == 1) {
			
			Player p = Bukkit.getPlayer(args[0]);
			
			if (p != null) {
				
				if (!sender.hasPermission("nf.fly.others")) {
					
					sender.sendMessage(noPerms);
					return true;
					
				}
				
				if (p.getAllowFlight()) {
					
					p.setAllowFlight(false);
					p.sendMessage(prefix + "Your gift of flight was revoked by " + sender.getName() + ChatColor.RESET + "!");
					sender.sendMessage(prefix + "You revoked the gift of flight from " + p.getName() + ChatColor.RESET + "!");
					
				} else {
				
					p.setAllowFlight(false);
					p.sendMessage(prefix + "You were given the gift of flight by " + sender.getName() + ChatColor.RESET + "!");
					sender.sendMessage(prefix + "You gave the gift of flight to " + p.getName() + ChatColor.RESET + "!");
			
				}
					
			} else {
				
				if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("true") || args[0].equalsIgnoreCase("yes")) {
					
					if (!(sender instanceof Player)) {
						
						sender.sendMessage(prefix + "Please run the command as a player if you are using enable or disable as your first argument!");
						return false;
						
					}
					
					p = (Player) sender;
					
					p.setAllowFlight(true);
					p.sendMessage(prefix + "You're walking in the air...  You're floating in the moonlit sky....");
					
				} else if (args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("false") || args[0].equalsIgnoreCase("no")) {
					
					if (!(sender instanceof Player)) {
						
						sender.sendMessage(prefix + "Please run the command as a player if you are using enable or disable as your first argument!");
						return false;
						
					}
					
					p = (Player) sender;
					
					p.setAllowFlight(false);
					p.sendMessage(prefix + "You're falling through the air...  You're plummeting through the moonlit sky....");
					
				} else {
				
					if (!sender.hasPermission("nf.fly.others")) {
						
						sender.sendMessage(noPerms);
						return true;
						
					}
					
					sender.sendMessage(prefix + "Player: " + args[0] + " was not found!");
					
				}
				
			}
			
		} else if (args.length == 2) {
			
			if (!sender.hasPermission("nf.fly.others")) {
				
				sender.sendMessage(noPerms);
				return true;
				
			}
			
			Player p = Bukkit.getPlayer(args[0]);
			
			if (p != null) {
				
				if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("enable") || args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("yes")) {
					
					p.sendMessage(prefix + "You were given the gift of flight by " + sender.getName() + ChatColor.RESET + "!");
					sender.sendMessage(prefix + "You gave the gift of flight to " + p.getName() + ChatColor.RESET + "!");
					p.setAllowFlight(true);
					
				} else if (args[1].equalsIgnoreCase("off") || args[1].equalsIgnoreCase("disable") || args[1].equalsIgnoreCase("false") || args[1].equalsIgnoreCase("no")) {
					
					p.setAllowFlight(false);
					p.sendMessage(prefix + "Your gift of flight was revoked by " + sender.getName() + ChatColor.RESET + "!");
					sender.sendMessage(prefix + "You revoked the gift of flight from " + p.getName() + ChatColor.RESET + "!");
					
				} else {
					
					sender.sendMessage(usage);
					return true;
					
				}
					
			} else {
					
				sender.sendMessage(prefix + "Player: " + args[0] + " was not found!");
				
			}
			
		} else {
			
			sender.sendMessage(usage);
			
		}
		
		return true;
		
	}

}
