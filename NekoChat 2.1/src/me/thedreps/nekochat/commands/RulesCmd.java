package me.thedreps.nekochat.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.thedreps.nekochat.NekoChat;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;

public class RulesCmd implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = (Player) sender;
		
		List<String> rules = NekoChat.plugin.getConfig().getStringList("Rules");
		
		for (int i = 1; i <= rules.size(); i++) {
			
			p.sendMessage(ChatColor.LIGHT_PURPLE + Integer.toString(i) + ") " + rules.get(i - 1));
			
		}
		
		String json = "{\"text\":\"Click here to see the rules online.\",\"color\":\"dark_purple\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://www.nekomc.com/rules\"}}";
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(json));
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
		
		
		
		
		return false;
	}

}
