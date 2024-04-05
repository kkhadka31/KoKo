CREATE TABLE KoKo_DBOne.USER_CREDENTIALS (
                                  userId INT AUTO_INCREMENT PRIMARY KEY,
                                  userName VARCHAR(255) NOT NULL UNIQUE,
                                  password VARCHAR(255) NOT NULL,
                                  salt BINARY(16) NOT NULL
);