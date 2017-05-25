package me.thedreps.nekochat;

import java.sql.Connection;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.thedreps.nekochat.commands.NicknameCmd;
import me.thedreps.nekochat.commands.RulesCmd;
import me.thedreps.nekochat.events.OnChat;
import me.thedreps.nekochat.events.PlayerJoin;
import me.thedreps.nekochat.events.PlayerQuit;
import me.thedreps.nekochat.sql.MySQL;
import me.thedreps.nekochat.sql.SQLD;

public class NekoChat extends JavaPlugin{
	
	public static NekoChat plugin;
	public static Connection c;
	MySQL SQL = new MySQL(SQLD.host, SQLD.port, SQLD.db, SQLD.user, SQLD.pw);
	
	
	public void onEnable(){
		registerConfig();
		registerEvents();
		registerCommands();
		
		
		try{
			c = SQL.open();
		}catch(Exception sql){
			sql.printStackTrace();
		}
		
		
		plugin = this;
	}
	
	
	public void onDisbale(){
		c = MySQL.closeConnection(c);
	}
	
	
	public void registerEvents(){
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new OnChat(), this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerQuit(), this);
	}
	
	
	private void registerConfig(){
	    getConfig().options().copyDefaults(true);
	    saveConfig();
	  }
	
	private void registerCommands(){
	    getCommand("rules").setExecutor(new RulesCmd());
	    getCommand("nickname").setExecutor(new NicknameCmd());
	  }
	

}
