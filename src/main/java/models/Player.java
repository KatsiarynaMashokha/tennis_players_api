package models;

abstract class Player {
    private String name;
    private String gender;
    private int age;

    public Player(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (age != player.age) return false;
        if (!name.equals(player.name)) return false;
        return gender.equals(player.gender);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + age;
        return result;
    }
}
