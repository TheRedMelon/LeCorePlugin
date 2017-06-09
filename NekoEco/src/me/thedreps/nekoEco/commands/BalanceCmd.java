package me.thedreps.nekoEco.commands;

import org.bukkit.Bukkit;
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
		String noPerms = prefix + "Sorry you do not have the required permissions.";
		String playerNotFound = prefix + "That player could not be found.";
		String usage = prefix + "Usage: /bal";
		String usage2 = prefix + "Usage: /bal [player]";
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(prefix + "This command is for players only!");
			return true;
		}
		
		
		Player player = (Player) sender;
		
		if (args.length == 0){
			
			if(player.hasPermission("nekoeco.balance")){
				player.sendMessage(prefix + "$" + api.getBal(player.getUniqueId().toString()));
				return true;
			}else{
				player.sendMessage(noPerms);
				return true;
			}
			
			
			
			
		}else if(args.length == 1){
			
			if(player.hasPermission("nekoeco.balance.others")){
				Player player2 = Bukkit.getPlayer(args[0]);
				
				if(player2 == null){
					player.sendMessage(playerNotFound);
					return true;
				}
				
				player.sendMessage(prefix + "$" + api.getBal(player2.getUniqueId().toString()));
				return true;
			}else{
				player.sendMessage(noPerms);
				return true;
			}
			
		}else{
			
			if(player.hasPermission("nekoeco.balance")){
				if(player.hasPermission("nekoeco.balance.others")){
					player.sendMessage(usage2);
				}else{
					player.sendMessage(usage);
				}
			}else{
				player.sendMessage(noPerms);
			}
			
			return true;
			
		}
	}

}
