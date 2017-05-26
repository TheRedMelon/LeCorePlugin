package me.thedreps.nekomessage.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import me.thedreps.nekomessage.DataStorage;
import me.thedreps.nekomessage.NekoMessage;
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
	String prefix = ChatColor.DARK_PURPLE + "Msg" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
	DataStorage storage = new DataStorage();
	HashMap<UUID, UUID> lastMsg = storage.getReplyMap();

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		ProxiedPlayer player = (ProxiedPlayer) sender;
		
		if(!(lastMsg.containsKey(player.getUniqueId()))){
			player.sendMessage(new ComponentBuilder(prefix + "You haven't messaged anyone recently!").create());
			return;
		}
		
		ProxiedPlayer receiver = NekoMessage.plugin.getProxy().getPlayer(lastMsg.get(player.getUniqueId()));
		if(receiver == null){
			player.sendMessage(new ComponentBuilder(prefix + "That player is no longer online.").create());
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
		
		lastMsg.put(receiver.getUniqueId(), player.getUniqueId());
		
	}

}
