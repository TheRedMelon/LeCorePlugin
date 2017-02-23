package com.nekomc.leCorePlugin.randomStuff;

public enum BoardSection {

	SERVER("server"), MONEY("money"), LEVEL("level");
	
	private final String boardSection;
	
	BoardSection(String section) {
		
		boardSection = section;
		
	}
	
	public String getSection() {
		
		return boardSection;
		
	}
	
}
