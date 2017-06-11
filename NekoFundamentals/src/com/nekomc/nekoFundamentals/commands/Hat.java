package com.nekomc.nekoFundamentals.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Hat implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		String prefix = ChatColor.DARK_PURPLE + "Cosmetics" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		String noPerms = prefix + "Sorry you do not have the required permissions.";
		String usage = prefix + "Usage: /hat";
		
		if (!sender.hasPermission("nf.hat")) {
			
			sender.sendMessage(noPerms);
			return true;
			
		}
		
		if (!(sender instanceof Player)) {
			
			sender.sendMessage(prefix + "Please run this command as a PLAYER!");
			return true;
			
		}
		
		if (args.length != 0) {
			
			sender.sendMessage(usage);
			
		}
		
		Player p = (Player) sender;
		ItemStack i = p.getInventory().getItemInMainHand();
		ItemStack head = p.getInventory().getHelmet();
		
		if (p.hasPermission("nf.hat.stack") || (i.getAmount() == 1)) {
			
			p.getInventory().setItemInMainHand(head);
			p.getInventory().setHelmet(i);
			
		} else {
			
			int size = i.getAmount();
			Material material = i.getType();
			ItemStack items = new ItemStack(material, (size - 1));
			int empty = p.getInventory().firstEmpty();
			
			p.getInventory().setItemInMainHand(items);
			p.getInventory().setHelmet(new ItemStack(material, 1));
			
			if (empty == -1) {
				
				p.getWorld().dropItem(p.getLocation(), head);
				
			} else {
				
				p.getInventory().addItem(head);
				
			}
			
		}
		
		return true;
		
	}

}
