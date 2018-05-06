import java.util.Collections;
import java.util.ArrayList;

public class Minimax {

    private Game practiceGame;
    private Game game;
    private int choice;

    public Minimax(Game g) {
        game = g;
        try {
            practiceGame = (Game)g.clone();
        } catch (Exception e) {
            System.out.println("Game cloning didn't work so great");
        }
    }

    public void makeMove() {
        game.makeMove(calculateMove(practiceGame));
    }

    private int calculateMove(Game g) {
        if (isOver(g)) {
            return getScore(g);
        }
        ArrayList<Integer> scores;
        ArrayList<Integer> moves;
        for (int i = 0; i < 9; i++) {
            Cell c =  game.getBoard().getCell(i);
            if (!c.getIsTaken()) {
                Game possibleGame = (Game)g.clone();
                possibleGame.aiMakeMove(i);
                scores.add(calculateMove(possibleGame));
                moves.add(i);
            }
        }
        if (g.getCurrentPlayer().equals("X")) {
            int max = 3;
        } else {
            int min = 3;
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
            if (playerO.containsAll(comb) || playerX.containsAll(comb)){
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
            if (playerX.containsAll(comb)) {
                return -1;
            } else if (playerO.containsAll(comb)) {
                return 1;
            }
        }
        return 0;
    }
}