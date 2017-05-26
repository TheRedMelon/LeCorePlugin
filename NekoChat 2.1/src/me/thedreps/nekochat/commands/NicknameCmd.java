package me.thedreps.nekochat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.thedreps.nekochat.NekoChat;
import me.thedreps.nekochat.Nicknames;

public class NicknameCmd implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Nicknames nicknames = new Nicknames();
		String prefix = ChatColor.DARK_PURPLE + "Chat" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command can only be used by a player!");
			return true;
		}
		
		
		if(sender.hasPermission("nekochat.nickname") && !(sender.hasPermission("rank.mod") && !(sender.hasPermission("rank.admin")))){
			if (!(args.length == 1)) {
				sender.sendMessage(prefix + "/nick <nickname>");
				return false;

			}
			
			
			Player p = (Player) sender;
			
			
			if(args[0].length() > 16){
				p.sendMessage(prefix + "Nickname is too long!");
				return false;
			}
			
			
			String test = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', args[0])).toLowerCase();

			if(NekoChat.plugin.getNamesConfig().contains(test)){
				p.sendMessage(prefix + "Ay thats identity theft!");
				return false;
			}
			
			
			nicknames.setNickname(p.getUniqueId().toString(), args[0]);
			
			
			if(args[0].equalsIgnoreCase("off")){
				p.sendMessage(prefix + "Nickname disabled!");
				return false;
			}
			
			
			p.sendMessage(prefix + "Nickname set!");
			
			
			return false;
			
			
			
		}else{
			sender.sendMessage(prefix + "You do not have the required permissions!");
			return false;
		}
		
	}

}
