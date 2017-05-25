package me.thedreps.nekochat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;

public class Nicknames {
	
	public String getNickname(UUID uuid){
		
		try{
			ResultSet res = NekoChat.c.createStatement().executeQuery("SELECT * FROM nicknames WHERE uuid= '" + uuid + "'");
			if(!res.next()){
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

}
