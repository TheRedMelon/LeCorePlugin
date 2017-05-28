package com.nekomc.nekoFundamentals.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCmd implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String prefix = ChatColor.DARK_PURPLE + "Cheat" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		String noPerms = prefix + "Sorry you do not have the required permissions";
		String usage = prefix + "Usage: /gamemode <gamemode> [player]";
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(prefix + "This command is for players only!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(p.hasPermission("nf.gamemode")){
			
			
			if(args.length == 0 || args.length > 2){
				p.sendMessage(usage);
				return false;
				
				
			}else if(args.length == 1){ /// Args 1
				
				if(args[0].equalsIgnoreCase("survival") ||
					args[0].equalsIgnoreCase("s") ||
					args[0].equalsIgnoreCase("0")){
					
					
					if(p.hasPermission("nf.gamemode.survival")){
						
						p.sendMessage(prefix + "Set your gamemode to survival");
						p.setGameMode(GameMode.SURVIVAL);
						return false;
						
					}else{
						p.sendMessage(noPerms);
						return false;
					}
					
					
				}else if(args[0].equalsIgnoreCase("creative") ||
						args[0].equalsIgnoreCase("c") ||
						args[0].equalsIgnoreCase("1")){
					
					if(p.hasPermission("nf.gamemode.creative")){
						
						p.setGameMode(GameMode.CREATIVE);
						return false;
						
					}else{
						p.sendMessage(noPerms);
						return false;
					}
					
					
				}else if(args[0].equalsIgnoreCase("spectator") ||
						args[0].equalsIgnoreCase("sp") ||
						args[0].equalsIgnoreCase("3")){
					
					if(p.hasPermission("nf.gamemode.spectator")){
						
						p.setGameMode(GameMode.SPECTATOR);
						return false;
						
					}else{
						p.sendMessage(noPerms);
						return false;
					}
					
					
				}else if(args[0].equalsIgnoreCase("adventure") ||
						args[0].equalsIgnoreCase("a") ||
						args[0].equalsIgnoreCase("4")){
					
					if(p.hasPermission("nf.gamemode.adventure")){
						
						p.setGameMode(GameMode.ADVENTURE);
						return false;
						
					}else{
						p.sendMessage(noPerms);
						return false;
					}
					
				}else{
					p.sendMessage(usage);
					return false;
				}
				
			}else if(args.length == 2){ ///Args 2
				
				if(!(p.hasPermission("nf.gamemode.others"))){
					p.sendMessage(noPerms);
					return false;
				}
				
				
				Player p2 = Bukkit.getPlayer(args[1]);
				if(p2 == null){
					p.sendMessage(prefix + "Could not find player " + args[1]);
					return false;
				}
				
				
				
				if(args[0].equalsIgnoreCase("survival")){
					
					
					if(args[0].equalsIgnoreCase("survival") ||
							args[0].equalsIgnoreCase("s") ||
							args[0].equalsIgnoreCase("0")){
						
						p2.setGameMode(GameMode.SURVIVAL);
						return false;
						
					}else{
						p.sendMessage(noPerms);
						return false;
					}
					
					
				}else if(args[0].equalsIgnoreCase("creative") ||
						args[0].equalsIgnoreCase("c") ||
						args[0].equalsIgnoreCase("1")){
					
					if(p.hasPermission("nf.gamemode.creative")){
						
						p2.setGameMode(GameMode.CREATIVE);
						return false;
						
					}else{
						p.sendMessage(noPerms);
						return false;
					}
					
					
				}else if(args[0].equalsIgnoreCase("spectator") ||
						args[0].equalsIgnoreCase("sp") ||
						args[0].equalsIgnoreCase("3")){
					
					if(p.hasPermission("nf.gamemode.spectator")){
						
						p2.setGameMode(GameMode.SPECTATOR);
						return false;
						
					}else{
						p.sendMessage(noPerms);
						return false;
					}
					
					
				}else if(args[0].equalsIgnoreCase("adventure") ||
						args[0].equalsIgnoreCase("a") ||
						args[0].equalsIgnoreCase("4")){
					
					if(p.hasPermission("nf.gamemode.adventure")){
						
						p2.setGameMode(GameMode.ADVENTURE);
						return false;
						
					}else{
						p.sendMessage(noPerms);
						return false;
					}
					
				}
				else{
					p.sendMessage(usage);
					return false;
				}
				
			}
			
			
		}else{
			p.sendMessage(noPerms);
			return false;
		}
		
		
		
		
		
		return false;
	}

}
