package me.thedreps.nekochat;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.huskehhh.mysql.mysql.*;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.thedreps.nekochat.commands.Nickname;
import me.thedreps.nekochat.commands.Rules;
import me.thedreps.nekochat.events.OnChat;
import me.thedreps.nekochat.events.PlayerJoin;
import me.thedreps.nekochat.events.PlayerQuit;

public class NekoChat extends JavaPlugin{
	
	public static NekoChat plugin;
	
	MySQL MySQL = new MySQL("nekomc.com", "3306", "nekomcco_nicknames", "nekomcco_nickn", "tv&$hyBT!iyg");
	Connection c = null;
	
	public void onEnable(){
		registerConfig();
		registerEvents();
		registerCommands();
		
		try {
			c = MySQL.openConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		plugin = this;
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
	    createFiles();
	  }
	
	private void registerCommands(){
	    getCommand("rules").setExecutor(new Rules());
	    getCommand("nickname").setExecutor(new Nickname());
	  }
	
	
	
	
	
	private File nicknamef;
    private FileConfiguration nickname;


    public FileConfiguration getNicknameConfig() {
        return this.nickname;
    }
    
    public void saveNicknames(){
    	try {
			nickname.save(nicknamef);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private void createFiles() {

        nicknamef = new File(getDataFolder(), "nickname.yml");

        if (!nicknamef.exists()) {
            nicknamef.getParentFile().mkdirs();
            saveResource("nickname.yml", false);
        }


        nickname = new YamlConfiguration();
        
        try {
            nickname.load(nicknamef);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
    }

}
