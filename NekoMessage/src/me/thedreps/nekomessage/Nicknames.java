package me.thedreps.nekomessage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.thedreps.nekomessage.sql.MySQL;
import me.thedreps.nekomessage.sql.SQLD;


public class Nicknames {
	
	public static Connection c;
	MySQL SQL = new MySQL(SQLD.host, SQLD.port, SQLD.db, SQLD.user, SQLD.pw);
	
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
		
		
		NekoMessage.plugin.getLogger().warning("[NekoChat] SQL error!");
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
			NekoMessage.plugin.getLogger().warning("[NekoChat] SQL error!");
		}
	}

}
