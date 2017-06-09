package me.thedreps.nekoEco.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.thedreps.nekoEco.API;

public class TakeBalanceCmd implements CommandExecutor{
	
	API api = new API();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String prefix = ChatColor.DARK_PURPLE + "Eco" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		String noPerms = prefix + "Sorry you do not have the required permissions.";
		String playerNotFound = prefix + "That player could not be found.";
		String usage = prefix + "Usage: /setbal [player] <amount>";
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(prefix + "This command is for players only!");
			return true;
		}
		
		
		Player player = (Player) sender;
		
		if (args.length == 0){
			
			if(player.hasPermission("nekoeco.setbalance")){
				player.sendMessage(usage);
				return true;
			}else{
				player.sendMessage(noPerms);
				return true;
			}
			
			
			
			
		}else if(args.length == 1){
			
			if(player.hasPermission("nekoeco.setbalance")){

				int amount = Integer.parseInt(args[0]);
				api.takeBal(player.getUniqueId().toString(), amount);
				
				player.sendMessage(prefix + "Balance set!");
				return true;
			}else{
				player.sendMessage(noPerms);
				return true;
			}
			
			
		}else if(args.length == 2){
			
			if(player.hasPermission("nekoeco.setbalance")){
				Player player2 = Bukkit.getPlayer(args[1]);
				
				if(player2 == null){
					player.sendMessage(playerNotFound);
					return true;
				}
				
				String uuid = player2.getUniqueId().toString();
				int amount = Integer.parseInt(args[0]);
				
				api.takeBal(uuid, amount);
				
				player.sendMessage(prefix + "Balance set!");
				return true;
			}else{
				player.sendMessage(noPerms);
				return true;
			}
			
		}else{
			
			if(player.hasPermission("nekoeco.setbalance")){
				player.sendMessage(usage);
			}else{
				player.sendMessage(noPerms);
			}
			
			return true;
			
		}
	}

}