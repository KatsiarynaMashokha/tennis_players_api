package dao;

import models.GrandSlam;
import models.TennisPlayer;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oGrandSlamDao implements GrandSlamDao {
    private final Sql2o sql2o;

    public Sql2oGrandSlamDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(GrandSlam grandSlam) {

    }

    @Override
    public List<GrandSlam> getAllTornaments() {
        return null;
    }

    @Override
    public GrandSlam findById(int tournId) {
        return null;
    }

    @Override
    public List<TennisPlayer> getAllPlayersWonTheTournament(int tournId) {
        return null;
    }

    @Override
    public void update(int tournId, String date) {

    }

    @Override
    public void deleteTourn(int tournId) {

    }

    @Override
    public void deleteAllTourn() {

    }
}
