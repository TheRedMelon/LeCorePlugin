package me.thedreps.nekochat.events;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.thedreps.nekochat.NekoChat;
import me.thedreps.nekochat.Rank;

public class OnChat implements Listener {

	Rank rank = new Rank();

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {

		Player p = event.getPlayer();
		FileConfiguration nicknameConfig = NekoChat.plugin.getNicknameConfig();

		if(p.hasPermission("chat.color")){ //Gives color and formatting if they have permission
			event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
		}
		
		String path = ChatColor.translateAlternateColorCodes('&', NekoChat.plugin.getConfig().getString("Chat format"));

		if (!(nicknameConfig.contains(p.getUniqueId().toString()))) { //Checks for their UUID
			path = path.replace("getMessage", "%2$s").replace("getDisplayName", "%1$s").replace("getRank",
					rank.getColoredRank(p.getUniqueId()));

		} else { //If its found
			String nickname = nicknameConfig.getString(p.getUniqueId().toString());

			if (nickname.equalsIgnoreCase("off")) { //Checking for disable command
				path = path.replace("getMessage", "%2$s").replace("getDisplayName", "%1$s").replace("getRank",
						rank.getColoredRank(p.getUniqueId()));
				
			} else { //Displays the nickname

				nickname = nickname.replace(nickname, ChatColor.translateAlternateColorCodes('&', "&7~&f" + nickname));
				path = path.replace("getMessage", "%2$s").replace("getDisplayName", nickname).replace("getRank",rank.getColoredRank(p.getUniqueId()));
			}
		}

		event.setFormat(path);
	}
}
