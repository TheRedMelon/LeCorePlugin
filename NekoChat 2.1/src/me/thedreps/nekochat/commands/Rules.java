package me.thedreps.nekochat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;

public class Rules implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = (Player) sender;
		
		
		String json = "{\"text\":\"Click here to see the rules.\",\"color\":\"dark_purple\",\"bold\":true,\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://www.nekomc.com/rules\"}}";
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(json));
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
		
		
		return false;
	}

}
