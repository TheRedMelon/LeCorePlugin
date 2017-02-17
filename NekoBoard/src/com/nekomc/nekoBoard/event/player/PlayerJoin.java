package com.nekomc.nekoBoard.event.player;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.nekomc.nekoBoard.Hub;

public class PlayerJoin implements Listener {

	public void onPlayerJoin (PlayerJoinEvent e) {
		
		new Hub().showPlayer(e.getPlayer().getUniqueId());
		
	}
	
}
