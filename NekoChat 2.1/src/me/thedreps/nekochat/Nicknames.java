package me.thedreps.nekochat;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.Bukkit;

public class Nicknames {
	
	public String getNickname(String uuid){
		
		try{
			ResultSet res = NekoChat.c.createStatement().executeQuery("SELECT * FROM `nicknames` WHERE `uuid`= '" + uuid + "'");
			
			
			if(!res.next()){
				setNickname(uuid, "off");
				return "off";
				
				
			}else{
				return res.getString("nickname");
			}
			
			
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		
		
		Bukkit.getLogger().warning("[NekoChat] SQL error!");
		return "off";
	}
	
	
	
	public void setNickname(String uuid, String name){
		
		try{
			
			ResultSet res = NekoChat.c.createStatement().executeQuery("SELECT * FROM `nicknames` WHERE `uuid`= '" + uuid + "'");
			if(!res.next()){
				NekoChat.c.createStatement().executeUpdate("INSERT INTO `nicknames` (`uuid`,`nickname`) VALUES ('" + uuid + "','" + name +"')");
			}else{
				NekoChat.c.createStatement().executeUpdate("UPDATE `nicknames` SET `nickname`='" + name + "' WHERE `uuid`='" + uuid + "'");
			}
			
			
			
			
			
			
		}catch(SQLException sql){
			sql.printStackTrace();
			Bukkit.getLogger().warning("[NekoChat] SQL error!");
		}
	}

}
