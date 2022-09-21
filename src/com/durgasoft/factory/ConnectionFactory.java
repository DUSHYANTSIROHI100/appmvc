package com.durgasoft.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static Connection con;
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "xe", "123");
		    System.out.println("conection craeted successfully");
			} catch (Exception e) {
			e.printStackTrace();
			}
			}
	public static Connection getConnection() {
		return con;
	}
	public static void cleanUp() {
		try {
			con.close();
			 System.out.println("conection Destroy successfully");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	}



