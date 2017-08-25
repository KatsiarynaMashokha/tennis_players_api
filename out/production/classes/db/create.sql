SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS players (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    gender CHAR,
    age INTEGER,
    ranking INTEGER,
    country VARCHAR,
    points INTEGER,
    tournamentsPlayed INTEGER
);

CREATE TABLE IF NOT EXISTS grand_clam (
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
    country_id INTEGER,
    player_id INTEGER
);

