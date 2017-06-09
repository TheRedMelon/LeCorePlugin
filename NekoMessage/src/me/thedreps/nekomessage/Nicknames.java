package me.thedreps.nekomessage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Nicknames {
	
	public String getNickname(String uuid){
		
		try{
			ResultSet res = NekoMessage.c.createStatement().executeQuery("SELECT * FROM `nicknames` WHERE `uuid`= '" + uuid + "'");
			
			
			if(!res.next()){
				setNickname(uuid, "off");
				return "off";
				
				
			}else{
				return res.getString("nickname");
			}
			
			
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		
		
		NekoMessage.plugin.getLogger().warning("[NekoMessage] SQL error!");
		return "off";
	}
	
	
	
	public void setNickname(String uuid, String name){
		
		try{
			
			ResultSet res = NekoMessage.c.createStatement().executeQuery("SELECT * FROM `nicknames` WHERE `uuid`= '" + uuid + "'");
			if(!res.next()){
				NekoMessage.c.createStatement().executeUpdate("INSERT INTO `nicknames` (`uuid`,`nickname`) VALUES ('" + uuid + "','" + name +"')");
			}else{
				NekoMessage.c.createStatement().executeUpdate("UPDATE `nicknames` SET `nickname`='" + name + "' WHERE `uuid`='" + uuid + "'");
			}
			
			
		}catch(SQLException sql){
			sql.printStackTrace();
			NekoMessage.plugin.getLogger().warning("[NekoMessage] SQL error!");
		}
	}

}
