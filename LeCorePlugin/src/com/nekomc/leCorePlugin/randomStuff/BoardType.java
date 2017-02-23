package com.nekomc.leCorePlugin.randomStuff;

public enum BoardType {

	HUB("hub");
	
	private final String boardType;
	
	BoardType(String type) {
		
		boardType = type;
		
	}
	
	public String getType() {
		
		return boardType;
		
	}
	
}
