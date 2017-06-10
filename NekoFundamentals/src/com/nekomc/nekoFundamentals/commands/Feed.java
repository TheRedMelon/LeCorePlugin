package com.nekomc.nekoFundamentals.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
				
		String prefix = ChatColor.DARK_PURPLE + "Cheat" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		String noPerms = prefix + "Sorry you do not have the required permissions.";
		String usage = prefix + "Usage: /feed [player]";
		
		if (!sender.hasPermission("nf.feed")) {
			
			sender.sendMessage(noPerms);
			return true;
			
		}
		
		if (args.length > 1) {
			
			sender.sendMessage(usage);
			return true;
			
		}
		
		if (args.length < 1) {
				
			if (!(sender instanceof Player)) {
				
				sender.sendMessage(prefix + "Please run the command as a player if you do not use any arguments!");
				return true;
				
			} else {
				
				Player p = (Player) sender;
				
				p.setFoodLevel(20);
				p.sendMessage(prefix + "You magically fed yourself!");
				
			}
			
		} else {
			
			if (!sender.hasPermission("nf.feed.others")) {
				
				sender.sendMessage(noPerms);
				return true;
				
			}
			
			Player p = Bukkit.getPlayer(args[0]);
			
			if (p == null) {
				
				sender.sendMessage(prefix + "Player: " + p + " was not found!");
				return true;
				
			} else {
				
				p.setFoodLevel(20);
				p.sendMessage(prefix + sender.getName() + ChatColor.RESET + " magically fed you!");
				sender.sendMessage(prefix + "You magically fed " + p.getName() + ChatColor.RESET + "!");
				
			}
			
		}
		
		return true;
	
	}

}
