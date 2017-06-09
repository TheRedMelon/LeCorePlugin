package me.thedreps.nekoEco;

import java.sql.Connection;

import org.bukkit.plugin.java.JavaPlugin;

import me.thedreps.nekoEco.commands.BalanceCmd;
import me.thedreps.nekoEco.commands.PayCmd;
import me.thedreps.nekoEco.commands.admin.AddBalanceCmd;
import me.thedreps.nekoEco.commands.admin.SetBalanceCmd;
import me.thedreps.nekoEco.commands.admin.TakeBalanceCmd;
import me.thedreps.nekoEco.sql.MySQL;
import me.thedreps.nekoEco.sql.SQLD;

public class NekoEco extends JavaPlugin{
	
	public static NekoEco plugin;
	public static Connection c;
	MySQL SQL = new MySQL(SQLD.host, SQLD.port, SQLD.db, SQLD.user, SQLD.pw);
	
	public void onEnable(){
		registerCommands();
		
		try{
			c = SQL.open();
		}catch(Exception sql){
			sql.printStackTrace();
		}
		
		plugin = this;
	}
	

	public void onDisable(){
		c = MySQL.closeConnection(c);
	}
	
	private void registerCommands() {
		getCommand("balance").setExecutor(new BalanceCmd());
		getCommand("setbalance").setExecutor(new SetBalanceCmd());
		getCommand("addbalance").setExecutor(new AddBalanceCmd());
		getCommand("takebalance").setExecutor(new TakeBalanceCmd());
		getCommand("pay").setExecutor(new PayCmd());
	}

}
