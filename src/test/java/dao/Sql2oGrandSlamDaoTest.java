package dao;

import models.GrandSlam;
import models.TennisPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Sql2oGrandSlamDaoTest {
    private Sql2oGrandSlamDao grandSlamDao;
    private Sql2oTennisPlayerDao tennisPlayerDao;
    private Connection conn;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        grandSlamDao = new Sql2oGrandSlamDao(sql2o);
        tennisPlayerDao = new Sql2oTennisPlayerDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    // helpers
    GrandSlam createGrandSlamOne() {
        return new GrandSlam("US Open", "September");
    }

    GrandSlam createGrandSlamTwo() {
        return new GrandSlam("Australian Open", "January");
    }

    TennisPlayer createPlayerOne() {
        return new TennisPlayer("Roger Federer", "M", 36, 3, 7145, 16);
    }

    TennisPlayer createPlayerTwo() {
        return new TennisPlayer("Rafael Nadal", "M", 31, 1, 7645, 16);
    }

    @Test
    public void getAllTornaments() throws Exception {
        GrandSlam grandSlamOne = createGrandSlamOne();
        GrandSlam grandSlamTwo = createGrandSlamTwo();
        grandSlamDao.add(grandSlamOne);
        grandSlamDao.add(grandSlamTwo);
        GrandSlam[] slams = {grandSlamOne, grandSlamTwo};
        assertEquals(2, grandSlamDao.getAllTornaments().size());
        assertEquals(Arrays.asList(slams), grandSlamDao.getAllTornaments());
    }

    @Test
    public void findById() throws Exception {
        GrandSlam grandSlamOne = createGrandSlamOne();
        GrandSlam grandSlamTwo = createGrandSlamTwo();
        grandSlamDao.add(grandSlamOne);
        grandSlamDao.add(grandSlamTwo);
        GrandSlam foundTournament = grandSlamDao.findById(grandSlamOne.getId());
        assertEquals(grandSlamOne, foundTournament);
    }

    @Test
    public void getAllPlayersWonTheTournament() throws Exception {
        GrandSlam grandSlamOne = createGrandSlamOne();
        grandSlamDao.add(grandSlamOne);
        TennisPlayer playerOne = createPlayerOne();
        tennisPlayerDao.add(playerOne);
        TennisPlayer playerTwo = createPlayerTwo();
        tennisPlayerDao.add(playerTwo);
        grandSlamDao.addTournamentToPlayer(grandSlamOne, playerOne);
        grandSlamDao.addTournamentToPlayer(grandSlamOne, playerTwo);
        TennisPlayer[] players = {playerOne, playerTwo};
        List<TennisPlayer> pl = grandSlamDao.getAllPlayersWonTheTournament(grandSlamOne.getId());
        assertEquals(Arrays.asList(players), pl);
    }

    @Test
    public void deleteTourn() throws Exception {
        GrandSlam grandSlamOne = createGrandSlamOne();
        GrandSlam grandSlamTwo = createGrandSlamTwo();
        grandSlamDao.add(grandSlamOne);
        grandSlamDao.add(grandSlamTwo);
        grandSlamDao.deleteTourn(grandSlamOne.getId());
        assertEquals(1, grandSlamDao.getAllTornaments().size());
    }

    @Test
    public void deleteAllTourn() throws Exception {
        GrandSlam grandSlamOne = createGrandSlamOne();
        GrandSlam grandSlamTwo = createGrandSlamTwo();
        grandSlamDao.add(grandSlamOne);
        grandSlamDao.add(grandSlamTwo);
        grandSlamDao.deleteAllTourn();
        assertEquals(0, grandSlamDao.getAllTornaments().size());
    }
}