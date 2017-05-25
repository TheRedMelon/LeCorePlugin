package me.thedreps.nekospawn.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.thedreps.nekospawn.NekoSpawn;

public class SpawnCmd implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command is for players only!");
			return true;
		}
		
		Player p = (Player) sender;
		
		World world = Bukkit.getWorld(NekoSpawn.plugin.getConfig().getString("spawn.world"));
		double x = NekoSpawn.plugin.getConfig().getDouble("spawn.x");
		double y = NekoSpawn.plugin.getConfig().getDouble("spawn.y");
		double z = NekoSpawn.plugin.getConfig().getDouble("spawn.z");
		float yaw = NekoSpawn.plugin.getConfig().getInt("spawn.yaw");
		float pitch = NekoSpawn.plugin.getConfig().getInt("spawn.pitch");
		
		
		Location l = new Location(world, x, y, z, yaw, pitch);
		
		p.teleport(l);
		
		
		return false;
	}

}
