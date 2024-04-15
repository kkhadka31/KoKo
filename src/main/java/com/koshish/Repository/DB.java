package com.koshish.Repository;

import com.koshish.Service.SecretsDecryptor;
import jakarta.annotation.PostConstruct;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.sql.*;

@Component
public class DB {

	@Inject
	private Environment env;

	private Connection connection;

	@Inject
	private SecretsDecryptor secretsDecryptor;

	@PostConstruct
	public void init() {
		String url = env.getProperty("spring.datasource.url");
		String username = env.getProperty("spring.datasource.username");
		String password = env.getProperty("spring.datasource.password");
		String secretKey = env.getProperty("jasypt.encryptor.password");
		String encryptAlgo = env.getProperty("jasypt.encryptor.algorithm");

		try {
			password = secretsDecryptor.decrypt(secretKey, password, encryptAlgo);
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