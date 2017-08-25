package dao;

import models.GrandSlam;
import models.TennisPlayer;

import java.util.List;

public interface GrandSlamDao {

    // create
    void add(GrandSlam grandSlam);

    // read
    List<GrandSlam> getAllTornaments();
    GrandSlam findById(int tournId);
    List<TennisPlayer> getAllPlayersWonTheTournament(int tournId);

    // update
    void update(int tournId, String date);

    // delete
    void deleteTourn(int tournId);
    void deleteAllTourn();
}
