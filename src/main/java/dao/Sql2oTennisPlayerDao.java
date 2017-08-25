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
        String sql = "INSERT INTO players (name, gender, age, ranking, country, points, tournaments_played) values (:name, :gender, :age, :ranking, :country, :points, :tournaments_played)";
        try (Connection conn = sql2o.open()) {
           int id = (int) conn.createQuery(sql)
                    .addParameter("name", player.getName())
                    .addParameter("gender", player.getGender())
                    .addParameter("age", player.getAge())
                    .addParameter("ranking", player.getRanking())
                    .addParameter("country", player.getCountry())
                    .addParameter("points", player.getPoints())
                    .addParameter("tournaments_played", player.getTournamentsPlayed())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("GENDER", "gender")
                    .addColumnMapping("AGE", "age")
                    .addColumnMapping("RANKING", "ranking")
                    .addColumnMapping("COUNTRY", "country")
                    .addColumnMapping("POINTS", "points")
                    .addColumnMapping("TOURNAMENTS_PLAYED", "tournaments_played")
                    .executeUpdate()
                    .getKey();
           player.setId(id);
        } catch (Sql2oException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<TennisPlayer> getAllPlayers() {
        return null;
    }

    @Override
    public TennisPlayer findById(int playerId) {
        return null;
    }

    @Override
    public List<GrandSlam> getAllTounamentsWonByPlayer(int playerId) {
        return null;
    }

    @Override
    public List<TennisPlayer> getAllPlayersForCountry(int countryId) {
        return null;
    }

    @Override
    public void update(int playerId, int age, int ranking, int points, int tourn_played) {

    }

    @Override
    public void deletePlayer(int id) {

    }

    @Override
    public void deleteAllPlayers() {

    }
}
