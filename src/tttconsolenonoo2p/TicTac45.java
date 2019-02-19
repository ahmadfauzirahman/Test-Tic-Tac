/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tttconsolenonoo2p;

import java.util.Scanner;

/**
 *
 * @author macbook
 */
public class TicTac45 {

    private char[][] papan = {{'1', '2', '3', '4'}, {'5', '6', '7', '8'}, {'9', 'a', 'b', 'c'}, {'d', 'e', 'f', 'g'}};
    private static String status; 
    private char winner; // winerr
    private char whoseTurn; // 
    
    // panjang leng papan bisa diganti jika ingin 4 * 4 atau 5 * 5
    private int length = 4;
 
    public TicTac45() //constructor that creates the initial setting
    {
        status = "tetepBermain";
        winner = ' ';
        whoseTurn = 'X';
    }

    public void Papan() { //to display papan for user
        System.out.println();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(papan[i][j] + " ");

            }
            System.out.println();

        }
        System.out.println();
    }

    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(whoseTurn + ", your turn to play. Enter characters (1-9, a-g): ");
        String result = scanner.nextLine().trim();
        boolean flag = false; 
        boolean gameResult = false;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (result.equals(String.valueOf(papan[i][j]))) { 
                    if (papan[i][j] != whoseTurn) { 
                        flag = true;
                        papan[i][j] = whoseTurn; 
                        gameResult = cekPapan(i, j);

                        if (gameResult) {
                            winner = whoseTurn;
                            status = "done";
                        }
                    }
                }
            }
        }
        if (!flag) { 
            System.out.println("Illegal character or place has been already taken. TRY AGAIN! ");
        } else {
            whoseTurn = changePlayer(whoseTurn);
        }

        if (full(papan)) { 
            System.out.println("TIE!!!");
            winner = 'T';
            status = "done";
        }
    }
   
    public boolean full(char[][] papan) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (papan[i][j] == 'X' || papan[i][j] == 'O') {
                    count++;
                }
            }
        }
        if (count == length * length) {
            return true;
        } else {
            return false;
        }
    }

   
    public boolean cekPapan(int i, int j) {
        int count = 1;
        int iUp = i - 1;
        int jLeft = j - 1;
        while (iUp >= 0 && jLeft >= 0 && papan[iUp][jLeft] == whoseTurn) {
            --iUp;
            --jLeft;
            count++;
        }
        int iDown = i + 1;
        int jRight = j + 1;
        while (iDown <= length - 1 && jRight <= length - 1 && papan[iDown][jRight] == whoseTurn) {
            ++iDown;
            ++jRight;
            count++;
        }
        if (count < 3) {
            count = 1;
        }
        iUp = i - 1;
        jRight = j + 1;
        while (iUp >= 0 && jRight <= length - 1 && papan[iUp][jRight] == whoseTurn) {
            --iUp;
            ++jRight;
            count++;
        }
        iDown = i + 1;
        jLeft = j - 1;
        while (iDown <= length - 1 && jLeft >= 0 && papan[iDown][jLeft] == whoseTurn) {
            ++iDown;
            --jLeft;
            count++;
        }
        if (count >= 3) {
            return true;
        }
        count = 1;
        jLeft = j - 1; 
        while (jLeft >= 0 && papan[i][jLeft] == whoseTurn) {
            --jLeft;
            ++count;
        }
        jRight = j + 1; 
        while (jRight <= length - 1 && papan[i][jRight] == whoseTurn) {
            ++jRight;
            ++count;
        }
        if (count < 3) {
            count = 1;
        }
        iDown = i + 1;
        while (iDown <= length - 1 && papan[iDown][j] == whoseTurn) {
            ++iDown;
            ++count;
        }
        iUp = i - 1;
        while (iUp >= 0 && papan[iUp][j] == whoseTurn) {
            --iUp;
            ++count;
        }
        if (count >= 3) {
            return true;
        }
        return false;
    }

    private static char changePlayer(char player) { 
        if (player == 'X') {
            return 'O';
        } else {
            return 'X';
        }
    }

   
    public static boolean done() {
        if (status == "tetepBermain") {
            return false;
        } else {
            return true;
        }
    }

 
    public char mencariPemenang() {
        return winner;
    }

    public static void main(String[] args) {
        TicTac45 game2 = new TicTac45();
        while (!done()) {
            game2.Papan();
            game2.input();
        }
        game2.Papan();
        System.out.println();
        if (game2.mencariPemenang() == 'T') {
            System.out.println("Tidak Ada Pemenang");
        } else {
            System.out.println("Pemenang Adalah : " + game2.mencariPemenang() + " !! ");
        }

    }
}
