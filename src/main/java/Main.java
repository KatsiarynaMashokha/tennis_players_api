import com.google.gson.Gson;
import dao.Sql2oGrandSlamDao;
import dao.Sql2oTennisPlayerDao;
import dao.TennisPlayerDao;
import models.GrandSlam;
import models.TennisPlayer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oTennisPlayerDao tennisPlayerDao = new Sql2oTennisPlayerDao(sql2o);
        Sql2oGrandSlamDao grandSlamDao = new Sql2oGrandSlamDao(sql2o);
        Connection connection = sql2o.open();

        // filters
        after((req, res) -> {
            res.type("application/json");
        });

        // TENNIS PLAYERS
        // CREATE
        post("/players/new", "application/json", (request, response) -> {
            TennisPlayer player = gson.fromJson(request.body(), TennisPlayer.class);
            tennisPlayerDao.add(player);
            response.status(201);
            return gson.toJson(player);
        });

        //TODO
        // add a country to a players

        //TODO
        // add a player to tournament

        // READ
        // all players
        get("/players", "application/json", (request, response) -> {
            List<TennisPlayer> players = tennisPlayerDao.getAllPlayers();
            return gson.toJson(players);
        });

        // single player
        // TODO: handle an exception
        get("/players/:id", "application/json", (request, response) -> {
            int playerId = Integer.parseInt(request.params("id"));
            TennisPlayer player = tennisPlayerDao.findById(playerId);
            return gson.toJson(player);
        });

        //TODO
        // get all tournaments won by a player


        // TODO
        // get all players for a country

        // UPDATE
        post("/players/:id/update", "applciation/json", (request, response) -> {
            int playerId = Integer.parseInt(request.params("id"));
            TennisPlayer player = gson.fromJson(request.body(), TennisPlayer.class);
            tennisPlayerDao.update(playerId, player.getAge(), player.getRanking(), player.getPoints(), player.getTournamentsPlayed());
            response.status(201);
            return gson.toJson(player);
        });

        // DELETE
        // single player
        get("/players/:id/delete", "application/json", (request, response) -> {
            int playerId = Integer.parseInt(request.params("id"));
            tennisPlayerDao.deletePlayer(playerId);
            return gson.toJson(playerId);
        });

        // all players
        get("/players/delete", "application/json", (request, response) -> {
            tennisPlayerDao.deleteAllPlayers();
            return gson.toJson(tennisPlayerDao.getAllPlayers().size());
        });


        // TOURNAMENTS
        //CREATE
        post("/tournaments/new", "application/json", (request, response) -> {
            GrandSlam tournament = gson.fromJson(request.body(), GrandSlam.class);
            grandSlamDao.add(tournament);
            response.status(201);
            return gson.toJson(tournament);
        });

        // TODO:
        // add a tournament to a player

        //READ
        // all tournaments
        get("/tournaments", "application/json", (request, response) -> {
            List<GrandSlam> tournaments = grandSlamDao.getAllTornaments();
            return gson.toJson(tournaments);
        });

        // single tournament
        // TODO: handle an exception
        get("/tournaments/:tournId", "application/json", (request, response) -> {
            int tournId = Integer.parseInt(request.params("tournId"));
            GrandSlam tournament = grandSlamDao.findById(tournId);
            return gson.toJson(tournament);
        });

        // TODO:
        // get all players won the tournament

        //DELETE
        // single tournament
        get("/tournaments/:tournId/delete", "application/json", (request, response) -> {
            int tournId = Integer.parseInt(request.params("tournId"));
            grandSlamDao.deleteTourn(tournId);
            return gson.toJson(tournId);
        });

        // all tournaments
        get("/tournaments/delete", "application/json", (request, response) -> {
            grandSlamDao.deleteAllTourn();
            return gson.toJson(grandSlamDao.getAllTornaments().size());
        });
    }
}
