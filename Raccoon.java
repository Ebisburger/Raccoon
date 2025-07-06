public class Raccoon{
    private String name;
    private boolean talking;
    private String personality;
    private boolean timbitGiven;

    public Raccoon(String name, boolean talking, String personality, boolean timbitsGiven) {
        this.name = name;
        this.talking = talking;
        this.personality = personality;
        this.timbitGiven = timbitsGiven;

    }

    public String getName() {
        return name;
    }

    public boolean isTalking() {
        return talking;
    }

    public String getPersonality() {
        return personality;
    }

    public boolean isTimbitGiven() {
        return timbitGiven;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTalking(boolean talking) {
        this.talking = talking;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public void setTimbitGiven(boolean timbitsGiven) {
        this.timbitGiven = timbitsGiven;
    }
}
