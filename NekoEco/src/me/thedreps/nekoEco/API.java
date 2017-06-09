package me.thedreps.nekoEco;

import java.sql.ResultSet;
import java.sql.SQLException;

public class API {

	public Integer getBal(String uuid) {

		try {
			ResultSet res = NekoEco.c.createStatement()
					.executeQuery("SELECT * FROM `Eco` WHERE `UUID`= '" + uuid + "'");

			if (!res.next()) {
				setBal(uuid, 0);
				return 0;

			} else {
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

			ResultSet res = NekoEco.c.createStatement()
					.executeQuery("SELECT * FROM `Eco` WHERE `UUID`= '" + uuid + "'");
			if (!res.next()) {
				NekoEco.c.createStatement()
						.executeUpdate("INSERT INTO `Eco` (`UUID`,`BALANCE`) VALUES ('" + uuid + "','" + amount + "')");
			} else {
				NekoEco.c.createStatement()
						.executeUpdate("UPDATE `Eco` SET `BALANCE`='" + amount + "' WHERE `UUID`='" + uuid + "'");
			}

		} catch (SQLException sql) {
			sql.printStackTrace();
			NekoEco.plugin.getLogger().warning("[NekoEco] SQL error!");
		}
	}

	//

	public void addBal(String uuid, int amount) {

		try {

			amount = getBal(uuid) + amount;

			ResultSet res = NekoEco.c.createStatement()
					.executeQuery("SELECT * FROM `Eco` WHERE `UUID`= '" + uuid + "'");
			if (!res.next()) {
				NekoEco.c.createStatement()
						.executeUpdate("INSERT INTO `Eco` (`UUID`,`BALANCE`) VALUES ('" + uuid + "','" + amount + "')");
			} else {
				NekoEco.c.createStatement()
						.executeUpdate("UPDATE `Eco` SET `BALANCE`='" + amount + "' WHERE `UUID`='" + uuid + "'");
			}

		} catch (SQLException sql) {
			sql.printStackTrace();
			NekoEco.plugin.getLogger().warning("[NekoEco] SQL error!");
		}
	}

	//

	public void takeBal(String uuid, int amount) {

		try {

			amount = getBal(uuid) - amount;

			ResultSet res = NekoEco.c.createStatement()
					.executeQuery("SELECT * FROM `Eco` WHERE `UUID`= '" + uuid + "'");
			if (!res.next()) {
				NekoEco.c.createStatement()
						.executeUpdate("INSERT INTO `Eco` (`UUID`,`BALANCE`) VALUES ('" + uuid + "','" + amount + "')");
			} else {
				NekoEco.c.createStatement()
						.executeUpdate("UPDATE `Eco` SET `BALANCE`='" + amount + "' WHERE `UUID`='" + uuid + "'");
			}

		} catch (SQLException sql) {
			sql.printStackTrace();
			NekoEco.plugin.getLogger().warning("[NekoEco] SQL error!");
		}
	}

}
