package dao;

import models.TennisPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class Sql2oTennisPlayerDaoTest {
    private Sql2oTennisPlayerDao tennisPlayerDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        tennisPlayerDao = new Sql2oTennisPlayerDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    // helper to create a new player
    TennisPlayer createPlayerOne() {
        return new TennisPlayer("Roger Federer", 'M', 36, 3, "Switzerland", 7145, 16);
    }

    TennisPlayer createPlayerTwo() {
        return new TennisPlayer("Rafael Nadal", 'M', 31, 1, "Spain", 7645, 16);
    }


    @Test
    public void add() throws Exception {
        TennisPlayer player = createPlayerOne();
        tennisPlayerDao.add(player);
        assertEquals(1, player.getId());
        assertEquals("Roger Federer", player.getName());
        assertEquals(7145, player.getPoints());
    }

    @Test
    public void getAllPlayers() throws Exception {
    }

    @Test
    public void findById() throws Exception {
    }

    @Test
    public void getAllTounamentsWonByPlayer() throws Exception {
    }

    @Test
    public void getAllPlayersForCountry() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void deletePlayer() throws Exception {
    }

    @Test
    public void deleteAllPlayers() throws Exception {
    }

}