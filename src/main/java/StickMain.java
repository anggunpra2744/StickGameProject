package src.main.java;

// ALPRO I2 / Kelompok 8
// 1. Anggun Pratiwi Silalahi (082111633045)
// 2. Annisa Isaura Alfiany (082111633049)
// 3. Adiyatma Admaja (0821116330..)


import java.util.Scanner;
import java.util.Random;

public class StickMain {
    
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        
        System.out.println("Selamat datang!");
        int mode = gameMode();
        int numSticks = askInitialNumSticks();
        playGame(mode, numSticks);
    }
    
    //fungsi berikut untuk memilih mode game, apakah bermain lawan komputer atau pemain lain
    public static int gameMode() {
        int mode;
        System.out.println("Apakah Anda mau main pertama? (Y/N)");
        Scanner input = new Scanner(System.in);
        String goFirst = input.nextLine();
        Scanner take = new Scanner(System.in);
        int numToTake = 0;

        while (numSticks > 0) {
            if (goFirst.equals("y") || goFirst.equals("Y")) {
                System.out.println("Ada sebanyak " + numSticks + "stik.");
                System.out.println("Berapa banyak stik yang ingin Anda ambil? (Pilih 1 hingga 3)");
                numToTake = take.nextInt();

                if (numToTake > 3) {
                    numToTake = 3;
                } else if (numToTake < 1) {
                    numToTake = 1;
                }
                numSticks = numSticks - numToTake;

                if (numSticks <= 0) {
                    System.out.println("Anda kalah!");
                }
            } else {
                if ((numSticks - 2) % 3 == 0 || numSticks - 2 == 0) {
                    numToTake = 1;
                } else {
                    numToTake = 2;
                }
                System.out.println("Komputer mengambil " + numToTake + "stik.");
                numSticks = numSticks - numToTake;

                if (numSticks <= 0) {
                    System.out.println("Anda menang!");
                } else {
                    System.out.println("Ada sebanyak " + numSticks + "stik.");
                    System.out.println("Berapa stik yang ingin Anda ambil? (Pilih 1 hingga 3)");
                    numToTake = take.nextInt();

                    if (numToTake > 3) {
                        numToTake = 3;
                    } else if (numToTake < 1) {
                        numToTake = 1;
                    }
                    numSticks = numSticks - numToTake;

                    if (numSticks <= 0) {
                        System.out.println("Anda kalah!");
                    }

                }

            }

        }

    }

}
