package me.thedreps.nekoEco;

import org.bukkit.plugin.java.JavaPlugin;

import me.thedreps.nekoEco.commands.BalanceCmd;
import me.thedreps.nekoEco.commands.PayCmd;
import me.thedreps.nekoEco.commands.admin.AddBalanceCmd;
import me.thedreps.nekoEco.commands.admin.SetBalanceCmd;
import me.thedreps.nekoEco.commands.admin.TakeBalanceCmd;

public class NekoEco extends JavaPlugin{
	
	public static NekoEco plugin;
	
	
	public void onEnable(){
		registerCommands();

		plugin = this;
	}

	
	private void registerCommands() {
		getCommand("balance").setExecutor(new BalanceCmd());
		getCommand("setbalance").setExecutor(new SetBalanceCmd());
		getCommand("addbalance").setExecutor(new AddBalanceCmd());
		getCommand("takebalance").setExecutor(new TakeBalanceCmd());
		getCommand("pay").setExecutor(new PayCmd());
	}

}
