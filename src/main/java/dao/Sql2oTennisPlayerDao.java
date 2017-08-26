package dao;

import models.Country;
import models.GrandSlam;
import models.TennisPlayer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oTennisPlayerDao implements TennisPlayerDao {
    private final Sql2o sql2o;

    public Sql2oTennisPlayerDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(TennisPlayer player) {
        String sql = "INSERT INTO players (name, gender, age, ranking, points, tournamentsPlayed) values (:name, :gender, :age, :ranking, :points, :tournamentsPlayed)";
        try (Connection conn = sql2o.open()) {
           int id = (int) conn.createQuery(sql)
                    .addParameter("name", player.getName())
                    .addParameter("gender", player.getGender())
                    .addParameter("age", player.getAge())
                    .addParameter("ranking", player.getRanking())
                    .addParameter("points", player.getPoints())
                    .addParameter("tournamentsPlayed", player.getTournamentsPlayed())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("GENDER", "gender")
                    .addColumnMapping("AGE", "age")
                    .addColumnMapping("RANKING", "ranking")
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
    public void addCountryToPlayer(TennisPlayer player, Country country) {
        String sql = "INSERT INTO countries_players (country, player_id) VALUES (:country, :player_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("country", country.getName())
                    .addParameter("player_id", player.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addPlayerToTournament(TennisPlayer player, GrandSlam grandSlam) {
        String sql = "INSERT INTO players_grand_slam (player_id, tournament_id) VALUES (:player_id, :tournament_id)";
        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("player_id", player.getId())
                    .addParameter("tournament_id", grandSlam.getId())
                    .executeUpdate();
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
        List<GrandSlam> slams = new ArrayList<>();
        String joinQuery = "SELECT tournament_id FROM players_grand_slam WHERE player_id = :player_id";
        try (Connection conn = sql2o.open()) {
            List<Integer> allTournId = conn.createQuery(joinQuery)
                    .addParameter("player_id", playerId)
                    .executeAndFetch(Integer.class);
            for (Integer tournId : allTournId) {
                String tournQuery = "SELECT * FROM grand_slam WHERE id = :tournament_id";
                slams.add(
                        conn.createQuery(tournQuery)
                        .addParameter("tournament_id", tournId)
                        .executeAndFetchFirst(GrandSlam.class));
            }
        } catch (Sql2oException ex) {
            ex.printStackTrace();

        }
        return slams;
    }

    @Override
    public List<TennisPlayer> getAllPlayersForCountry(String country) {
        List<TennisPlayer> players = new ArrayList<>();
        String joinQuery = "SELECT player_id FROM countries_players WHERE country = :country";

        try(Connection conn = sql2o.open()) {
            List<Integer> allPlayersId = conn.createQuery(joinQuery)
                    .addParameter("country", country)
                    .executeAndFetch(Integer.class);
            for (Integer playerId : allPlayersId) {
                String playersQuery = "SELECT * FROM players WHERE id = :player_id";
                players.add(
                        conn.createQuery(playersQuery)
                                .addParameter("player_id", playerId)
                                .executeAndFetchFirst(TennisPlayer.class));
            }
        } catch (Sql2oException ex) {
            ex.printStackTrace();
        }
        return players;
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
        String sql = "DELETE FROM players";
        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .executeUpdate();
        }
        catch (Sql2oException ex) {
            ex.printStackTrace();
        }
    }
}
