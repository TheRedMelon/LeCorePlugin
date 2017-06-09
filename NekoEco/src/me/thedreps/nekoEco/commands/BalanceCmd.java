package me.thedreps.nekoEco.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.thedreps.nekoEco.API;

public class BalanceCmd implements CommandExecutor{
	
	API api = new API();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String prefix = ChatColor.DARK_PURPLE + "Eco" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		//String noPerms = prefix + "Sorry you do not have the required permissions";
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(prefix + "This command is for players only!");
			return true;
		}
		
		Player player = (Player) sender;
		
		player.sendMessage(prefix + "$" + api.getBal(player.getUniqueId().toString()));
		
		
		return false;
	}

}
