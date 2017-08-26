package dao;

import models.GrandSlam;
import models.TennisPlayer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oGrandSlamDao implements GrandSlamDao {
    private final Sql2o sql2o;

    public Sql2oGrandSlamDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(GrandSlam grandSlam) {
        String sql = "INSERT INTO grand_slam (title, date) VALUES (:title, :date)";
        try(Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql)
                    .addParameter("title", grandSlam.getTitle())
                    .addParameter("date", grandSlam.getDate())
                    .addColumnMapping("TITLE", "title")
                    .addColumnMapping("DATE", "date")
                    .executeUpdate()
                    .getKey();
            grandSlam.setId(id);
        } catch (Sql2oException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public List<GrandSlam> getAllTornaments() {
        String sql = "SELECT * FROM grand_slam";
        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .executeAndFetch(GrandSlam.class);
        }
    }

    @Override
    public GrandSlam findById(int tournId) {
        String sql = "SELECT * FROM grand_slam WHERE id = :id";
        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .addParameter("id", tournId)
                    .executeAndFetchFirst(GrandSlam.class);
        }
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
