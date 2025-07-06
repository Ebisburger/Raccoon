import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JFrame implements ActionListener, KeyListener {
    JButton startButton;
    JLabel backgroundLabel;
    ArrayList<ImageIcon> images = new ArrayList<>();
    int currentIndex = 0;

    // Battle-related components
    JButton healButton, attackButton, shieldButton, noneButton;
    JLabel playerHpLabel, managerHpLabel;
    JLabel healLabel, attackLabel, shieldLabel, noneLabel;
    JPanel buttonPanel; // <- Changed to instance variable

    // Battle state
    Player trashDiver;
    ManagerCoonCoon managerCoonCoon;
    boolean CoonCoonDefeated = false;
    int healingTimbits = 3;
    int attackTimbits = 3;
    int shieldTimbits = 3;

    boolean timbitsUsedUp = false;

    public Main() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setPreferredSize(new Dimension(1320, 780));
        setFocusable(true);
        addKeyListener(this);

        // Load background images
        String[] paths = {
                "1.png", "2.png", "3.png", "4.png", "5.png", "6.png", "7.png", "8.png",
                "9.png", "10.png", "11.png", "12.png", "13.png", "14.png", "15.png",
                "16.png", "17.png", "18.png", "19.png", "20.png", "21.png", "22.png",
                "23.png", "24.png", "25.png", "26.png", "27.png", "28.png", "29.png",
                "30.png", "31.png", "32.png", "33.png", "34.png", "35.png", "36.png",
        };

        for (String path : paths) {
            ImageIcon original = new ImageIcon(path);
            Image scaled = original.getImage().getScaledInstance(1320, 780, Image.SCALE_SMOOTH);
            images.add(new ImageIcon(scaled));
        }

        backgroundLabel = new JLabel(images.get(currentIndex));
        backgroundLabel.setBounds(0, 0, 1320, 780);
        add(backgroundLabel);

        startButton = new JButton();
        startButton.setBounds(500, 400, 300, 300);
        startButton.addActionListener(this);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        backgroundLabel.add(startButton);

        // Load and scale button images
        ImageIcon healIcon = scaleIcon(new ImageIcon("heal_btn.png"), 75, 75);
        ImageIcon attackIcon = scaleIcon(new ImageIcon("attack_btn.png"), 75, 75);
        ImageIcon shieldIcon = scaleIcon(new ImageIcon("shield_btn.png"), 75, 75);
        ImageIcon noneIcon = scaleIcon(new ImageIcon("none_btn.png"), 75, 75);

        healButton = new JButton(healIcon);
        attackButton = new JButton(attackIcon);
        shieldButton = new JButton(shieldIcon);
        noneButton = new JButton(noneIcon);

        for (JButton btn : new JButton[] { healButton, attackButton, shieldButton, noneButton }) {
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setOpaque(false);
            btn.setPreferredSize(new Dimension(180, 60));
        }

        healLabel = new JLabel("Heal");
        attackLabel = new JLabel("Attack Boost");
        shieldLabel = new JLabel("Shield");
        noneLabel = new JLabel("None");

        for (JLabel label : new JLabel[] { healLabel, attackLabel, shieldLabel, noneLabel }) {
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }

        buttonPanel = new JPanel(new GridLayout(2, 4, 30, 5));
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(210, 650, 900, 110);

        buttonPanel.add(healButton);
        buttonPanel.add(attackButton);
        buttonPanel.add(shieldButton);
        buttonPanel.add(noneButton);
        buttonPanel.add(healLabel);
        buttonPanel.add(attackLabel);
        buttonPanel.add(shieldLabel);
        buttonPanel.add(noneLabel);

        backgroundLabel.add(buttonPanel);

        healButton.addActionListener(e -> useHealingTimbit());
        attackButton.addActionListener(e -> useAttackTimbit());
        shieldButton.addActionListener(e -> useShieldTimbit());
        noneButton.addActionListener(e -> playerAttacks());

        setBattleButtonsVisible(false);

        playerHpLabel = new JLabel();
        playerHpLabel.setFont(new Font("Arial", Font.BOLD, 24));
        playerHpLabel.setForeground(Color.WHITE);
        playerHpLabel.setBounds(50, 50, 400, 40);
        backgroundLabel.add(playerHpLabel);

        managerHpLabel = new JLabel();
        managerHpLabel.setFont(new Font("Arial", Font.BOLD, 24));
        managerHpLabel.setForeground(Color.WHITE);
        managerHpLabel.setBounds(50, 100, 400, 40);
        backgroundLabel.add(managerHpLabel);

        playerHpLabel.setVisible(false);
        managerHpLabel.setVisible(false);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private ImageIcon scaleIcon(ImageIcon icon, int w, int h) {
        Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    private void setBattleButtonsVisible(boolean visible) {
        healButton.setVisible(visible);
        attackButton.setVisible(visible);
        shieldButton.setVisible(visible);
        noneButton.setVisible(visible);
        healLabel.setVisible(visible);
        attackLabel.setVisible(visible);
        shieldLabel.setVisible(visible);
        noneLabel.setVisible(visible);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            ImageIcon newIcon = new ImageIcon("2.png");
            Image scaledImage = newIcon.getImage().getScaledInstance(1320, 780, Image.SCALE_SMOOTH);
            backgroundLabel.setIcon(new ImageIcon(scaledImage));
            startButton.setVisible(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            if (timbitsUsedUp && currentIndex < images.size() - 1) {
                currentIndex++;
                backgroundLabel.setIcon(images.get(currentIndex));
            } else if (!timbitsUsedUp && currentIndex < images.size() - 1) {
                currentIndex++;
                backgroundLabel.setIcon(images.get(currentIndex));

                if (currentIndex == 31) {
                    startFinalBattle();
                }
            }
        }

        if (key == KeyEvent.VK_LEFT && !timbitsUsedUp && currentIndex > 0) {
            currentIndex--;
            backgroundLabel.setIcon(images.get(currentIndex));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        new Main();
    }

    private void startFinalBattle() {
        ImageIcon newIcon = new ImageIcon("37.png");
        Image scaledImage = newIcon.getImage().getScaledInstance(1320, 780, Image.SCALE_SMOOTH);
        backgroundLabel.setIcon(new ImageIcon(scaledImage));

        trashDiver = new Player(200);
        managerCoonCoon = new ManagerCoonCoon("Manager CoonCoon", true, "Bossy", true, 250);
        CoonCoonDefeated = false;
        timbitsUsedUp = false;

        healingTimbits = 3;
        attackTimbits = 3;
        shieldTimbits = 3;

        setBattleButtonsVisible(true);
        buttonPanel.setVisible(true);
        playerHpLabel.setVisible(true);
        managerHpLabel.setVisible(true);

        updateHpLabels();
    }

    private void updateHpLabels() {
        playerHpLabel.setText("Your HP: " + trashDiver.getHp());
        managerHpLabel.setText("Manager CoonCoon HP: " + managerCoonCoon.getHp());
    }

    private void playerAttacks() {
        int playerDamage = trashDiver.AttackDamage();
        managerCoonCoon.setHp(managerCoonCoon.getHp() - playerDamage);
        updateHpLabels();

        if (managerCoonCoon.getHp() <= 0) {
            showBattleResult("Manager CoonCoon has been defeated!");
            return;
        }

        managerTurn();
    }

    private void managerTurn() {
        int damage = managerCoonCoon.TrashAttackDamage();
        trashDiver.setHp(trashDiver.getHp() - damage);
        updateHpLabels();

        if (trashDiver.getHp() <= 0) {
            showBattleResult("You have been defeated by Manager CoonCoon!");
            return;
        }

        if (Math.random() < 0.1) {
            managerCoonCoon.HealAmount();
            updateHpLabels();
            JOptionPane.showMessageDialog(this, "Manager CoonCoon healed!");
        }
    }

    private void useHealingTimbit() {
        if (healingTimbits > 0) {
            trashDiver.healingTim();
            healingTimbits--;
            JOptionPane.showMessageDialog(this, "You used a healing timbit!");
        } else {
            JOptionPane.showMessageDialog(this, "No healing timbits remaining!");
        }
        checkAllTimbitsUsed();
        playerAttacks();
    }

    private void useAttackTimbit() {
        if (attackTimbits > 0) {
            int boost = (int) (Math.random() * 10 + 5);
            trashDiver.setAttackDamage(trashDiver.AttackDamage() + boost);
            attackTimbits--;
            JOptionPane.showMessageDialog(this, "Attack boost timbit used!");
        } else {
            JOptionPane.showMessageDialog(this, "No attack boost timbits remaining!");
        }
        checkAllTimbitsUsed();
        playerAttacks();
    }

    private void useShieldTimbit() {
        if (shieldTimbits > 0) {
            int shieldAmount = managerCoonCoon.TrashAttackDamage();
            trashDiver.setHp(trashDiver.getHp() + shieldAmount);
            shieldTimbits--;
            JOptionPane.showMessageDialog(this, "Shield timbit used! HP increased by " + shieldAmount);
        } else {
            JOptionPane.showMessageDialog(this, "No shield timbits remaining!");
        }
        checkAllTimbitsUsed();
        playerAttacks();
    }

    private void checkAllTimbitsUsed() {
        if (!timbitsUsedUp && healingTimbits == 0 && attackTimbits == 0 && shieldTimbits == 0) {
            timbitsUsedUp = true;

            ImageIcon finalImage = new ImageIcon("32.png");
            Image scaled = finalImage.getImage().getScaledInstance(1320, 780, Image.SCALE_SMOOTH);
            backgroundLabel.setIcon(new ImageIcon(scaled));

            setBattleButtonsVisible(false);
            if (buttonPanel != null)
                buttonPanel.setVisible(false);
            playerHpLabel.setVisible(false);
            managerHpLabel.setVisible(false);

            JOptionPane.showMessageDialog(this, "All timbits used! Press → to continue.");
        }
    }

    private void showBattleResult(String message) {
        JOptionPane.showMessageDialog(this, message);
        disableAllButtons();

        // Clean up UI after battle
        setBattleButtonsVisible(false);
        if (buttonPanel != null)
            buttonPanel.setVisible(false);
        playerHpLabel.setVisible(false);
        managerHpLabel.setVisible(false);

        timbitsUsedUp = true;
    }

    private void disableAllButtons() {
        healButton.setEnabled(false);
        attackButton.setEnabled(false);
        shieldButton.setEnabled(false);
        noneButton.setEnabled(false);
    }
}
