import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create Raccoon objects
        Raccoon Grancoon = new Raccoon("Grancoon", false, "Grandmaly", false);
        Raccoon MrScrappy = new Raccoon("Mr. Scrappy", false, "Sophisticated", false);
        Raccoon Bandita = new Raccoon("Bandita", false, "Sassy", false);
        Raccoon Chadcoon = new Raccoon("Chadcoon", false, "Sleepy", false);
        FinalBattle();
    }

    // public static void Grancoon() {
    //     Grancoon.setTalking(true);

    // }

    // public static void MrScrappy() {
    //     MrScrappy.setTalking(true);
    // }

    // public static void Bandita() {
    //     Bandita.setTalking(true);
    // }

    // public static void Chadcoon() {
    //     Chadcoon.setTalking(true);
    // }

    public static void FinalBattle() {
    Player trashDiver = new Player(200);
    ManagerCoonCoon managerCoonCoon = new ManagerCoonCoon("Manager CoonCoon", true, "Bossy", true, 250);
    boolean CoonCoonDefeated = false;

    int healingTimbits = 3;
    int attackTimbits = 3;
    int shieldTimbits = 3;

    Scanner scanner = new Scanner(System.in);

    while (!CoonCoonDefeated) {
        // Player attacks
        int playerDamage = trashDiver.AttackDamage();
        managerCoonCoon.setHp(managerCoonCoon.getHp() - playerDamage);
        System.out.println("You attacked Manager CoonCoon for " + playerDamage + " damage!");

        // Check if Manager CoonCoon is defeated
        if (managerCoonCoon.getHp() <= 0) {
            System.out.println("Manager CoonCoon has been defeated!");
            CoonCoonDefeated = true;
            break;
        }

        // Manager CoonCoon attacks
        int CoonDamage = managerCoonCoon.TrashAttackDamage();
        trashDiver.setHp(trashDiver.getHp() - CoonDamage);
        System.out.println("Manager CoonCoon attacked you for " + CoonDamage + " damage!");

        // Check if Player is defeated
        if (trashDiver.getHp() <= 0) {
            System.out.println("You have been defeated by Manager CoonCoon!");
            break;
        }

        // Display current HP
        System.out.println("Your current HP: " + trashDiver.getHp());
        System.out.println("Manager CoonCoon's current HP: " + managerCoonCoon.getHp());

        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("Choose a timbit to use:");
            System.out.println("1. Healing timbit (" + healingTimbits + " left)");
            System.out.println("2. Attack boost timbit (" + attackTimbits + " left)");
            System.out.println("3. Shield timbit (" + shieldTimbits + " left)");
            System.out.println("4. None");
            System.out.print("Enter your choice (1-4): ");

            int timbitChoice;
            try {
                timbitChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                timbitChoice = 0;
            }

            switch (timbitChoice) {
                case 1:
                    if (healingTimbits > 0) {
                        System.out.println("Do you want to use a healing timbit? (yes/no)");
                        String healChoice = scanner.nextLine().toLowerCase();
                        if (healChoice.equals("yes")) {
                            HealingTim(trashDiver);
                            healingTimbits--;
                            validChoice = true;
                        } else if (healChoice.equals("no")) {
                            System.out.println("You chose not to heal yourself.");
                            validChoice = true;
                        } else {
                            System.out.println("Invalid choice. Please type 'yes' or 'no'.");
                        }
                    } else {
                        System.out.println("No healing timbits remaining!");
                    }
                    break;
                case 2:
                    if (attackTimbits > 0) {
                        System.out.println("Do you want to use an attack boost timbit? (yes/no)");
                        String attackChoice = scanner.nextLine().toLowerCase();
                        if (attackChoice.equals("yes")) {
                            AttackTim(trashDiver);
                            attackTimbits--;
                            validChoice = true;
                        } else if (attackChoice.equals("no")) {
                            System.out.println("You chose not to use an attack boost timbit.");
                            validChoice = true;
                        } else {
                            System.out.println("Invalid choice. Please type 'yes' or 'no'.");
                        }
                    } else {
                        System.out.println("No attack boost timbits remaining!");
                    }
                    break;
                case 3:
                    if (shieldTimbits > 0) {
                        System.out.println("Do you want to use a shield timbit? (yes/no)");
                        String shieldChoice = scanner.nextLine().toLowerCase();
                        if (shieldChoice.equals("yes")) {
                            ShieldTim(trashDiver, managerCoonCoon);
                            shieldTimbits--;
                            validChoice = true;
                        } else if (shieldChoice.equals("no")) {
                            System.out.println("You chose not to use a shield timbit.");
                            validChoice = true;
                        } else {
                            System.out.println("Invalid choice. Please type 'yes' or 'no'.");
                        }
                    } else {
                        System.out.println("No shield timbits remaining!");
                    }
                    break;
                case 4:
                    System.out.println("You chose not to use a timbit this turn.");
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        // Manager CoonCoon healing option
        int randomChance = (int) (Math.random() * 10);
        if (randomChance < 1) { // 10% chance for Manager CoonCoon to heal
            CoonCoonHeals(managerCoonCoon);
        } else {
            System.out.println("Manager CoonCoon did not heal this turn.");
        }
    }
}

    // Methods for using timbits
    public static void HealingTim(Player trashDiver) {
        trashDiver.healingTim();
        System.out.println("You healed yourself. Your current HP is: " + trashDiver.getHp());
    }

    public static void AttackTim(Player trashDiver) {
        int attackBoost = (int) (Math.random() * 10 + 5);
        trashDiver.setAttackDamage(trashDiver.AttackDamage() + attackBoost);
        System.out.println("You used an attack boost timbit! Your attack damage is now: " + trashDiver.AttackDamage());
    }

    public static void ShieldTim(Player trashDiver, ManagerCoonCoon managerCoonCoon){
        int shieldAmount = managerCoonCoon.TrashAttackDamage();
        trashDiver.setHp(trashDiver.getHp() + shieldAmount);
        System.out.println("You used a shield timbit!");
    }

    // Method for Manager CoonCoon healing themselves
    public static void CoonCoonHeals(ManagerCoonCoon managerCoonCoon) {
        managerCoonCoon.HealAmount();
        System.out.println("Manager CoonCoon healed themselves. Their current HP is: " + managerCoonCoon.getHp());
    }

}
