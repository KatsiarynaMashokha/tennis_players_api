package models;

public class GrandSlamTornaments extends Tournament {
    private String date;

    public GrandSlamTornaments(String title, String date) {
        super(title);
        this.date = date;
    }

    // getters
    public String getDate() {
        return date;
    }

    // setters
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GrandSlamTornaments that = (GrandSlamTornaments) o;

        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
