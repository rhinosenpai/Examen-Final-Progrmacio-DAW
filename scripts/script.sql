-- TABLE
CREATE TABLE category (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   name text NOT NULL
);
CREATE TABLE player (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   name text NOT NULL,
   score INTEGER );
CREATE TABLE question (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	question text NOT NULL,
	answer_A TEXT NOT NUll,
  	answer_B TEXT NOT NUll,
  	answer_C TEXT NOT NUll,
  	correct_answer INTEGER NOT NULL,
  	value INTEGER Not NULL,
  	category INTEGER NOT NULL
);
CREATE TABLE sqlite_sequence(name,seq);
 
-- INDEX
 
-- TRIGGER
 
-- VIEW
 
