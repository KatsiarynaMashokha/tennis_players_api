package models;

abstract class Tournament {
    private String title;

    public Tournament(String title) {
        this.title = title;
    }

    // getter
    public String getTitle() {
        return title;
    }

    // setter
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tournament that = (Tournament) o;

        return title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
