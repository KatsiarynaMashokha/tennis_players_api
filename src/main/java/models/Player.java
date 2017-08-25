package models;

abstract class Player {
    private String name;
    private char gender;
    private int age;

    public Player(String name, char gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    // getters
    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(char gender) {
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

        if (gender != player.gender) return false;
        if (age != player.age) return false;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (int) gender;
        result = 31 * result + age;
        return result;
    }
}
