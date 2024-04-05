CREATE TABLE KoKo_DBOne.PERSON (
                        userId INT PRIMARY KEY AUTO_INCREMENT,
                        userName VARCHAR(255) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL,
                        firstName VARCHAR(255),
                        secondName VARCHAR(255),
                        fullName VARCHAR(255),
                        email VARCHAR(255),
                        gender VARCHAR(255),
                        phoneNumber VARCHAR(255),
                        role VARCHAR(255) NOT NULL
);
ALTER TABLE PERSON ADD FOREIGN KEY (userId) REFERENCES USER_CREDENTIALS (userId) ON DELETE CASCADE;