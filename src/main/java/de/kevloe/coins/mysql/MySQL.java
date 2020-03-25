package de.kevloe.coins.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import de.kevloe.coins.CoinAPI;
import de.kevloe.coins.util.Coins;

public class MySQL {

	public static String host = CoinAPI.getInstance().getMysqlconfig().getCfg().getString("MySQL.Host");
	public static String port = CoinAPI.getInstance().getMysqlconfig().getCfg().getString("MySQL.Port");
	public static String database = CoinAPI.getInstance().getMysqlconfig().getCfg().getString("MySQL.Database");
	public static String username = CoinAPI.getInstance().getMysqlconfig().getCfg().getString("MySQL.Username");
	public static String password = CoinAPI.getInstance().getMysqlconfig().getCfg().getString("MySQL.Password");
	public static Connection con;
	
	public static void connect(){
		if(CoinAPI.getInstance().getMysqlconfig().getFile().exists()){
			if(!isConnected()){
				try {
					con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" +database, username, password);
					System.out.println("[MySQL] Verbingung aufgebaut!");
					Coins.createTable();
				} catch (SQLException e) {
					System.out.println("---------- MySQL Fehler ---------");
					System.out.println("[MySQL] Die Daten sind falsch MySQL konnte sich nicht Verbinden!");
					System.out.println("---------- MySQL Fehler ---------");
				}
			}
		}else{
			System.out.println("---------- MySQL Fehler ---------");
			System.out.println("[MySQL] Die Config muss noch bearbeitet werden!");
			System.out.println("---------- MySQL Fehler ---------");
		}
	}
	public static void disconnect(){
		if(isConnected()){
			try {
				con.close();
				System.out.println("[MySQL] Verbingung unterbrochen!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static boolean isConnected(){
		return (con == null ? false : true);
	}
	public static Connection getConnection(){
		return con;
	}
}
