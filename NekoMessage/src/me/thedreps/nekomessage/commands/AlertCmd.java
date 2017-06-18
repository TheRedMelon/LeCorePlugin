package me.thedreps.nekomessage.commands;

import java.util.Arrays;

import me.thedreps.nekomessage.NekoMessage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AlertCmd extends Command {

	public AlertCmd() {
		super("alert", "");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		ProxiedPlayer player = (ProxiedPlayer) sender;
		
		
		String prefix = ChatColor.DARK_RED + "" + ChatColor.BOLD + "ALERT!" + ChatColor.RED + ChatColor.BOLD;
		String prefix2 = ChatColor.DARK_PURPLE + "Alert" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		String noPerms = prefix2 + "Sorry you do not have the required permissions.";
		String usage = prefix2 + "Usage: /alert <message>";
		ProxyServer proxy = NekoMessage.plugin.getProxy();

		if (args.length == 0) { //Checks for correct syntax

			player.sendMessage(new ComponentBuilder(usage).create());
			return;
		}
		
		if(!player.hasPermission("nekomessage.alert")){
			player.sendMessage(new ComponentBuilder(noPerms).create());
		}
		
		String[] msgArray = Arrays.copyOfRange(args, 0, args.length);
		String msg = String.join(" ", msgArray);
		
		proxy.broadcast(new ComponentBuilder(prefix + " " + msg).create());
		
		
	}

}
