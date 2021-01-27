

DROP TABLE IF EXISTS Users;

CREATE TABLE Users
(
    id LONG NOT NULL AUTO_INCREMENT,
    username  varchar(100) NOT NULL,
    email  varchar(100) NOT NULL,
    password varchar(10000) NOT NULL,
    isenabled boolean,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS Confirmation_Tokens;

CREATE TABLE Confirmation_Tokens
(
    token_id LONG NOT NULL AUTO_INCREMENT,
    confirmation_token  varchar(1000) NOT NULL,
    expire_date  varchar(100) NOT NULL,
    user_id LONG NOT NULL,
    PRIMARY KEY(token_id)
);

