USE heroku_2e2d618df3e2303;

CREATE TABLE users (
	email VARCHAR(100) NOT NULL PRIMARY KEY,
	fullName VARCHAR(100) NOT NULL,
	pw VARCHAR(50) NOT NULL
);


CREATE TABLE posting (
	pfname VARCHAR(100) NOT NULL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    gender ENUM('female', 'male', 'other'),
    comments TEXT NOT NULL
);

SELECT * FROM users;

SELECT * FROM posting;

DESCRIBE users;

DESCRIBE posting;
