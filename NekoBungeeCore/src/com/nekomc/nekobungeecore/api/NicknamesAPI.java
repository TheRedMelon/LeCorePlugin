package com.nekomc.nekobungeecore.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nekomc.nekobungeecore.NekoBungeeCore;
import com.nekomc.nekobungeecore.sql.nicknames.MySQL;
import com.nekomc.nekobungeecore.sql.nicknames.Nicknames;


public class NicknamesAPI {
	
	public static Connection c;
	MySQL SQL = new MySQL(Nicknames.host, Nicknames.port, Nicknames.db, Nicknames.user, Nicknames.pw);
	
	public String getNickname(String uuid){
		
		try{
			c = SQL.open();
			ResultSet res = c.createStatement().executeQuery("SELECT * FROM `nicknames` WHERE `uuid`= '" + uuid + "'");
			
			
			if(!res.next()){
				setNickname(uuid, "off");
				c = MySQL.closeConnection(c);
				return "off";
				
				
			}else{
				c = MySQL.closeConnection(c);
				return res.getString("nickname");
			}
			
			
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		
		
		NekoBungeeCore.plugin.getLogger().warning("[NekoChat] SQL error! (Nicknames)");
		return "off";
	}
	
	
	
	public void setNickname(String uuid, String name){
		
		try{
			c = SQL.open();
			
			ResultSet res = c.createStatement().executeQuery("SELECT * FROM `nicknames` WHERE `uuid`= '" + uuid + "'");
			if(!res.next()){
				c.createStatement().executeUpdate("INSERT INTO `nicknames` (`uuid`,`nickname`) VALUES ('" + uuid + "','" + name +"')");
				c = MySQL.closeConnection(c);
			}else{
				c.createStatement().executeUpdate("UPDATE `nicknames` SET `nickname`='" + name + "' WHERE `uuid`='" + uuid + "'");
				c = MySQL.closeConnection(c);
			}
			
			
			
			
			
			
		}catch(SQLException sql){
			sql.printStackTrace();
			NekoBungeeCore.plugin.getLogger().warning("[NekoChat] SQL error! (Nicknames)");
		}
	}

}
