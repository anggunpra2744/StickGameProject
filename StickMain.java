package src.main.java;

import java.util.Scanner;

public class StickMain {

    public static void main(String[] args) {

        int numSticks = 21;
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
