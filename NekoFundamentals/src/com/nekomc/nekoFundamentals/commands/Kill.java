package com.nekomc.nekoFundamentals.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kill implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		String prefix = ChatColor.DARK_PURPLE + "Death" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		String noPerms = prefix + "Sorry you do not have the required permissions.";
		String usage = prefix + "Usage: /kill [player]";
		
		if (!sender.hasPermission("nf.kill")) {
		
			sender.sendMessage(noPerms);
			return true;
			
		}
		
		if (args.length > 1) {
			
			sender.sendMessage(usage);
			return true;
			
		} else if (args.length == 0) {
			
			if (!(sender instanceof Player)) {
				
				sender.sendMessage(prefix + "Please run the command as a player if you do not use any arguments!");
				return true;
				
			} else {
				
				Player p = (Player) sender;
				
				p.setHealth(0D);
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_DEATH, 10F, 1F);
				p.sendMessage(prefix + "Great sadness drove you to suicide");
				
			}
			
		} else {
			
			if (!sender.hasPermission("nf.kill.others")) {
				
				sender.sendMessage(noPerms);
				return true;
				
			} else {
				
				Player p = Bukkit.getPlayer(args[0]);
				
				if (p != null) {
				
					p.setHealth(0D);
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_DEATH, 10F, 1F);
					p.sendMessage(prefix + "You were killed by " + sender.getName());
					sender.sendMessage(prefix + "You killed " + p.getName() + ChatColor.RESET + "!");
				
				} else {
					
					sender.sendMessage(prefix + "Player: " + args[0] + " was not found!");
					
				}
				
			}
			
		}
		
		return true;
	
	}

}
