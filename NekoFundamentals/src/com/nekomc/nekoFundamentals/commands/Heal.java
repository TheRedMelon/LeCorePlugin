package com.nekomc.nekoFundamentals.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		String prefix = ChatColor.DARK_PURPLE + "Death" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		String noPerms = prefix + "Sorry you do not have the required permissions";
		String usage = prefix + "Usage: /heal [player]";
		
		if (!sender.hasPermission("nf.heal")) {
			
			sender.sendMessage(noPerms);
			return false;
			
		}
		
		if (args.length > 1) {
			
			sender.sendMessage(usage);
			return false;
			
		}
		
		if (args.length < 1) {
				
			if (!(sender instanceof Player)) {
				
				sender.sendMessage(prefix + "Please run the command as a player if you do not use any arguments!");
				return false;
				
			} else {
				
				Player p = (Player) sender;
				
				p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
				p.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You magically healed yourself!");
				
			}
			
		} else {
			
			if (!sender.hasPermission("nf.heal.others")) {
				
				sender.sendMessage(noPerms);
				return false;
				
			}
			
			Player p = Bukkit.getPlayer(args[0]);
			
			if (p == null) {
				
				sender.sendMessage(prefix + "Player: " + p + " was not found!");
				return false;
				
			} else {
				
				p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
				p.sendMessage(prefix + ChatColor.LIGHT_PURPLE + sender.getName() + ChatColor.RESET + ChatColor.LIGHT_PURPLE + "magically healed you!");
				sender.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You magically healed " + p.getName() + ChatColor.RESET + ChatColor.LIGHT_PURPLE + "!");
				
			}
			
		}
		
		return true;
		
	}

}
