import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class TicTacToe {

    //human vs human
    private static void multiplayer() {
        Scanner scan = new Scanner(System.in);
        Game game = new Game("X");

        while (game.getIsOn()) {
            System.out.print("\033[H\033[2J");
            game.getBoard().print();
            boolean validMove = false;
            while (!validMove) {
                game.getBoard().printDemo();
                System.out.println("Your move(0-8): ");
                String pos = scan.nextLine();
                try {
                    game.makeMove(pos);
                    validMove = true;
                } catch (Exception e) {
                    System.out.print("\033[H\033[2J");
                    game.getBoard().print();
                    System.out.println("Invalid Input. Enter a number 0-8");
                }
            }
            if (game.checkForWinner()) {
                break;
            }
            game.switchPlayer();
        }
    }

    //human vs computer
    private static void singleplayer() {
        Scanner scan = new Scanner(System.in);
        Game game = new Game("X");
        Minimax ai = new Minimax(game);

        while (game.getIsOn()) {
            System.out.print("\033[H\033[2J");
            game.getBoard().print();
            boolean validMove = false;
            while (!validMove) {
                game.getBoard().printDemo();
                System.out.println("Your move(0-8): ");
                String pos = scan.nextLine();
                try {
                    game.makeMove(pos);
                    validMove = true;
                } catch (Exception e) {
                    System.out.print("\033[H\033[2J");
                    game.getBoard().print();
                    System.out.println("Invalid Input. Enter a number 0-8");
                }
            }
            if (game.checkForWinner()) {
                break;
            }
            game.switchPlayer();
            try {
                ai.aiMakeMove();
            } catch (CloneNotSupportedException e) {
                System.out.println("Ai isn't working");
            }
            if (game.checkForWinner()) {
                break;
            }
            game.switchPlayer();
        }
    }


    public static void main(String[] args) {

        singleplayer();
    }
}