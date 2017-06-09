package com.nekomc.nekoFundamentals.commands;

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
			return false;
			
		}
		
		if (args.length < 1) {
			
			if (!(sender instanceof Player)) {
				
				sender.sendMessage(prefix + "Please run the command as a player if you do not use any arguments!");
				return false;
				
			}
			
			
			
		}
		
		return true;
		
	}

}
