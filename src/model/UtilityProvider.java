package model;

public class UtilityProvider extends map.Cell {
    private final UtilityType utilityType;
    private final int capacity;

    public UtilityProvider(int row, int col, char symbol, UtilityType utilityType) {
        super(row, col, symbol);
        this.utilityType = utilityType;
        this.capacity = 100;
    }

    public UtilityType getUtilityType() {
        return utilityType;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }
}