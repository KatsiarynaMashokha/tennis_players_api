package dao;

import models.Country;
import models.GrandSlam;
import models.TennisPlayer;

import java.util.List;

public interface TennisPlayerDao {


    // create
    void add(TennisPlayer player);
    void addCountryToPlayer(TennisPlayer player, Country country);

    // read
    List<TennisPlayer> getAllPlayers();
    TennisPlayer findById(int playerId);
    List<GrandSlam> getAllTournamentsWonByPlayer(int playerId);
    List<TennisPlayer> getAllPlayersForCountry(String country);

    // update
    void update(int playerId, int age, int ranking, int points, int tourn_played);

    // delete
    void deletePlayer(int id);
    void deleteAllPlayers();
}
