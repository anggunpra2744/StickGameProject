package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class StickGameGUI extends JFrame {
    private int totalSticks;
    private boolean isPlayer1Turn = true;
    private boolean isVsComputer = false;
    private boolean isPlayerStarts = true;
    private Random random = new Random();

    private JLabel statusLabel;
    private JLabel stickLabel;
    private JButton[] stickButtons = new JButton[3];

    public StickGameGUI() {
        setTitle("Game Stick");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initStartDialog();
    }

    private void initStartDialog() {
        JPanel panel = new JPanel(new GridLayout(6, 1));

        // Mode pilihan
        String[] modes = { "Player vs Player", "Player vs Komputer" };
        JComboBox<String> modeBox = new JComboBox<>(modes);

        // Jumlah stik awal
        JTextField stickInput = new JTextField("21");

        // Giliran
        String[] turnOptions = { "Main Pertama", "Main Kedua" };
        JComboBox<String> turnBox = new JComboBox<>(turnOptions);

        JButton startButton = new JButton("Mulai Game");
        panel.add(new JLabel("Pilih Mode Permainan:"));
        panel.add(modeBox);
        panel.add(new JLabel("Jumlah stik awal (minimal 5):"));
        panel.add(stickInput);
        panel.add(new JLabel("Pilih giliran:"));
        panel.add(turnBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Setup Game", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                totalSticks = Integer.parseInt(stickInput.getText());
                if (totalSticks < 5)
                    throw new NumberFormatException();

                isVsComputer = modeBox.getSelectedIndex() == 1;
                isPlayerStarts = turnBox.getSelectedIndex() == 0;
                isPlayer1Turn = isPlayerStarts;

                initGameUI();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Jumlah stik harus angka minimal 5.");
                initStartDialog();
            }
        } else {
            System.exit(0);
        }
    }

    private void initGameUI() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        stickLabel = new JLabel("Sisa Stik: " + totalSticks, SwingConstants.CENTER);
        stickLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(stickLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        for (int i = 0; i < 3; i++) {
            final int sticks = i + 1;
            stickButtons[i] = new JButton("Ambil " + sticks);
            stickButtons[i].addActionListener(e -> playerMove(sticks));
            buttonPanel.add(stickButtons[i]);
        }
        add(buttonPanel, BorderLayout.CENTER);

        statusLabel = new JLabel(getTurnText(), SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);

        if (!isPlayer1Turn && isVsComputer) {
            computerMove();
        }
    }

    private void playerMove(int taken) {
        if (taken < 1 || taken > 3 || taken > totalSticks) {
            JOptionPane.showMessageDialog(this, "Pilihan tidak valid.");
            return;
        }

        totalSticks -= taken;
        updateGameState(taken);
    }

    private void computerMove() {
        Timer timer = new Timer(1000, e -> {
            int move = totalSticks % 4;
            int taken = (move == 0) ? Math.min(3, totalSticks) : move;
            totalSticks -= taken;

            JOptionPane.showMessageDialog(this, "Komputer mengambil " + taken + " stik.");
            updateGameState(taken);
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void updateGameState(int taken) {
        stickLabel.setText("Sisa Stik: " + totalSticks);

        if (totalSticks == 0) {
            String loser;
            if (!isVsComputer) {
                loser = isPlayer1Turn ? "Player 1" : "Player 2";
            } else {
                loser = isPlayer1Turn ? "Player" : "Komputer";
            }
            JOptionPane.showMessageDialog(this, loser + " mengambil stik terakhir dan kalah!");
            System.exit(0);
        } else {
            isPlayer1Turn = !isPlayer1Turn;
            statusLabel.setText(getTurnText());

            if (!isPlayer1Turn && isVsComputer) {
                disableButtons();
                computerMove();
            } else {
                enableButtons();
            }
        }
    }

    private String getTurnText() {
        if (!isVsComputer) {
            return isPlayer1Turn ? "Giliran: Player 1" : "Giliran: Player 2";
        } else {
            return isPlayer1Turn ? "Giliran: Player" : "Giliran: Komputer";
        }
    }

    private void disableButtons() {
        for (JButton button : stickButtons) {
            button.setEnabled(false);
        }
    }

    private void enableButtons() {
        for (JButton button : stickButtons) {
            button.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StickGameGUI());
    }
}