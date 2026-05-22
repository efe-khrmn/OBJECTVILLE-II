package model;

public class PowerPlant extends UtilityProvider {
    public PowerPlant(int row, int col) {
        super(row, col, 'P', UtilityType.ELECTRICITY);
    }
}