package me.thedreps.nekospawn.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.thedreps.nekospawn.NekoSpawn;

public class SetSpawnCmd implements CommandExecutor{
	
	public FileConfiguration getConfig() {
		return NekoSpawn.plugin.getConfig();
	}

	public void saveConfig() {
		NekoSpawn.plugin.saveConfig();
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		String prefix = ChatColor.DARK_PURPLE + "Spawn" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(prefix + "This command is for players only!");
			return true;
		}

		Player p = (Player) sender;

		if (sender.hasPermission("nekospawn.setspawn")) {
			getConfig().set("spawn.world", p.getLocation().getWorld().getName());
			getConfig().set("spawn.x", p.getLocation().getX());
			getConfig().set("spawn.y", p.getLocation().getY());
			getConfig().set("spawn.z", p.getLocation().getZ());
			getConfig().set("spawn.pitch", p.getLocation().getPitch());
			getConfig().set("spawn.yaw", p.getLocation().getYaw());
			saveConfig();
			p.sendMessage(prefix + "Spawn set!");
			return true;
				
		} else {
			sender.sendMessage(prefix + "You do not have the required permissions!");
		}
		
		
		return false;
	}

}
