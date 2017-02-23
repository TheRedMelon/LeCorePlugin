package com.nekomc.nekoBoard;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.nekomc.leCorePlugin.customEvents.PlayerBoardUpdateEvent;
import com.nekomc.leCorePlugin.randomStuff.BoardSection;
import com.nekomc.leCorePlugin.randomStuff.BoardType;

public class Test implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		if (args[0].equalsIgnoreCase("money")) {
		
			Bukkit.getPluginManager().callEvent(new PlayerBoardUpdateEvent(((Player) sender).getUniqueId(), BoardType.HUB, BoardSection.MONEY));
			
		} else if (args[0].equalsIgnoreCase("level")) {
			
			Bukkit.getPluginManager().callEvent(new PlayerBoardUpdateEvent(((Player) sender).getUniqueId(), BoardType.HUB, BoardSection.LEVEL));
			
		} else {
			
			Bukkit.getPluginManager().callEvent(new PlayerBoardUpdateEvent(((Player) sender).getUniqueId(), BoardType.HUB, BoardSection.SERVER));
			
		}
		
		return true;
	}

}
