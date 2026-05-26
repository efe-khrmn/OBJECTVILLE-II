package map;

import map.Cell;
import model.Zone;
import model.UtilityProvider;
import model.ServiceBuilding;

import java.util.ArrayList;
import java.util.List;

public class CityMap {
    private final Cell[][] grid;
    private final int rows;
    private final int cols;

    public CityMap(Cell[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public boolean isInside(int row, int col) {
        return row >= 0 &&
                row < rows &&
                col >= 0 &&
                col < cols;
    }
