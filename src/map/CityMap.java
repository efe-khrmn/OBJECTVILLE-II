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

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public List<Zone> getZones() {
        List<Zone> zones = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Cell cell = grid[r][c];

                if (cell instanceof Zone) {
                    zones.add((Zone) cell);
                }
            }
        }

        return zones;
    }

    public List<UtilityProvider> getUtilityProviders() {
        List<UtilityProvider> providers = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Cell cell = grid[r][c];

                if (cell instanceof UtilityProvider) {
                    providers.add((UtilityProvider) cell);
                }
            }
        }

        return providers;
    }

    public List<ServiceBuilding> getServiceBuildings() {
        List<ServiceBuilding> services = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Cell cell = grid[r][c];

                if (cell instanceof ServiceBuilding) {
                    services.add((ServiceBuilding) cell);
                }
            }
        }

        return services;
    }

    public void printSymbols() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(grid[r][c].getSymbol() + " ");
            }
            System.out.println();
        }
    }
}
