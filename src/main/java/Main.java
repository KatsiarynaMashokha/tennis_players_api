import com.google.gson.Gson;
import dao.Sql2oGrandSlamDao;
import dao.Sql2oTennisPlayerDao;
import exceptions.ApiException;
import models.Country;
import models.GrandSlam;
import models.TennisPlayer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // add a country to a player
        post("/players/:id/country/:countryName", "application/json", (request, response) -> {
            int playerId = Integer.parseInt(request.params("id"));
            String countryName = request.params("countryName");
            TennisPlayer player = tennisPlayerDao.findById(playerId);
            if (player == null) {
                throw new ApiException(String.format("No player with id = %d found", playerId), 404);
            }
            Country country = new Country(countryName);
            tennisPlayerDao.addCountryToPlayer(player, country);
            response.status(201);
            return gson.toJson(country);
        });

        // add a player to a tournament
        post("/players/:id/tournaments/:tournamentId", "application/json", (request, response) -> {
            int playerId = Integer.parseInt(request.params("id"));
            int tournmId = Integer.parseInt(request.params("tournamentId"));
            TennisPlayer player = tennisPlayerDao.findById(playerId);
            if (player == null) {
                throw new ApiException(String.format("No player with id = %d found", playerId), 404);
            }
            GrandSlam tournament = grandSlamDao.findById(tournmId);
            if (tournament == null) {
                throw new ApiException(String.format("No tournament with id = %d found", tournmId), 404);
            }
            tennisPlayerDao.addPlayerToTournament(player, tournament);
            response.status(201);
            return gson.toJson(player);
        });

        // READ
        // all players
        get("/players", "application/json", (request, response) -> {
            List<TennisPlayer> players = tennisPlayerDao.getAllPlayers();
            return gson.toJson(players);
        });

        // single player
        get("/players/:id", "application/json", (request, response) -> {
            int playerId = Integer.parseInt(request.params("id"));
            TennisPlayer player = tennisPlayerDao.findById(playerId);
            if (player == null) {
                throw new ApiException(String.format("No player with id = %d found", playerId), 404);
            }
            return gson.toJson(player);
        });

        // get all tournaments won by a player
        get("/players/:id/tournaments", "application/json", (request, response) -> {
            int playerId = Integer.parseInt(request.params("id"));
            TennisPlayer player = tennisPlayerDao.findById(playerId);
            if (player == null) {
                throw new ApiException(String.format("No player with id = %d found", playerId), 404);
            }
            List<GrandSlam> tournaments = tennisPlayerDao.getAllTournamentsWonByPlayer(playerId);
            return gson.toJson(tournaments);
        });


        // get all players for a country
        get("/players/country/:countryName", "application/json", (request, response) -> {
            String country = request.params("countryName");
            List<TennisPlayer> players = tennisPlayerDao.getAllPlayersForCountry(country);
            return gson.toJson(players);
        });

        // UPDATE
        post("/players/:id/update", "application/json", (request, response) -> {
            int playerId = Integer.parseInt(request.params("id"));
            TennisPlayer player = gson.fromJson(request.body(), TennisPlayer.class);
            if (player == null) {
                throw new ApiException(String.format("No player with id = %d found", playerId), 404);
            }
            tennisPlayerDao.update(playerId, player.getAge(), player.getRanking(), player.getPoints(), player.getTournamentsPlayed());
            response.status(201);
            return gson.toJson(player);
        });

        // DELETE
        // single player
        get("/players/:id/delete", "application/json", (request, response) -> {
            int playerId = Integer.parseInt(request.params("id"));
            TennisPlayer player = tennisPlayerDao.findById(playerId);
            if (player == null) {
                throw new ApiException(String.format("No player with id = %d found", playerId), 404);
            }
            tennisPlayerDao.deletePlayer(playerId);
            return gson.toJson(playerId);
        });

        // all players
        get("/players/delete", "application/json", (request, response) -> {
            tennisPlayerDao.deleteAllPlayers();
            return gson.toJson(tennisPlayerDao.getAllPlayers().size());
        });


        // TOURNAMENTS
        // CREATE
        post("/tournaments/new", "application/json", (request, response) -> {
            GrandSlam tournament = gson.fromJson(request.body(), GrandSlam.class);
            grandSlamDao.add(tournament);
            response.status(201);
            return gson.toJson(tournament);
        });

        // add a tournament to a player
        post("/tournaments/:tournId/players/id", "application/json", (request, response) -> {
            int tournId = Integer.parseInt(request.params("tournId"));
            GrandSlam tournament = grandSlamDao.findById(tournId);
            int playerId = Integer.parseInt(request.params("id"));
            TennisPlayer player = tennisPlayerDao.findById(playerId);
            grandSlamDao.addTournamentToPlayer(tournament, player);
            return gson.toJson(tournament);
        });

        // READ
        // all tournaments
        get("/tournaments", "application/json", (request, response) -> {
            List<GrandSlam> tournaments = grandSlamDao.getAllTornaments();
            return gson.toJson(tournaments);
        });

        // single tournament
        get("/tournaments/:tournId", "application/json", (request, response) -> {
            int tournId = Integer.parseInt(request.params("tournId"));
            GrandSlam tournament = grandSlamDao.findById(tournId);
            if (tournament == null) {
                throw new ApiException(String.format("No tournament with id = %d found", tournId), 404);
            }
            return gson.toJson(tournament);
        });

        // get all players won the tournament
        get("/tournaments/:tournId/players", "application/json", (request, response) -> {
            int tournId = Integer.parseInt(request.params("tournId"));
            GrandSlam tournament = grandSlamDao.findById(tournId);
            if (tournament == null) {
                throw new ApiException(String.format("No tournament with id = %d found", tournId), 404);
            }
            List<TennisPlayer> players = grandSlamDao.getAllPlayersWonTheTournament(tournId);
            return gson.toJson(players);
        });

        //DELETE
        // single tournament
        get("/tournaments/:tournId/delete", "application/json", (request, response) -> {
            int tournId = Integer.parseInt(request.params("tournId"));
            GrandSlam tournament = grandSlamDao.findById(tournId);
            if (tournament == null) {
                throw new ApiException(String.format("No tournament with id = %d found", tournId), 404);
            }
            grandSlamDao.deleteTourn(tournId);
            return gson.toJson(tournId);
        });

        // all tournaments
        get("/tournaments/delete", "application/json", (request, response) -> {
            grandSlamDao.deleteAllTourn();
            return gson.toJson(grandSlamDao.getAllTornaments().size());
        });

        // exception
        exception(ApiException.class, (exception, request, response) -> {
            ApiException err = (ApiException) exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            response.type("application/json");
            response.status(err.getStatusCode());
            response.body(gson.toJson(jsonMap));
        });
    }
}
