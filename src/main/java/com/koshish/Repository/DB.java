package com.koshish.Repository;

import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.sql.*;

@Component
public class DB {

	@Inject
	private Environment env;

	private Connection connection;

	@PostConstruct
	public void init() {
		String url = env.getProperty("spring.datasource.url");
		String username = env.getProperty("spring.datasource.username");
		String password = env.getProperty("spring.datasource.password");

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to connect to database", e);
		}
	}

	public Connection getConnection() {
		return this.connection;
	}
}

//@Component
//public class DB {
//
//	@Inject
//	public Environment env;
//	Connection connection = null;
//
//	String url;
//	String username;
//	String password;
//
//	@PostConstruct
//	public void init() {
//		url = env.getProperty("spring.datasource.url");
//		username = env.getProperty("spring.datasource.username");
//		password = env.getProperty("spring.datasource.password");
//	}
//
//	public DB() {
//		try {
////			connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"),
////					env.getProperty("spring.datasource.password"));
//			connection = DriverManager.getConnection(url, username, password);
//		} catch (SQLException e) {
//			throw new RuntimeException();
//		}
//	}
//
//}
/*
@Component
public class DB {

	@Inject
	private Environment env;

	private Connection connection;

	@PostConstruct
	public void init() {
		String url = env.getProperty("spring.datasource.url");
		String username = env.getProperty("spring.datasource.username");
		String password = env.getProperty("spring.datasource.password");

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to connect to database", e);
		}
	}

	public Connection getConnection() {
		return connection;
	}
}
 */