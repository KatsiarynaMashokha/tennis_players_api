import com.google.gson.Gson;
import dao.Sql2oGrandSlamDao;
import dao.Sql2oTennisPlayerDao;
import dao.TennisPlayerDao;
import models.TennisPlayer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
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
        after((req, res) ->{
            res.type("application/json");
        });

        // TENNIS PLAYERS
        // create
        post("/players/new", "application/json", (request, response) -> {
            TennisPlayer player = gson.fromJson(request.body(), TennisPlayer.class);
            tennisPlayerDao.add(player);
            response.status(201);
            return gson.toJson(player);
        });

        // read
        get("/players", "application/json", (request, response) -> {
            tennisPlayerDao.getAllPlayers();
            return gson.toJson(tennisPlayerDao.getAllPlayers().size());
        });

        // update
        post("/players/:id/update", "applciation/json", (request, response) -> {
            int playerId = Integer.parseInt(request.params("id"));
            TennisPlayer player = gson.fromJson(request.body(), TennisPlayer.class);
            tennisPlayerDao.update(playerId, player.getAge(), player.getRanking(), player.getPoints(), player.getTournamentsPlayed());
            response.status(201);
            return gson.toJson(player);
        });

        // delete
        get("/players/:id/delete", "application/json", (request, response) -> {
            int playerId = Integer.parseInt(request.params("id"));
            tennisPlayerDao.deletePlayer(playerId);
            return gson.toJson(playerId);
        });

        get("/players/delete", "application/json", (request, response) -> {
            tennisPlayerDao.deleteAllPlayers();
            return gson.toJson(tennisPlayerDao.getAllPlayers().size());
        });

    }
}
