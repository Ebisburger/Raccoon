public class ManagerCoonCoon extends Raccoon{
    private int Hp;
    
    public ManagerCoonCoon(String name, boolean talking, String personality, boolean timbitGiven, int Hp) {
        super(name, talking, personality, timbitGiven);
        this.Hp = Hp;
    }

    public int TrashAttackDamage(){
        int damage = (int)(Math.random() * 10 + 1);
        return damage;
    }

    public int HealAmount() {
        int healAmount = (int)(Math.random() * 5 + 1);
        return healAmount;
    }

    public int getHp() {
        return Hp;
    }

    public void setHp(int hp) {
        this.Hp = hp;
    }
}
