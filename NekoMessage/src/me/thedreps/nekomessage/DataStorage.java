package me.thedreps.nekomessage;

import java.util.HashMap;
import java.util.UUID;

public class DataStorage {
	
	private HashMap<UUID, UUID> lastSentMessages = new HashMap<UUID, UUID>();
	
	public HashMap<UUID, UUID> getReplyMap(){
		return lastSentMessages;
	}

}
