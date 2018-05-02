import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class Game {

    private HashSet<ArrayList<Integer>> winningCombinations;
    private ArrayList<Integer> playerX = new ArrayList<>();
    private ArrayList<Integer> playerO = new ArrayList<>();
    private boolean isOn;
    private String currentPlayer;
    private Board board;

    public boolean getIsOn(){
        return isOn;
    }

    public Board getBoard() {
        return board;
    }

    public Game(String p) {
        board = new Board();
        currentPlayer = p.toUpperCase();
        isOn = true;
        winningCombinations = new HashSet<>(Arrays
            .asList(new ArrayList<Integer>(Arrays.asList(0, 1, 2)),
                    new ArrayList<Integer>(Arrays.asList(3, 4, 5)),
                    new ArrayList<Integer>(Arrays.asList(6, 7, 8)),
                    new ArrayList<Integer>(Arrays.asList(0, 3, 6)),
                    new ArrayList<Integer>(Arrays.asList(1, 4, 7)),
                    new ArrayList<Integer>(Arrays.asList(2, 5, 8)),
                    new ArrayList<Integer>(Arrays.asList(0, 4, 8)),
                    new ArrayList<Integer>(Arrays.asList(2, 4, 6))));
    }

    public void makeMove(String rawInput) {
        //might throw NumberFormatException
        int pos = Integer.parseInt(rawInput);
        //might throw CellAlreadyTakenException
        //or ArrayIndexOutOfBoundsException
        board.getCell(pos).setPlayer(currentPlayer);
        if (currentPlayer.equals("X")) {
            playerX.add(pos);
        } else {
            playerO.add(pos);
        }
        board.takeCell();
    }

    public void checkForWinner() {
        if (board.isFull()) {
            isOn = false;
            System.out.print("\033[H\033[2J");
            board.print();
            System.out.println("Draw");
        }
        for (ArrayList<Integer> comb : winningCombinations) {
            if (playerO.containsAll(comb) || playerX.containsAll(comb)){
                isOn = false;
                System.out.print("\033[H\033[2J");
                board.print();
                System.out.println("Player " + currentPlayer + " won");
            }
        }
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }
}