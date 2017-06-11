package me.thedreps.nekochat;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.thedreps.nekochat.commands.NicknameCmd;
import me.thedreps.nekochat.commands.RulesCmd;
import me.thedreps.nekochat.events.OnChat;
import me.thedreps.nekochat.events.PlayerJoin;
import me.thedreps.nekochat.events.PlayerQuit;

public class NekoChat extends JavaPlugin{
	
	public static NekoChat plugin;
	
	public void onEnable(){
		registerConfig();
		registerEvents();
		registerCommands();
		
		
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
	    getCommand("rules").setExecutor(new RulesCmd());
	    getCommand("nickname").setExecutor(new NicknameCmd());
	  }
	
	
	
	
	//NAMES CONFIG
	
	private File namesf;
    private FileConfiguration names;


    public FileConfiguration getNamesConfig() {
        return this.names;
    }
    
    public void saveNames(){
    	try {
			names.save(namesf);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private void createFiles() {

        namesf = new File(getDataFolder(), "names.yml");

        if (!namesf.exists()) {
            namesf.getParentFile().mkdirs();
            saveResource("names.yml", false);
        }


        names = new YamlConfiguration();
        
        try {
            names.load(namesf);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
    }
	

}
