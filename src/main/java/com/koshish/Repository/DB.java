package com.koshish.Repository;

import java.sql.*;

public class DB {

	Connection connection = null;
	
	public DB() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/KoKo_DBOne", "root", "sqlPassword");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
