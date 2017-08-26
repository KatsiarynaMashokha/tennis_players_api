package dao;

import models.Country;
import models.GrandSlam;
import models.TennisPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oTennisPlayerDaoTest {
    private Sql2oTennisPlayerDao tennisPlayerDao;
    private Sql2oGrandSlamDao grandSlamDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        tennisPlayerDao = new Sql2oTennisPlayerDao(sql2o);
        grandSlamDao = new Sql2oGrandSlamDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    // helper to create a new player
    TennisPlayer createPlayerOne() {
        return new TennisPlayer("Roger Federer", "M", 36, 3, 7145, 16);
    }

    TennisPlayer createPlayerTwo() {
        return new TennisPlayer("Rafael Nadal", "M", 31, 1, 7645, 16);
    }

    TennisPlayer createPlayerThree() {
        return new TennisPlayer("Stan Wawrinka", "M", 32, 4, 5690, 18);
    }

    GrandSlam createGrandSlamOne() {
        return new GrandSlam("US Open", "September");
    }

    GrandSlam createGrandSlamTwo() {
        return new GrandSlam("Australian Open", "January");
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
        TennisPlayer playerOne = createPlayerOne();
        TennisPlayer playerTwo = createPlayerTwo();
        tennisPlayerDao.add(playerOne);
        tennisPlayerDao.add(playerTwo);
        TennisPlayer[] players = {playerOne, playerTwo};
        assertEquals(2, tennisPlayerDao.getAllPlayers().size());
        assertEquals(Arrays.asList(players), tennisPlayerDao.getAllPlayers());
    }

    @Test
    public void findById() throws Exception {
        TennisPlayer playerOne = createPlayerOne();
        TennisPlayer playerTwo = createPlayerTwo();
        tennisPlayerDao.add(playerOne);
        tennisPlayerDao.add(playerTwo);
        TennisPlayer foundPlayer = tennisPlayerDao.findById(playerOne.getId());
        assertEquals(playerOne, foundPlayer);
    }

    @Test
    public void getAllPlayersForCountry() throws Exception {
        TennisPlayer playerOne = createPlayerOne();
        TennisPlayer playerTwo = createPlayerTwo();
        TennisPlayer playerThree = createPlayerThree();
        Country countryOne = new Country("Switzerland");
        Country countryTwo = new Country("Spain");
        tennisPlayerDao.add(playerOne);
        tennisPlayerDao.add(playerTwo);
        tennisPlayerDao.add(playerThree);
        tennisPlayerDao.addCountryToPlayer(playerOne, countryOne);
        tennisPlayerDao.addCountryToPlayer(playerTwo, countryTwo);
        tennisPlayerDao.addCountryToPlayer(playerThree, countryOne);
        TennisPlayer [] players = {playerOne, playerThree};
        assertEquals(2, tennisPlayerDao.getAllPlayersForCountry(countryOne.getName()).size());
        assertEquals(Arrays.asList(players), tennisPlayerDao.getAllPlayersForCountry(countryOne.getName()));
    }

    @Test
    public void getAllTournamentsWonByPlayer() throws Exception {
        TennisPlayer playerOne = createPlayerOne();
        tennisPlayerDao.add(playerOne);
        GrandSlam grandSlamOne = createGrandSlamOne();
        grandSlamDao.add(grandSlamOne);
        GrandSlam grandSlamTwo = createGrandSlamTwo();
        grandSlamDao.add(grandSlamTwo);
        tennisPlayerDao.addPlayerToTournament(playerOne, grandSlamOne);
        tennisPlayerDao.addPlayerToTournament(playerOne, grandSlamTwo);
        GrandSlam[] slams = {grandSlamOne, grandSlamTwo};
        assertEquals(2, tennisPlayerDao.getAllTournamentsWonByPlayer(playerOne.getId()).size());
        assertEquals(Arrays.asList(slams), tennisPlayerDao.getAllTournamentsWonByPlayer(playerOne.getId()));
    }

    @Test
    public void update() throws Exception {
        TennisPlayer playerOne = createPlayerOne();
        tennisPlayerDao.add(playerOne);
        tennisPlayerDao.update(playerOne.getId(), 37, 1, 7145, 16);
        TennisPlayer updatedPlayer = tennisPlayerDao.findById(playerOne.getId());
        assertNotEquals(playerOne, updatedPlayer);
        assertEquals(37, updatedPlayer.getAge());
        assertEquals(1, updatedPlayer.getRanking());
    }

    @Test
    public void deletePlayer() throws Exception {
        TennisPlayer playerOne = createPlayerOne();
        TennisPlayer playerTwo = createPlayerTwo();
        tennisPlayerDao.add(playerOne);
        tennisPlayerDao.add(playerTwo);
        tennisPlayerDao.deletePlayer(playerOne.getId());
        assertEquals(1, tennisPlayerDao.getAllPlayers().size());
    }

    @Test
    public void deleteAllPlayers() throws Exception {
        TennisPlayer playerOne = createPlayerOne();
        TennisPlayer playerTwo = createPlayerTwo();
        tennisPlayerDao.add(playerOne);
        tennisPlayerDao.add(playerTwo);
        tennisPlayerDao.deleteAllPlayers();
        assertEquals(0, tennisPlayerDao.getAllPlayers().size());
    }
}