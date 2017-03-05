package come.nekomc.bungeecore.playerMisc;

import java.util.UUID;

import me.TheDreps.leveling.API;

public class Leveling {
	API api = new API();
	
	public String getChatPrefix(UUID uuid){
		return api.getChatPrefix(uuid);
	}
	
	public int getLevel(UUID uuid){
		return api.getLevel(uuid);
	}
	
	public int getPoints(UUID uuid){
		return api.getPoints(uuid);
	}
	
	public void addPoints(UUID uuid, int amount){
		api.addPoints(uuid, amount);
	}
	
	public boolean testPoints(UUID uuid){
		return api.testPoints(uuid);
	}
	
	public String getProgress(UUID uuid){
		return api.getProgress(uuid);
	}
	
}
