// Project Stick Game 
// Kelas Algoritma Pemrograman I2
// Kelompok 16:
// 1. Anggun Pratiwi Silalahi (082111633045)
// 2. Annisa Isaura Alfiany (082111633049)
// 3. Adiyatma Admaja (082111633061)

package src.main.java;

import java.util.Scanner;
import java.util.Random;

public class StickGame {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    // main class untuk stick game
    public static void main(String[] args) {
        System.out.print("===== Stick Game =====" + "\n\n");
        System.out.print("Selamat datang ke Stick Game Kelompok 16!" + "\n\n");
        int mode = chooseGameMode();
        int totalSticks = askInitialStickCount();
        boolean isPlayer1Turn = chooseTurnOrder(mode);
        playGame(mode, totalSticks, isPlayer1Turn);
    }

    // fungsi untuk memilih mode permainan, apakah user ingin bermain melawan pemain
    // atau komputer
    public static int chooseGameMode() {
        int mode;
        System.out.println("Silahkan pilih mode permainan:");
        System.out.println("1. Player vs Player");
        System.out.println("2. Player vs Komputer");

        while (true) {
            System.out.print("Masukkan pilihan (1/2): ");
            mode = scanner.nextInt();
            if (mode == 1 || mode == 2)
                break;
            System.out.println("Pilihan tidak valid. Coba lagi.");
        }
        return mode;
    }

    // fungsi untuk memasukkan jumlah stik awal
    public static int askInitialStickCount() {
        int sticks;
        while (true) {
            System.out.print("Masukkan jumlah stik awal (minimal 5 stik): ");
            sticks = scanner.nextInt();
            if (sticks >= 5)
                break;
            System.out.println("Jumlah stik terlalu sedikit. Minimal 5 stik.");
        }
        return sticks;
    }

    // fungsi untuk memilih apakah ingin bermain giliran pertama
    public static boolean chooseTurnOrder(int mode) {
        if (mode == 1)
            return true; // Player 1 always starts in PvP

        System.out.println("Silahkan pilih giliran main:");
        System.out.println("1. Main pertama");
        System.out.println("2. Main kedua");
        while (true) {
            System.out.print("Masukkan pilihan (1/2): ");
            int turn = scanner.nextInt();
            if (turn == 1)
                return true; // Player starts
            if (turn == 2)
                return false; // Computer starts
            System.out.println("Pilihan tidak valid. Coba lagi.");
        }
    }

    // fungsi logika utama permainan
    public static void playGame(int mode, int totalSticks, boolean isPlayer1Turn) {
        while (totalSticks > 0) {
            System.out.println("\nSisa stik: " + totalSticks);
            int taken;

            if (mode == 1 || isPlayer1Turn) {
                String name = (mode == 1) ? (isPlayer1Turn ? "Player 1" : "Player 2") : "Player";
                taken = playerTurn(name, totalSticks);
            } else {
                taken = computerTurn(totalSticks);
            }

            totalSticks -= taken;

            if (totalSticks == 0) {
                showLoser(mode, isPlayer1Turn);
                break;
            }

            isPlayer1Turn = !isPlayer1Turn;
        }
    }

    // fungsi giliran pemain
    public static int playerTurn(String playerName, int sticksLeft) {
        System.out.print(playerName + ", ambil 1 hingga 3 stik: ");
        int taken = scanner.nextInt();

        while (taken < 1 || taken > 3 || taken > sticksLeft) {
            System.out.print("Input tidak valid. Ambil antara 1 dan 3 stik (tidak melebihi sisa stik): ");
            taken = scanner.nextInt();
        }

        return taken;
    }

    // fungsi untuk giliran komputer (apabila user memilih melawan komputer)
    public static int computerTurn(int sticksLeft) {
        int move = sticksLeft % 4;
        int taken = (move == 0) ? Math.min(3, sticksLeft) : move;

        System.out.println("Komputer mengambil " + taken + " stik.");
        return taken;
    }

    // fungsi untuk menunjukkan hasil akhir, siapa yang kalah dan menang
    public static void showLoser(int mode, boolean isPlayer1Turn) {
        if (mode == 1) {
            System.out.println((isPlayer1Turn ? "Player 1" : "Player 2") + " mengambil stik terakhir dan kalah!");
        } else {
            if (isPlayer1Turn) {
                System.out.println("Player mengambil stik terakhir. Komputer menang!");
            } else {
                System.out.println("Komputer mengambil stik terakhir dan kalah. Player menang!");
            }
        }
    }
}