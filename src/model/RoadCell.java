package model;

public class RoadCell extends model.Cell {
    public RoadCell(int row, int col) {
        super(row, col, 'R');
    }

    @Override
    public boolean isConnectable() {
        return true;
    }
}