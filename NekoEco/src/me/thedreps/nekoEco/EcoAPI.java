package me.thedreps.nekoEco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.thedreps.nekoEco.sql.MySQL;
import me.thedreps.nekoEco.sql.SQLD;

public class EcoAPI {
	
	public static Connection c;
	MySQL SQL = new MySQL(SQLD.host, SQLD.port, SQLD.db, SQLD.user, SQLD.pw);

	public Integer getBal(String uuid) {

		try {
			c = SQL.open();
			ResultSet res = c.createStatement()
					.executeQuery("SELECT * FROM `Eco` WHERE `UUID`= '" + uuid + "'");

			if (!res.next()) {
				setBal(uuid, 0);
				c = MySQL.closeConnection(c);
				return 0;

			} else {
				c = MySQL.closeConnection(c);
				return res.getInt("BALANCE");
			}

		} catch (SQLException sql) {
			sql.printStackTrace();
		}

		NekoEco.plugin.getLogger().warning("[NekoEco] SQL error!");
		return 0;

	}

	//

	public void setBal(String uuid, int amount) {

		try {

			c = SQL.open();
			ResultSet res = c.createStatement()
					.executeQuery("SELECT * FROM `Eco` WHERE `UUID`= '" + uuid + "'");
			if (!res.next()) {
				c.createStatement()
						.executeUpdate("INSERT INTO `Eco` (`UUID`,`BALANCE`) VALUES ('" + uuid + "','" + amount + "')");
				c = MySQL.closeConnection(c);
			} else {
				c.createStatement()
						.executeUpdate("UPDATE `Eco` SET `BALANCE`='" + amount + "' WHERE `UUID`='" + uuid + "'");
				c = MySQL.closeConnection(c);
			}

		} catch (SQLException sql) {
			sql.printStackTrace();
			NekoEco.plugin.getLogger().warning("[NekoEco] SQL error!");
		}
	}

	//

	public void addBal(String uuid, int amount) {

		try {

			c = SQL.open();
			amount = getBal(uuid) + amount;

			ResultSet res = c.createStatement()
					.executeQuery("SELECT * FROM `Eco` WHERE `UUID`= '" + uuid + "'");
			if (!res.next()) {
				c.createStatement()
						.executeUpdate("INSERT INTO `Eco` (`UUID`,`BALANCE`) VALUES ('" + uuid + "','" + amount + "')");
				c = MySQL.closeConnection(c);
			} else {
				c.createStatement()
						.executeUpdate("UPDATE `Eco` SET `BALANCE`='" + amount + "' WHERE `UUID`='" + uuid + "'");
				c = MySQL.closeConnection(c);
			}

		} catch (SQLException sql) {
			sql.printStackTrace();
			NekoEco.plugin.getLogger().warning("[NekoEco] SQL error!");
		}
	}

	//

	public void takeBal(String uuid, int amount) {

		try {

			c = SQL.open();
			amount = getBal(uuid) - amount;

			ResultSet res = c.createStatement()
					.executeQuery("SELECT * FROM `Eco` WHERE `UUID`= '" + uuid + "'");
			if (!res.next()) {
				c.createStatement()
						.executeUpdate("INSERT INTO `Eco` (`UUID`,`BALANCE`) VALUES ('" + uuid + "','" + amount + "')");
				c = MySQL.closeConnection(c);
			} else {
				c.createStatement()
						.executeUpdate("UPDATE `Eco` SET `BALANCE`='" + amount + "' WHERE `UUID`='" + uuid + "'");
				c = MySQL.closeConnection(c);
			}

		} catch (SQLException sql) {
			sql.printStackTrace();
			NekoEco.plugin.getLogger().warning("[NekoEco] SQL error!");
		}
	}

	public boolean checkBal(String uuid, int amount){
		int current = getBal(uuid);
		
		if(amount <= current){
			return true;
		}else{
			return false;
		}
	}

}
