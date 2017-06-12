package me.thedreps.nekomessage.commands;

import java.util.Arrays;

import com.nekomc.nekocore.api.NicknamesAPI;
import com.nekomc.nekocore.api.RankAPI;

import me.thedreps.nekomessage.DataStorage;
import me.thedreps.nekomessage.NekoMessage;
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
	
	RankAPI rank = new RankAPI();
	NicknamesAPI nick = new NicknamesAPI();
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
		
		if(nick.getNickname(receiver.getUniqueId().toString()).equalsIgnoreCase("off")){
			player.sendMessage(new ComponentBuilder(ChatColor.LIGHT_PURPLE + "[" + ChatColor.RESET + "You " + ChatColor.LIGHT_PURPLE + "-> "
					+ rank.getColoredRank(receiver.getUniqueId()) + ChatColor.RESET + receiver.getDisplayName()
					+ ChatColor.LIGHT_PURPLE + "] " + ChatColor.RESET
					+ msg).create());
		}else{
			String rRank = ChatColor.translateAlternateColorCodes('&', "&7~" + nick.getNickname(receiver.getUniqueId().toString()));
			player.sendMessage(new ComponentBuilder(ChatColor.LIGHT_PURPLE + "[" + ChatColor.RESET + "You " + ChatColor.LIGHT_PURPLE + "-> "
					+ rank.getColoredRank(receiver.getUniqueId()) + ChatColor.RESET + rRank
					+ ChatColor.LIGHT_PURPLE + "] " + ChatColor.RESET
					+ msg).create());
		}
		
		if(nick.getNickname(player.getUniqueId().toString()).equalsIgnoreCase("off")){
			receiver.sendMessage(new ComponentBuilder(ChatColor.LIGHT_PURPLE + "["
					+ rank.getColoredRank(player.getUniqueId()) + ChatColor.RESET + player.getDisplayName()
					+ ChatColor.LIGHT_PURPLE + " -> "
					+ ChatColor.RESET + "You" + ChatColor.LIGHT_PURPLE + "] " + ChatColor.RESET
					+ msg).create());
		}else{
			String pRank = ChatColor.translateAlternateColorCodes('&', "&7~" + nick.getNickname(player.getUniqueId().toString()));
			receiver.sendMessage(new ComponentBuilder(ChatColor.LIGHT_PURPLE + "["
					+ rank.getColoredRank(player.getUniqueId()) + ChatColor.RESET + pRank
					+ ChatColor.LIGHT_PURPLE + " -> "
					+ ChatColor.RESET + "You" + ChatColor.LIGHT_PURPLE + "] " + ChatColor.RESET
					+ msg).create());
		}
		
		
		
		
		
		DataStorage.lastMsg.put(player.getUniqueId(), receiver.getUniqueId());
		DataStorage.lastMsg.put(receiver.getUniqueId(), player.getUniqueId());
	}
}