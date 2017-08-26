package dao;

import models.GrandSlam;
import models.TennisPlayer;

import java.util.List;

public interface GrandSlamDao {

    // create
    void add(GrandSlam grandSlam);
    void addTournamentToPlayer(GrandSlam grandSlam, TennisPlayer player);


    // read
    List<GrandSlam> getAllTornaments();
    GrandSlam findById(int tournId);
    List<TennisPlayer> getAllPlayersWonTheTournament(int tournId);


    // delete
    void deleteTourn(int tournId);
    void deleteAllTourn();
}
