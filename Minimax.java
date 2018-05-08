import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class Minimax {

    private Game game;
    private int choice;

    public Minimax(Game g) {
        game = g;
    }

    public void aiMakeMove() throws CloneNotSupportedException {
        calculateMove((Game)game.clone());
        game.makeMove(Integer.toString(choice));
    }

    private int calculateMove(Game g) throws CloneNotSupportedException {
        if (isOver(g)) {
            return getScore(g);
        }
        ArrayList<Integer> scores = new ArrayList<>();
        ArrayList<Integer> moves = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Cell c =  g.getBoard().getCell(i);
            if (!c.getIsTaken()) {
                Game possibleGame = (Game)g.clone();
                possibleGame.makeMove(Integer.toString(i));
                possibleGame.switchPlayer();
                scores.add(calculateMove(possibleGame));
                moves.add(i);
            }
        }
        if (g.getCurrentPlayer().equals("O")) {
            //max
            int maxIndex = 0;
            for (int i = 0; i < scores.size(); i++) {
                maxIndex = (scores.get(i) > scores.get(maxIndex)) ? i : maxIndex;
            }
            choice = moves.get(maxIndex);
            System.out.println(choice + "\n" + moves + "\n" + scores); // debug
            return scores.get(maxIndex);
        } else {
            //min
            int minIndex = 0;
            for (int i = 0; i < scores.size(); i++) {
                minIndex = (scores.get(i) > scores.get(minIndex)) ? i : minIndex;
            }
            choice = moves.get(minIndex);
            System.out.println(choice); //debug
            return scores.get(minIndex);
        }
    }

    private boolean isOver(Game g) {
        HashSet<ArrayList<Integer>> winCombs = new HashSet<>(Arrays
            .asList(new ArrayList<Integer>(Arrays.asList(0, 1, 2)),
                    new ArrayList<Integer>(Arrays.asList(3, 4, 5)),
                    new ArrayList<Integer>(Arrays.asList(6, 7, 8)),
                    new ArrayList<Integer>(Arrays.asList(0, 3, 6)),
                    new ArrayList<Integer>(Arrays.asList(1, 4, 7)),
                    new ArrayList<Integer>(Arrays.asList(2, 5, 8)),
                    new ArrayList<Integer>(Arrays.asList(0, 4, 8)),
                    new ArrayList<Integer>(Arrays.asList(2, 4, 6))));
        for (ArrayList<Integer> comb : winCombs) {
            if (g.playerO.containsAll(comb) || g.playerX.containsAll(comb)){
                return true;
            }
        }
        return g.getBoard().isFull();
    }

    private int getScore(Game g) {
        HashSet<ArrayList<Integer>> winCombs = new HashSet<>(Arrays
            .asList(new ArrayList<Integer>(Arrays.asList(0, 1, 2)),
                    new ArrayList<Integer>(Arrays.asList(3, 4, 5)),
                    new ArrayList<Integer>(Arrays.asList(6, 7, 8)),
                    new ArrayList<Integer>(Arrays.asList(0, 3, 6)),
                    new ArrayList<Integer>(Arrays.asList(1, 4, 7)),
                    new ArrayList<Integer>(Arrays.asList(2, 5, 8)),
                    new ArrayList<Integer>(Arrays.asList(0, 4, 8)),
                    new ArrayList<Integer>(Arrays.asList(2, 4, 6))));
        for (ArrayList<Integer> comb : winCombs) {
            if (g.playerX.containsAll(comb)) {
                return -1;
            } else if (g.playerO.containsAll(comb)) {
                return 1;
            }
        }
        return 0;
    }
}