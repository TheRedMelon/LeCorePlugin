package me.thedreps.nekoEco.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.thedreps.nekoEco.EcoAPI;
import me.thedreps.nekochat.Nicknames;
import me.thedreps.nekochat.Rank;

public class PayCmd implements CommandExecutor {

	EcoAPI api = new EcoAPI();
	Nicknames nick = new Nicknames();
	Rank rank = new Rank();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		String prefix = ChatColor.DARK_PURPLE + "Eco" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		String noPerms = prefix + "Sorry you do not have the required permissions.";
		String playerNotFound = prefix + "That player could not be found.";
		String usage = prefix + "Usage: /pay <player> <amount>";

		if (!(sender instanceof Player)) {
			sender.sendMessage(prefix + "This command is for players only!");
			return true;
		}

		Player player = (Player) sender;

		if (args.length == 0 || args.length == 1) {

			if (player.hasPermission("nekoeco.pay")) {
				player.sendMessage(usage);
				return true;
			} else {
				player.sendMessage(noPerms);
				return true;
			}

		} else if (args.length == 2) {

			if (player.hasPermission("nekoeco.pay")) {
				Player player2 = Bukkit.getPlayer(args[0]);

				if (player2 == null) {
					player.sendMessage(playerNotFound);
					return true;
				}
				
				

				String uuid = player.getUniqueId().toString();
				String uuid2 = player2.getUniqueId().toString();
				int amount = 0;
				
				
				try{
					amount = Integer.parseInt(args[1]);
					if(amount < 0){
						player.sendMessage(prefix + "Please use a positive amount.");
						return true;
					}
				}catch (NumberFormatException e){
					player.sendMessage(prefix + "Invalid number!");
					return true;
				}

				
				
				
				if(api.checkBal(uuid, amount)){
					api.addBal(uuid2, amount);
					api.takeBal(uuid, amount);
					
					String playerNick = getName(player.getUniqueId());
					String player2Nick = getName(player2.getUniqueId());
					
					player.sendMessage(prefix + "You sent $" + amount + " to " + player2Nick);
					player2.sendMessage(prefix + "You recieved $" + amount + " from " + playerNick);
				}else{
					player.sendMessage(prefix + "You can't afford this!");
				}
				
				return true;

			} else {
				player.sendMessage(noPerms);
				return true;
			}

		} else {

			if (player.hasPermission("nekoeco.pay")) {
				player.sendMessage(usage);
			} else {
				player.sendMessage(noPerms);
			}

			return true;
		}
	}
	
	private String getName(UUID uuid){
		
		String playerNick = null;
		if(nick.getNickname(uuid.toString()).equalsIgnoreCase("off")){
			playerNick = rank.getColoredRank(uuid) + Bukkit.getPlayer(uuid).getName();
		}else{
			playerNick = rank.getColoredRank(uuid) + nick.getNickname(uuid.toString());
		}
		
		
		return playerNick;
	}
}
