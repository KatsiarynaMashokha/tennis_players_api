package dao;

import models.GrandSlam;
import models.TennisPlayer;

import java.util.List;

public interface TennisPlayerDao {

    // create
    void add(TennisPlayer player);

    // read
    List<TennisPlayer> getAllPlayers();
    TennisPlayer findById(int playerId);
    List<GrandSlam> getAllTounamentsWonByPlayer(int playerId);

    // update
    void update(int playerId, int age, int ranking, int points, int tourn_played);

    // delete
    void deletePlayer(int id);
    void deleteAllPlayers();
}
