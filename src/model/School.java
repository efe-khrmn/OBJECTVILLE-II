package model;

public class School extends ServiceBuilding {
    public School(int row, int col) {
        super(row, col, 'S', ServiceType.EDUCATION, 5);
    }
}