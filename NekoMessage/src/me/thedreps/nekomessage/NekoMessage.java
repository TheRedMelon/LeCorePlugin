package me.thedreps.nekomessage;

import me.thedreps.nekomessage.commands.MessageCmd;
import me.thedreps.nekomessage.commands.ReplyCmd;
import net.md_5.bungee.api.plugin.Plugin;

public class NekoMessage extends Plugin{
	
	public static NekoMessage plugin;
	
	@Override
	public void onEnable(){
		registerCommands();
		
		plugin = this;
	}
	
	
	
	private void registerCommands(){
		getProxy().getPluginManager().registerCommand(this, new MessageCmd());
		getProxy().getPluginManager().registerCommand(this, new ReplyCmd());
	}

}
