import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JFrame implements ActionListener, KeyListener{
    JLabel backgroundLabel;
    JButton startButton;
    ImageIcon backgroundImage;

    JButton mrScrappyButton;
    JButton banditaButton;
    JButton chadcoonButton;

    ArrayList<Integer> checkpoints = new ArrayList<>();
    ArrayList<ImageIcon> backgrounds = new ArrayList<>();
    int currentIndex = 0;
    boolean allowScroll = true;

    boolean mrScrappy = false;
    boolean bandita = false;
    boolean chadcoon = false;

    public Main() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        String[] startingImagePaths = {
            "2.png",
            "3.png",
            "4.png",
            "5.png",
            "6.png",
            "7.png",
            "8.png",
            "9.png",
            "10.png",
            "11.png",
            "12.png",
        };

        String[] mrScrappyImagePaths = {
            "13.png",
            "14.png",
            "15.png",
            "16.png",
            "26.png",
        };

        String[] banditaImagePaths = {
            "27.png",
        };

        String[] chadcoonImagePaths = {
            "36.png",
            "37.png",
            "38.png",
            "39.png",
            "40.png",
            "26.png",
        };

        for (String path : startingImagePaths) {
            ImageIcon original = new ImageIcon(path);
            Image scaled = original.getImage().getScaledInstance(1320, 780, Image.SCALE_SMOOTH);
            backgrounds.add(new ImageIcon(scaled));
        }

        for (String path : mrScrappyImagePaths) {
            ImageIcon original = new ImageIcon(path);
            Image scaled = original.getImage().getScaledInstance(1320, 780, Image.SCALE_SMOOTH);
            backgrounds.add(new ImageIcon(scaled));
        }

        for (String path : banditaImagePaths) {
            ImageIcon original = new ImageIcon(path);
            Image scaled = original.getImage().getScaledInstance(1320, 780, Image.SCALE_SMOOTH);
            backgrounds.add(new ImageIcon(scaled));
        }

        for (String path : chadcoonImagePaths) {
            ImageIcon original = new ImageIcon(path);
            Image scaled = original.getImage().getScaledInstance(1320, 780, Image.SCALE_SMOOTH);
            backgrounds.add(new ImageIcon(scaled));
        }


        checkpoints.add(10); // 12.png
        checkpoints.add(14); // 16.png
        checkpoints.add(27); // 29.png
        checkpoints.add(24); // 26.png


        // Resize image to fit the content pane exactly
        ImageIcon originalIcon = new ImageIcon("startScreen.png");
        int newWidth = 1320;
        int newHeight = 780;
        Image scaledImage = originalIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon backgroundImage = new ImageIcon(scaledImage);

        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        backgroundLabel.setLayout(null); // needed to position child components
        this.setContentPane(backgroundLabel); // set the background image as the content pane

        // Buttons
        startButton = new JButton();
        startButton.setBounds(500, 400, 300, 300); // adjust as needed
        startButton.addActionListener(this);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        backgroundLabel.add(startButton);

        // character buttons
        mrScrappyButton = new JButton();
        mrScrappyButton.setBounds(1040, 270, 70, 100); // adjust as needed
        mrScrappyButton.addActionListener(this);
        mrScrappyButton.setOpaque(false);
        mrScrappyButton.setContentAreaFilled(false);
        mrScrappyButton.setBorderPainted(false);
        backgroundLabel.add(mrScrappyButton);
        mrScrappyButton.setEnabled(false);

        banditaButton = new JButton();
        banditaButton.setBounds(30, 320, 150, 200); // adjust as needed
        banditaButton.addActionListener(this);
        banditaButton.setOpaque(false);
        banditaButton.setContentAreaFilled(false);
        banditaButton.setBorderPainted(false);
        backgroundLabel.add(banditaButton);
        banditaButton.setEnabled(false);

        chadcoonButton = new JButton();
        chadcoonButton.setBounds(980, 540, 200, 200); // adjust as needed
        chadcoonButton.addActionListener(this);
        chadcoonButton.setOpaque(false);
        chadcoonButton.setContentAreaFilled(false);
        chadcoonButton.setBorderPainted(false);
        backgroundLabel.add(chadcoonButton);
        chadcoonButton.setEnabled(false);

        // Match frame size to image exactly (excluding window borders)
        setPreferredSize(new Dimension(1920, 1080));
        pack(); // makes frame size match preferred size
        setLocationRelativeTo(null); // center the window on screen
        setVisible(true);

        setFocusable(true);
        addKeyListener(this);
    }
    public static void main(String[] args) {
        new Main();
        
        // Create Raccoon objects
        Raccoon Grancoon = new Raccoon("Grancoon", false, "Grandmaly", false);
        Raccoon MrScrappy = new Raccoon("Mr. Scrappy", false, "Sophisticated", false);
        Raccoon Bandita = new Raccoon("Bandita", false, "Sassy", false);
        Raccoon Chadcoon = new Raccoon("Chadcoon", false, "Sleepy", false);
        FinalBattle();
    }

    public void generalSelection(){
        currentIndex = 15;
        backgroundLabel.setIcon(backgrounds.get(currentIndex));
        
        mrScrappyButton.setEnabled(!mrScrappy);
        banditaButton.setEnabled(!bandita);
        chadcoonButton.setEnabled(!chadcoon);
    }

    public void scrappyMiniGame(){
        Image miniGame = new ImageIcon("17.png").getImage().getScaledInstance(1320, 780, Image.SCALE_SMOOTH);
        backgroundLabel.setIcon(new ImageIcon(miniGame));

    }

    public void banditaDialogue(){
        currentIndex = 29; // index for 29.png
        backgroundLabel.setIcon(backgrounds.get(currentIndex));
    }

    public void chadcoonDialogue(){
        currentIndex = 24; // index for 26.png in your list
        backgroundLabel.setIcon(backgrounds.get(currentIndex));
    }

    @Override
        public void actionPerformed(ActionEvent e){
        if (e.getSource() == startButton){
            // Load the new image
            ImageIcon newIcon = new ImageIcon("2.png"); // replace with your new image filename
            Image scaledImage = newIcon.getImage().getScaledInstance(1320, 780, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Update the label's icon
            backgroundLabel.setIcon(scaledIcon);
            startButton.setVisible(false);

            System.out.println("start the game, background changed");
        }

        if (e.getSource() == mrScrappyButton){
            mrScrappy = true;
            scrappyMiniGame();
        }

        if (e.getSource() == banditaButton){
            bandita = true;
            banditaDialogue();
        }

        if (e.getSource() == chadcoonButton){
            chadcoon = true;
            chadcoonDialogue();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (!allowScroll) return; // Scrolling disabled at checkpoint

        if (key == KeyEvent.VK_RIGHT) {
            if (currentIndex < backgrounds.size() - 1) {
                currentIndex++;
                backgroundLabel.setIcon(backgrounds.get(currentIndex));

                if (checkpoints.contains(currentIndex)) {
                    allowScroll = false; // lock scrolling
                    if (currentIndex == 12 || currentIndex == 26){
                        generalSelection();
                    } else if (currentIndex == 16){
                        scrappyMiniGame();
                    } else if (currentIndex == 29){
                        banditaDialogue();
                    }
                    
                    //triggerCheckpoint(currentIndex); // show tasks
                }
            }
        } else if (key == KeyEvent.VK_LEFT) {
            if (currentIndex > 0) {
                currentIndex--;
                backgroundLabel.setIcon(backgrounds.get(currentIndex));
            }
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

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
