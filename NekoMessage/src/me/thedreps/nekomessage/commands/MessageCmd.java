package me.thedreps.nekomessage.commands;

import java.util.Arrays;
import me.thedreps.nekomessage.DataStorage;
import me.thedreps.nekomessage.NekoMessage;
import me.thedreps.nekomessage.Rank;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MessageCmd extends Command {

	public MessageCmd() {
		super("msg", "", "message", "m", "w", "whisper", "t", "tell");
	}
	
	Rank rank = new Rank();
	DataStorage storage = new DataStorage();

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		ProxiedPlayer player = (ProxiedPlayer) sender;
		
		
		String prefix = ChatColor.DARK_PURPLE + "Msg" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		ProxyServer proxy = NekoMessage.plugin.getProxy();

		if (args.length < 2) { //Checks for correct syntax

			player.sendMessage(new ComponentBuilder(prefix + "Usage: /msg <player> <message>").create());
			return;
		}
		
		
		ProxiedPlayer receiver = proxy.getPlayer(args[0]);
		
		if (receiver == null){ //Checks to see if player is online
			sender.sendMessage(new ComponentBuilder(prefix + "That player could not be found").create());
			return;
		}
		
		String[] msgArray = Arrays.copyOfRange(args, 1, args.length);
		String msg = String.join(" ", msgArray);
		
		player.sendMessage(new ComponentBuilder(ChatColor.LIGHT_PURPLE + "[" + ChatColor.RESET + "You " + ChatColor.LIGHT_PURPLE + "-> "
				+ rank.getColoredRank(receiver.getUniqueId()) + ChatColor.RESET + " " + receiver.getDisplayName()
				+ ChatColor.LIGHT_PURPLE + "] " + ChatColor.RESET
				+ msg).create());
		
		receiver.sendMessage(new ComponentBuilder(ChatColor.LIGHT_PURPLE + "["
				+ rank.getColoredRank(player.getUniqueId()) + ChatColor.RESET + " " + player.getDisplayName()
				+ ChatColor.LIGHT_PURPLE + " -> "
				+ ChatColor.RESET + "You" + ChatColor.LIGHT_PURPLE + "] " + ChatColor.RESET
				+ msg).create());
		
		
		DataStorage.lastMsg.put(player.getUniqueId(), receiver.getUniqueId());
		DataStorage.lastMsg.put(receiver.getUniqueId(), player.getUniqueId());
	}
}