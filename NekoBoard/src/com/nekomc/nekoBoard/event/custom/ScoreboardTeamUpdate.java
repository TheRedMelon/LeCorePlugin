package com.nekomc.nekoBoard.event.custom;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.nekomc.leCorePlugin.customEvents.ScoreboardTeamsUpdateEvent;

public class ScoreboardTeamUpdate implements Listener {

	@EventHandler
	public void onScoreboardTeamUpdate (ScoreboardTeamsUpdateEvent e) {
		
		Scoreboard sb = Bukkit.getPlayer(e.getName()).getScoreboard();
		
		boolean exists = false;
		
		for (Team t : sb.getTeams()) {
			
			if (t.getName().equals(e.getTeam().getName())) {
				
				exists = true;
				break;
				
			}
			
		}
		
		if (!exists) {
			
			Team t = sb.registerNewTeam(e.getTeam().getName());
			
			t.setPrefix(e.getTeam().getPrefix());
			t.addEntry(e.getName());
			
		} else {
			
			Team t = sb.getTeam(e.getTeam().getName());
			
			t.addEntry(e.getName());
			
		}
		
	}
	
}
