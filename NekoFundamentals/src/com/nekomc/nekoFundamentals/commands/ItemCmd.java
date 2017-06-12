package com.nekomc.nekoFundamentals.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		String prefix = ChatColor.DARK_PURPLE + "Cosmetics" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		String noPerms = prefix + "Sorry you do not have the required permissions.";
		String usage = prefix + "Usage: /item <item name/id>.... [amount] [enchantment]";
		
		if (!sender.hasPermission("nf.item")) {
			
			sender.sendMessage(noPerms);
			return true;
			
		}
		
		if (!(sender instanceof Player)) {
			
			sender.sendMessage(prefix + "Please run this command as a PLAYER!");
			return true;
			
		}
		
		if (args.length < 1) {
			
			sender.sendMessage(usage);
			return true;
			
		}
		
		if (args[0].contains(":") || args[0].contains("|") || args[0].contains(",") || args[0].contains(";")) {
			
			int arg2i = args[0].indexOf("|");
			arg2i = args[0].indexOf(",");
			arg2i = args[0].indexOf(";");
			arg2i = args[0].indexOf(":");
			String arg2 = args[0].substring(arg2i + 1);
			
			
		}
		
		return true;
		
	}

}
