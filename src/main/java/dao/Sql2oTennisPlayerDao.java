package dao;

import models.GrandSlam;
import models.TennisPlayer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTennisPlayerDao implements TennisPlayerDao {
    private final Sql2o sql2o;

    public Sql2oTennisPlayerDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(TennisPlayer player) {
        String sql = "INSERT INTO players (name, gender, age, ranking, country, points, tournamentsPlayed) values (:name, :gender, :age, :ranking, :country, :points, :tournamentsPlayed)";
        try (Connection conn = sql2o.open()) {
           int id = (int) conn.createQuery(sql)
                    .addParameter("name", player.getName())
                    .addParameter("gender", player.getGender())
                    .addParameter("age", player.getAge())
                    .addParameter("ranking", player.getRanking())
                    .addParameter("country", player.getCountry())
                    .addParameter("points", player.getPoints())
                    .addParameter("tournamentsPlayed", player.getTournamentsPlayed())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("GENDER", "gender")
                    .addColumnMapping("AGE", "age")
                    .addColumnMapping("RANKING", "ranking")
                    .addColumnMapping("COUNTRY", "country")
                    .addColumnMapping("POINTS", "points")
                    .addColumnMapping("TOURNAMENTSPLAYED", "tournamentsPlayed")
                    .executeUpdate()
                    .getKey();
           player.setId(id);
        } catch (Sql2oException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<TennisPlayer> getAllPlayers() {
        //String sql = "DROP TABLE players";
        String sql = "SELECT * FROM players";
        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .executeAndFetch(TennisPlayer.class);
        }
    }

    @Override
    public TennisPlayer findById(int playerId) {
        String sql = "SELECT * FROM players WHERE id = :id";
        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .addParameter("id", playerId)
                    .executeAndFetchFirst(TennisPlayer.class);
        }
    }

    @Override
    public List<GrandSlam> getAllTournamentsWonByPlayer(int playerId) {
        return null;
    }

    @Override
    public List<TennisPlayer> getAllPlayersForCountry(int countryId) {
        return null;
    }

    @Override
    public void update(int playerId, int age, int ranking, int points, int tourn_played) {
        String sql = "UPDATE players SET (age, ranking, points, tournamentsPlayed) = (:age, :ranking, :points, :tournamentsPlayed) WHERE id = :id";
        try(Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("age", age)
                    .addParameter("ranking", ranking)
                    .addParameter("points", points)
                    .addParameter("tournamentsPlayed", tourn_played)
                    .addParameter("id", playerId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deletePlayer(int id) {
        String sql = "DELETE from players WHERE id = :id";
        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteAllPlayers() {

    }
}
