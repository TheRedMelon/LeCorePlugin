package com.nekomc.nekoFundamentals.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		String prefix = ChatColor.DARK_PURPLE + "Cheat" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		String noPerms = prefix + "Sorry you do not have the required permissions.";
		String usage = prefix + "Usage: /heal [player]";
		
		if (!sender.hasPermission("nf.heal")) {
			
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
				
				p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
				p.setFoodLevel(20);
				p.sendMessage(prefix + "You magically healed yourself!");
				
			}
			
		} else {
			
			if (!sender.hasPermission("nf.heal.others")) {
				
				sender.sendMessage(noPerms);
				return true;
				
			}
			
			Player p = Bukkit.getPlayer(args[0]);
			
			if (p == null) {
				
				sender.sendMessage(prefix + "Player: " + p + " was not found!");
				return true;
				
			} else {
				
				p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
				p.setFoodLevel(20);
				p.sendMessage(prefix + sender.getName() + ChatColor.RESET + " magically healed you!");
				sender.sendMessage(prefix + "You magically healed " + p.getName() + ChatColor.RESET + "!");
				
			}
			
		}
		
		return true;
		
	}

}
