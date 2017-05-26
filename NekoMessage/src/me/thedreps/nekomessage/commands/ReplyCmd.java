package me.thedreps.nekomessage.commands;

import me.thedreps.nekomessage.DataStorage;
import me.thedreps.nekomessage.NekoMessage;
import me.thedreps.nekomessage.Nicknames;
import me.thedreps.nekomessage.Rank;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReplyCmd extends Command{

	public ReplyCmd() {
		super("reply", "", "r");
	}
	
	Rank rank = new Rank();
	Nicknames nick = new Nicknames();
	String prefix = ChatColor.DARK_PURPLE + "Msg" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(args.length == 0){
			sender.sendMessage(new ComponentBuilder(prefix + "Usage: /r <message>").create());
			return;
		}
		
		ProxiedPlayer player = (ProxiedPlayer) sender;
		
		if(!(DataStorage.lastMsg.containsKey(player.getUniqueId()))){
			player.sendMessage(new ComponentBuilder(prefix + "You haven't messaged anyone recently!").create());
			return;
		}
		
		ProxiedPlayer receiver = NekoMessage.plugin.getProxy().getPlayer(DataStorage.lastMsg.get(player.getUniqueId()));
		if(receiver == null){
			player.sendMessage(new ComponentBuilder(prefix + "That player is no longer online.").create());
			return;
		}
		
		String msg = String.join(" ", args);
		
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
					+ rank.getColoredRank(player.getUniqueId()) + ChatColor.RESET +  pRank
					+ ChatColor.LIGHT_PURPLE + " -> "
					+ ChatColor.RESET + "You" + ChatColor.LIGHT_PURPLE + "] " + ChatColor.RESET
					+ msg).create());
		}
		
		
		DataStorage.lastMsg.put(receiver.getUniqueId(), player.getUniqueId());

		
	}

}
