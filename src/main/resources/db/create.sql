SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS players (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    gender CHAR,
    age INTEGER,
    ranking INTEGER,
    points INTEGER,
    tournamentsPlayed INTEGER
);

CREATE TABLE IF NOT EXISTS grand_slam (
    id int PRIMARY KEY auto_increment,
    title VARCHAR,
    date VARCHAR
);

CREATE TABLE IF NOT EXISTS players_grand_slam (
    id int PRIMARY KEY auto_increment,
    player_id INTEGER,
    tournament_id INTEGER
);

CREATE TABLE IF NOT EXISTS countries_players (
    id int PRIMARY KEY auto_increment,
    country VARCHAR,
    player_id INTEGER
);

