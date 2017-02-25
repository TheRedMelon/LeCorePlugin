package com.nekomc.nekoBoard.commands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.nekomc.leCorePlugin.customEvents.PlayerBoardUpdateEvent;
import com.nekomc.leCorePlugin.randomStuff.BoardSection;
import com.nekomc.leCorePlugin.randomStuff.BoardType;
import com.nekomc.nekoBoard.NekoBoard;

public class Main implements CommandExecutor {

	PluginManager pm = Bukkit.getPluginManager();
	ScoreboardManager sbm = Bukkit.getScoreboardManager();
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		if (args.length < 1) {
			
			sender.sendMessage(ChatColor.DARK_RED + "Incorrect Usage: /nb <update | hide> [player | section | board] [section | board] [section | board]...");
			return false;
			
		} else if (args[0].equalsIgnoreCase("update")) {
		
			if (args.length == 1) {
				
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					
					for (BoardSection section : BoardSection.values()) {
						
						pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), section));
						
					}
					
				}
				
			} else if (args.length == 2) {
				
				if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayerExact(args[1]))) {
					
					Player p = Bukkit.getPlayerExact(args[1]);
					
					for (BoardSection section : BoardSection.values()) {
					
						pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), section));
					
					}
						
				} else {
				
					try {
						
						BoardSection section = BoardSection.valueOf(args[1].toUpperCase());
						
						for (Player p : Bukkit.getServer().getOnlinePlayers()) {
							
							pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), section));
							
						}
						
					} catch (IllegalArgumentException e) {
						
						sender.sendMessage(ChatColor.DARK_RED + "No user or board section " + args[1]);
						return false;
						
					}
					
				}
				
			} else if (args.length == 3) {
				
				if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayerExact(args[1]))) {
					
					Player p = Bukkit.getPlayerExact(args[1]);
					
					try {
						
						pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), BoardSection.valueOf(args[2].toUpperCase())));
					
					} catch (IllegalArgumentException e) {
						
						sender.sendMessage(ChatColor.DARK_RED + "No board section " + args[2]);
						return false;
						
					}
						
				} else {
					
					for (Player p : Bukkit.getServer().getOnlinePlayers()) {
						
						List<String> argsL = new ArrayList<String>(Arrays.asList(args));
						
						argsL.remove(0);
						
						for (String str : argsL) {
							
							try {
								
								pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), BoardSection.valueOf(str.toUpperCase())));
								
							} catch (IllegalArgumentException e) {
								
								sender.sendMessage(ChatColor.DARK_RED + "No player name " + args[1] + " and no board section " + args.toString());
								return false;
								
							}
							
						}
						
					}
					
				}
				
			} else {
				
				if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayerExact(args[1]))) {
					
					Player p = Bukkit.getPlayerExact(args[1]);
					List<String> argsL = new ArrayList<String>(Arrays.asList(args));
					
					argsL.remove(0);
					argsL.remove(0);
					
					for (String str : argsL) {
						
						try {
						
							pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), BoardSection.valueOf(str.toUpperCase())));
						
						} catch (IllegalArgumentException e) {
							
							sender.sendMessage(ChatColor.DARK_RED + "No board section " + str);
							return false;
							
						}
							
					}
					
				} else {
					
					for (Player p : Bukkit.getServer().getOnlinePlayers()) {
						
						List<String> argsL = new ArrayList<String>(Arrays.asList(args));
						
						argsL.remove(0);
						
						for (String str : argsL) {
							
							try {
								
								pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), BoardSection.valueOf(str)));
								
							} catch (IllegalArgumentException e) {
								
								sender.sendMessage(ChatColor.DARK_RED + "No player " + args[1] + " and no section " + str);
								
							}
							
						}
						
					}
					
				}
				
			}
		
		} else if (args[0].equalsIgnoreCase("hide")) {
			
			if (args.length == 1) {
				
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					
					p.setScoreboard(sbm.getMainScoreboard());
					
				}
				
			} else if (args.length == 2) {
				
				Player p = Bukkit.getPlayerExact(args[2]);
				
				p.setScoreboard(sbm.getMainScoreboard());
				
			} else {
				
				sender.sendMessage(ChatColor.DARK_RED + "Incorrect Usage: /nb <update | hide> [player | section | board] [section | board] [section | board]...");
				return false;
				
			}
			
		} else if (args[0].equalsIgnoreCase("show")) {
			
			if (args.length == 1) {
				
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					
					Object inst = null;
					
					try {
						
						inst = NekoBoard.plugin.worldBoards.get(p.getWorld().getName()).newInstance();
						
					} catch (InstantiationException | IllegalAccessException e) {
						
						e.printStackTrace();
						
					}
					
					Scoreboard sb = sbm.getNewScoreboard();
					Objective obj = sb.registerNewObjective(inst.getClass().getSimpleName(), "dummy");
					
					obj.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "NekoMC Networks");
					obj.setDisplaySlot(DisplaySlot.SIDEBAR);
					p.setScoreboard(sb);
					
					Method sp = null;
					
					try {
						
						sp = NekoBoard.plugin.worldBoards.get(p.getWorld().getName()).getMethod("showPlayer", new Class[] {UUID.class, Objective.class});
						
					} catch (NoSuchMethodException | SecurityException e) {
						
						e.printStackTrace();
						
					}
					
					if (sp != null) {
							
						try {
							
							sp.invoke(inst, p.getPlayer().getUniqueId(), obj);
							
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							
							e.printStackTrace();
							
						}
						
					}
					
				}
				
			} else if (args.length == 2) {
				
				if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayerExact(args[1]))) {
					
					Player p = Bukkit.getPlayerExact(args[1]);
					Object inst = null;
						
					try {
						
						inst = NekoBoard.plugin.worldBoards.get(p.getWorld().getName()).newInstance();
						
					} catch (InstantiationException | IllegalAccessException e) {
						
						e.printStackTrace();
						
					}
					
					Scoreboard sb = sbm.getNewScoreboard();
					Objective obj = sb.registerNewObjective(inst.getClass().getSimpleName(), "dummy");
					
					obj.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "NekoMC Networks");
					obj.setDisplaySlot(DisplaySlot.SIDEBAR);
					p.setScoreboard(sb);
					
					Method sp = null;
					
					try {
						
						sp = NekoBoard.plugin.worldBoards.get(p.getWorld().getName()).getMethod("showPlayer", new Class[] {UUID.class, Objective.class});
					
					} catch (NoSuchMethodException | SecurityException e) {

						e.printStackTrace();
						
					}
					
					if (sp != null) {
							
						try {
							
							sp.invoke(inst, p.getUniqueId(), obj);
							
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							
							e.printStackTrace();
							
						}
						
					}
					
				} else {
					
					for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					
						@SuppressWarnings("rawtypes")
						Class c = null;
								
						try {
							
							c = Class.forName("com.nekomc.nekoBoard.boards." + BoardType.valueOf(args[1].toUpperCase()).getType());
							
						} catch (IllegalArgumentException e) {
							
							sender.sendMessage(ChatColor.DARK_RED + "No board type " + args[1]);
							return false;
							
						} catch (ClassNotFoundException e) {

							e.printStackTrace();
							
						}
						
						Object inst = null;
						
						try {
							
							inst = c.newInstance();
							
						} catch (InstantiationException | IllegalAccessException e) {
							
							e.printStackTrace();
							
						}
						
						Scoreboard sb = sbm.getNewScoreboard();
						Objective obj = sb.registerNewObjective(c.getSimpleName(), "dummy");
						
						obj.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "NekoMC Networks");
						obj.setDisplaySlot(DisplaySlot.SIDEBAR);
					
						Method sp = null;
						
						try {
							
							sp = c.getMethod("showPlayer", new Class[] {UUID.class, Objective.class});
						
						} catch (NoSuchMethodException | SecurityException e) {

							e.printStackTrace();
							
						}
						
						if (sp != null) {
								
							try {
								
								sp.invoke(inst, p.getUniqueId(), obj);
							
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								
								e.printStackTrace();
								
							}
							
						}
						
					}
					
				}
				
			} else if (args.length == 3) {
				
				if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayerExact(args[1]))) {
					
					Player p = Bukkit.getPlayerExact(args[1]);
					@SuppressWarnings("rawtypes")
					Class c = null;
					Object inst = null;
					
					try {
						
						c = Class.forName("com.nekomc.nekoBoard.boards." + BoardType.valueOf(args[2].toUpperCase()).getType());
						
					} catch (IllegalArgumentException e) {
						
						sender.sendMessage(ChatColor.DARK_RED + "No board type " + args[2]);
						return false;
						
					} catch (ClassNotFoundException e) {
						
						e.printStackTrace();
						
					}
					
					try {
						
						inst = c.newInstance();
						
					} catch (InstantiationException | IllegalAccessException e1) {

						e1.printStackTrace();
						
					}
					
					Scoreboard sb = sbm.getNewScoreboard();
					Objective obj = sb.registerNewObjective(c.getSimpleName(), "dummy");
					
					obj.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "NekoMC Networks");
					obj.setDisplaySlot(DisplaySlot.SIDEBAR);
					p.setScoreboard(sb);
					
					Method sp = null;
					
					try {
						
						sp = c.getMethod("showPlayer", new Class[] {UUID.class, Objective.class});
						
					} catch (NoSuchMethodException | SecurityException e) {

						e.printStackTrace();
						
					}
					
					if (sp != null) {
							
						try {
							
							sp.invoke(inst, p.getUniqueId(), obj);
							
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

							e.printStackTrace();
							
						}
						
					}
					
				} else {
					
					sender.sendMessage(ChatColor.DARK_RED + "Incorrect Usage: /nb <update | hide> [player | section | board] [section | board] [section | board]...");
					return false;
					
				}
				
			} else {
				
				sender.sendMessage(ChatColor.DARK_RED + "Incorrect Usage: /nb <update | hide> [player | section | board] [section | board] [section | board]...");
				return false;
				
			}
			
		} else {
			
			sender.sendMessage(ChatColor.DARK_RED + "Incorrect Usage: /nb <update | hide> [player | section | board] [section | board] [section | board]...");
			return false;
			
		}
		
		sender.sendMessage(ChatColor.GREEN + "Success!");
		return true;
	}

}
