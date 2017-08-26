package models;

public class TennisPlayer extends Player {
    private int id;
    private int ranking;
    private int points;
    private int tournamentsPlayed;

    public TennisPlayer(String name, String gender, int age, int ranking, int points, int tournamentsPlayed) {
        super(name, gender, age);
        this.ranking = ranking;
        this.points = points;
        this.tournamentsPlayed = tournamentsPlayed;
    }

    // getters
    public int getRanking() {
        return ranking;
    }

    public int getPoints() {
        return points;
    }

    public int getTournamentsPlayed() {
        return tournamentsPlayed;
    }

    public int getId() {
        return id;
    }

    // setters
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setTournamentsPlayed(int tournamentsPlayed) {
        this.tournamentsPlayed = tournamentsPlayed;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TennisPlayer player = (TennisPlayer) o;

        if (id != player.id) return false;
        if (ranking != player.ranking) return false;
        if (points != player.points) return false;
        return tournamentsPlayed == player.tournamentsPlayed;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + ranking;
        result = 31 * result + points;
        result = 31 * result + tournamentsPlayed;
        return result;
    }
}
