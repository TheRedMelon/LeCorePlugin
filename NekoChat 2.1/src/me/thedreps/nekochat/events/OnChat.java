package me.thedreps.nekochat.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.thedreps.nekochat.NekoChat;
import me.thedreps.nekochat.Nicknames;
import me.thedreps.nekochat.Rank;

public class OnChat implements Listener {

	Rank rank = new Rank();
	Nicknames nicknames = new Nicknames();

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {

		Player p = event.getPlayer();

		if(p.hasPermission("chat.color")){ //Gives color and formatting if they have permission
			event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
		}
		
		String path = ChatColor.translateAlternateColorCodes('&', NekoChat.plugin.getConfig().getString("Chat format"));


		String nick = ChatColor.translateAlternateColorCodes('&', nicknames.getNickname(p.getUniqueId().toString()));
		
		if (nick.equalsIgnoreCase("off")) { //Checking for disable command
			path = path.replace("getMessage", "%2$s").replace("getDisplayName", "%1$s").replace("getRank", rank.getColoredRank(p.getUniqueId()));
				
		}else{
			nick = ChatColor.GRAY + "~" + ChatColor.RESET + nick;
			path = path.replace("getMessage", "%2$s").replace("getDisplayName", nick).replace("getRank", rank.getColoredRank(p.getUniqueId()));

		}
		
		


		event.setFormat(path);
	}
}
