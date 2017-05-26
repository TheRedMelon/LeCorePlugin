package me.thedreps.nekomessage;

import java.sql.Connection;


import me.thedreps.nekomessage.commands.MessageCmd;
import me.thedreps.nekomessage.commands.ReplyCmd;
import me.thedreps.nekomessage.sql.MySQL;
import me.thedreps.nekomessage.sql.SQLD;
import net.md_5.bungee.api.plugin.Plugin;

public class NekoMessage extends Plugin{
	
	public static NekoMessage plugin;
	public static Connection c;
	MySQL SQL = new MySQL(SQLD.host, SQLD.port, SQLD.db, SQLD.user, SQLD.pw);
	
	@Override
	public void onEnable(){
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
	
	
	
	private void registerCommands(){
		getProxy().getPluginManager().registerCommand(this, new MessageCmd());
		getProxy().getPluginManager().registerCommand(this, new ReplyCmd());
	}

}
