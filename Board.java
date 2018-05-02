import java.util.ArrayList;

public class Board {

    private Cell[] cells;
    private int cellsTaken = 0;

    public Cell getCell(int pos) {
        return cells[pos];
    }

    public Board() {
        cells = new Cell[]{new Cell(), new Cell(), new Cell(), new Cell(),
            new Cell(), new Cell(), new Cell(), new Cell(), new Cell()} ;
    }

    public boolean isFull() {
        return cellsTaken == 9;
    }

    //prints current board
    public void print() {
        System.out.println("+---+---+---+");
        for (int i = 0; i < 9; i++) {
            if (i == 2 | i == 5 | i == 8) {
                System.out.println("| " + cells[i].getPlayer() + " |");
                System.out.println("+---+---+---+");
            } else {
                System.out.print("| " + cells[i].getPlayer() + " ");
            }
        }
    }

    public void takeCell() {
        cellsTaken++;
    }

    //prints a board with numbers as a hint for the user
    public void printDemo() {
        System.out.println("+---+---+---+");
        for (int i = 0; i < 9; i++) {
            if (i == 2 | i == 5 | i == 8) {
                System.out.println("| " + i + " |");
                System.out.println("+---+---+---+");
            } else {
                System.out.print("| " + i + " ");
            }
        }
    }
}