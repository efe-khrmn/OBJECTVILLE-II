package objectville.model;

public abstract class Cell {
    protected int row;
    protected int col;
    protected char symbol;

    public Cell(int row, int col, char symbol) {
        this.row = row;
        this.col = col;
        this.symbol = symbol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isConnectable() {
        return false;
    }

    public boolean isZone() {
        return false;
    }
}