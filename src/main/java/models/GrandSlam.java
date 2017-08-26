package models;

public class GrandSlam extends Tournament {
    private String date;
    private int id;

    public GrandSlam(String title, String date) {
        super(title);
        this.date = date;
    }

    // getters
    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    // setters
    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GrandSlam grandSlam = (GrandSlam) o;

        if (id != grandSlam.id) return false;
        return date.equals(grandSlam.date);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + id;
        return result;
    }
}
