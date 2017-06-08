package com.nekomc.nekoFundamentals.commands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Suicide implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		String prefix = ChatColor.DARK_PURPLE + "Death" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		String noPerms = prefix + "Sorry you do not have the required permissions";
		
		if (!sender.hasPermission("nf.suicide")) {
			
			sender.sendMessage(noPerms);
			return false;
			
		}
		
		if (!(sender instanceof Player)) {
			
			sender.sendMessage(prefix + "Please run this command as a " + ChatColor.DARK_PURPLE + "PLAYER" + ChatColor.RESET + "!");
			return false;
			
		}
		
		Player p = (Player) sender;
		
		p.setHealth(0D);
		p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_DEATH, 10F, 1F);
		p.sendMessage(prefix + "Great sadness drove you to suicide");
		
		return true;
		
	}

}
