package com.nekomc.nekoFundamentals.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tree implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		if (!(sender instanceof Player)) {
			
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "Please run this commands as a " + ChatColor.DARK_PURPLE + "PLAYER" + ChatColor.LIGHT_PURPLE + "!");
			return false;
			
		}
		
		if (args.length > 2) {
			
			
			
		}
		
		if (args.length == 1) {
			
			
			
		}
		
		Player p = (Player) sender;
		Location loc = p.getEyeLocation();
		
		
		
		return true;
		
	}

}
