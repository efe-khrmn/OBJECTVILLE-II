package model;

public class WaterPump extends UtilityProvider {
    public WaterPump(int row, int col) {
        super(row, col, 'W', UtilityType.WATER);
    }
}