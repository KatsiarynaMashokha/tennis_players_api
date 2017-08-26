package dao;

import models.GrandSlam;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oGrandSlamDaoTest {
    private Sql2oGrandSlamDao grandSlamDao;
    private Connection conn;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        grandSlamDao = new Sql2oGrandSlamDao(sql2o);
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
    }

    @Test
    public void getAllPlayersWonTheTournament() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void deleteTourn() throws Exception {
    }

    @Test
    public void deleteAllTourn() throws Exception {
    }

}