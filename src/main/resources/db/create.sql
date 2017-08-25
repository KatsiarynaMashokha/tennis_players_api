SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS tennis_players (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    gender CHAR,
    age INTEGER,
    ranking INTEGER,
    country VARCHAR,
    points INTEGER,
    tournaments_played INTEGER
);

CREATE TABLE IF NOT EXISTS grand_clam_tournaments (
    id int PRIMARY KEY auto_increment,
    title VARCHAR,
    date VARCHAR
);

CREATE TABLE IF NOT EXISTS tennis_players_won_tournaments (
    id int PRIMARY KEY auto_increment,
    player_id INTEGER,
    tournament_id INTEGER
);

