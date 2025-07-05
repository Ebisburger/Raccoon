public class Raccoon{
    private String name;
    private boolean talking;
    private String personality;
    private boolean tokenGiven;

    public Raccoon(String name, boolean talking, String personality, boolean tokenGiven) {
        this.name = name;
        this.talking = talking;
        this.personality = personality;
        this.tokenGiven = tokenGiven;

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

    public boolean isTokenGiven() {
        return tokenGiven;
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

    public void setTokenGiven(boolean tokenGiven) {
        this.tokenGiven = tokenGiven;
    }
}