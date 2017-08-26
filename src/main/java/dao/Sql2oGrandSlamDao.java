package dao;

import models.GrandSlam;
import models.TennisPlayer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
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
    public void addTournamentToPlayer(GrandSlam grandSlam, TennisPlayer player) {
        String sql = "INSERT INTO players_grand_slam (player_id, tournament_id) VALUES (:player_id, :tournament_id)";
        try(Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("player_id", player.getId())
                    .addParameter("tournament_id", grandSlam.getId())
                    .executeUpdate();
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
        List<TennisPlayer> players = new ArrayList<>();
        String joinQuery = "SELECT player_id FROM players_grand_slam WHERE tournament_id = :tournament_id";
        try(Connection conn = sql2o.open()) {
            List<Integer> playersId = conn.createQuery(joinQuery)
                    .addParameter("tournament_id", tournId)
                    .executeAndFetch(Integer.class);
            for (Integer playerId : playersId) {
                String sql = "SELECT * FROM players WHERE id = :id";
                players.add(conn.createQuery(sql)
                        .addParameter("id", playerId)
                        .executeAndFetchFirst(TennisPlayer.class));
            }
        } catch (Sql2oException ex) {
            ex.printStackTrace();
        } return players;
    }


    @Override
    public void deleteTourn(int tournId) {
        String sql = "DELETE FROM grand_slam WHERE id = :id";
        String joinSql = "DELETE FROM players_grand_slam WHERE tournament_id = :tournament_id";
        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("id", tournId)
                    .executeUpdate();
            conn.createQuery(joinSql)
                    .addParameter("tournament_id", tournId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteAllTourn() {
        String sql = "DELETE FROM grand_slam";
        try(Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            ex.printStackTrace();
        }
    }
}
