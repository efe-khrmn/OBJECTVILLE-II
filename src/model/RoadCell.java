package model;

public class RoadCell extends map.Cell {
    public RoadCell(int row, int col) {
        super(row, col, 'R');
    }

    @Override
    public boolean isConnectable() {
        return true;
    }
}