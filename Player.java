public class Player {
    private int hp;
    private static final int MAX_HP = 200;

    
    Player(int hp) {
        this.hp = hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public int AttackDamage() {
        int damage = (int)(Math.random() * 20 + 10);
        return damage;
    }

    public int setAttackDamage(int attackDamage) {
        return attackDamage;
    }
    
    public int healingTim(){
        int healAmount = 10;
        if (hp < MAX_HP) {
            hp += healAmount;
            if (hp > MAX_HP) {
                hp = MAX_HP; 
            }
        }
        return hp;
    }

    
}
