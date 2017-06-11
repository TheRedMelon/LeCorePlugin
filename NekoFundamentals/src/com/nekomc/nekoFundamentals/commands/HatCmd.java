package com.nekomc.nekoFundamentals.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class HatCmd implements CommandExecutor {

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
			return true;
			
		}
		
		Player p = (Player) sender;
		ItemStack i = p.getInventory().getItemInMainHand();
		ItemStack head = p.getInventory().getHelmet();
		
		if (i == null || i.getType() == Material.AIR) {
			
			p.sendMessage(prefix + "You cannot wear air on your head!");
			return true;
			
		}
		
		if (p.hasPermission("nf.hat.stack") || (i.getAmount() == 1)) {
			
			p.getInventory().setHelmet(i);
			
			if (!(head == null || head.getType() == Material.AIR)) {
				
				p.getInventory().setItemInMainHand(head);
				
			}
			
		} else {
			
			int size = i.getAmount();
			Material material = i.getType();
			MaterialData data = i.getData();
			ItemStack items = new ItemStack(material, (size - 1));
			int empty = p.getInventory().firstEmpty();
			
			items.setData(data);
			p.getInventory().setItemInMainHand(items);
			p.getInventory().setHelmet(new ItemStack(material, 1));
			
			if (!(head == null || head.getType() == Material.AIR)) {
			
				if (empty == -1) {
					
					p.getWorld().dropItem(p.getLocation(), head);
					
				} else {
					
					p.getInventory().addItem(head);
					
				}
			
			}
			
		}
		
		return true;
		
	}

}
