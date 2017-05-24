package me.thedreps.nekochat;

import java.sql.Connection;

import com.huskehhh.mysql.mysql.MySQL;

public class Nicknames {
	
	MySQL MySQL = new MySQL(NekoChat, "nekomc.com", "3306", "nekomcco_nicknames", "nekomcco_nickn", "tv&$hyBT!iyg");
	Connection c = null;
	
	c = MySQL.open();

}
