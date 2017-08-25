package models;

public class TennisPlayer extends Player {
    private int id;
    private int ranking;
    private String country;
    private int points;
    private int tournamentsPlayed;

    public TennisPlayer(String name, String gender, int age, int ranking, String country, int points, int tournamentsPlayed) {
        super(name, gender, age);
        this.ranking = ranking;
        this.country = country;
        this.points = points;
        this.tournamentsPlayed = tournamentsPlayed;
    }

    // getters
    public int getRanking() {
        return ranking;
    }

    public String getCountry() {
        return country;
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

    public void setCountry(String country) {
        this.country = country;
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

        TennisPlayer that = (TennisPlayer) o;

        if (id != that.id) return false;
        if (ranking != that.ranking) return false;
        if (points != that.points) return false;
        if (tournamentsPlayed != that.tournamentsPlayed) return false;
        return country.equals(that.country);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + ranking;
        result = 31 * result + country.hashCode();
        result = 31 * result + points;
        result = 31 * result + tournamentsPlayed;
        return result;
    }
}
