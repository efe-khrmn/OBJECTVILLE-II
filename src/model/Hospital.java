package model;

public class Hospital extends ServiceBuilding {
    public Hospital(int row, int col) {
        super(row, col, 'D', ServiceType.HEALTH, 5);
    }
}