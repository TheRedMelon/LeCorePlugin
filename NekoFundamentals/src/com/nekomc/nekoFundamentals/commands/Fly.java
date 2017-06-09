package com.nekomc.nekoFundamentals.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		String prefix = ChatColor.DARK_PURPLE + "Death" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		String noPerms = prefix + "Sorry you do not have the required permissions";
		String usage = prefix + "Usage: /fly [player]";
		
		if (!sender.hasPermission("nf.fly")) {
			
			sender.sendMessage(noPerms);
			return true;
			
		}
		
		if (args.length < 1) {
			
			if (!(sender instanceof Player)) {
				
				sender.sendMessage(prefix + "Please run the command as a player if you do not use any arguments!");
				
			}
			
			Player p = (Player) sender;
			
			if (p.isFlying()) {
				
				p.setAllowFlight(false);
				p.sendMessage(prefix + "You're falling through the air...  You're plummeting through the moonlit sky....");
				
			} else {
			
				p.setAllowFlight(true);
				p.sendMessage(prefix + "You're walking in the air...  You're floating in the moonlit sky....");
			
			}
			
		} else if (args.length == 1) {
			
			if (!sender.hasPermission("nf.fly.others")) {
				
				sender.sendMessage(noPerms);
				return true;
				
			}
			
			Player p = Bukkit.getPlayer(args[0]);
			
			if (p != null) {
			
				if (p.isFlying()) {
					
					p.setAllowFlight(false);
					p.sendMessage(prefix + "Your gift of flight was revoked by " + sender.getName() + ChatColor.RESET + "!");
					sender.sendMessage(prefix + "You revoked the gift of flight from " + p.getName() + ChatColor.RESET + "!");
					
				} else {
				
					p.setAllowFlight(false);
					p.sendMessage(prefix + "You were given the gift of flight by " + sender.getName() + ChatColor.RESET + "!");
					sender.sendMessage(prefix + "You gave the gift of flight to " + p.getName() + ChatColor.RESET + "!");
			
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
