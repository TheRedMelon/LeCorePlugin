package com.nekomc.npcTutorial.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.nekomc.npcTutorial.NPCTutorial;

public class Main implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		if (args.length <= 0 || args.length >= 3) {
			
			sendHelp(sender);
			return false;
			
		} else if (args[0].equalsIgnoreCase("help") && args.length == 1) {
			
			sendHelp(sender);
			return false;
			
		} else if (args[0].equalsIgnoreCase("setup") && args.length == 3) {
			
			} else if (args[1].equalsIgnoreCase("createtutorial")) {
			
				if (!NPCTutorial.plugin.tutSetup.containsKey(sender.getName())) {
					
					if (!(NPCTutorial.plugin.tuts.contains(args[2]))) {
						
						List<Object> newList = new ArrayList<Object>();
						newList.add(args[2]);
						
						NPCTutorial.plugin.tutSetup.put(sender.getName(), newList);
						
					} else {
						
						sender.sendMessage(ChatColor.RED + "There is already a tutorial with that name or it is still being made!");
						return false;
						
					}
					
				} else {
					
					sender.sendMessage(ChatColor.RED + "You are already creating a tutorial, it is named '" + NPCTutorial.plugin.tutSetup.get(sender.getName()).get(0) + "'!");
					return false;
					
				}
				
			}
			
		} else {
			
			sendHelp(sender);
			return false;
			
		}
		
		return true;
		
	}
	
	private void sendHelp(CommandSender s) {
		
		s.sendMessage(ChatColor.RED + "Incorrect Usage!");
		s.sendMessage(ChatColor.RED + "/npctutorial <createtutorial | help> <name>");
		
	}

}
