package model;

public class PoliceStation extends ServiceBuilding {
    public PoliceStation(int row, int col) {
        super(row, col, 'F', ServiceType.SECURITY, 5);
    }
}