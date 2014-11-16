package XCOMGame;


import java.util.Random;

public class Continent {

    private int panic;
    private String name;
    private String[] cities;

    public Continent(int panic, String name, String[] cities) {
        this.panic = panic;
        this.name = name;
        this.cities = cities;
    }

    public Continent processPanic() {
        return new Continent(panic + 1, name, cities);
    }

    public int panic() {
        return panic;
    }

    public String name() {
        return name;
    }

    public String randCity() {
        Random rng = new Random();
        return cities[rng.nextInt(cities.length)];
    }

}
