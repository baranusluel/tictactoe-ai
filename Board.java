import java.util.ArrayList;

public class Board {

    private Cell[] cells;
    private int cellsTaken = 0;

    public Cell getCell(int pos) {
        return cells[pos];
    }

    public Board() {
        cells = new Cell[]{ new Cell(0), new Cell(1), new Cell(2), new Cell(3),
            new Cell(4), new Cell(5), new Cell(6), new Cell(7), new Cell(8) };
    }

    public boolean isFull() {
        return cellsTaken == 9;
    }

    public void takeCell() {
        cellsTaken++;
    }
}
