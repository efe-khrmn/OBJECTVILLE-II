package map;

import model.*;
import map.Cell;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {
    public CityMap loadFromFile(String filePath) {
        List<String> lines = readLines(filePath);
        int rows = lines.size();
        int cols = lines.get(0).length();
        Cell[][] grid = new Cell[rows][cols];

        for (int r = 0; r < rows; r++) {
            String line = lines.get(r);
            for (int c = 0; c < cols; c++) {
                char symbol = line.charAt(c);
                grid[r][c] = createCell(symbol, r, c);
            }
        }
        return new CityMap(grid);
    }

    private List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    lines.add(line.trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Map file could not be read: " + filePath);
        }
        return lines;}
    private Cell createCell(char symbol, int row, int col) {
        return switch (symbol) {
            case 'H' -> new HousingZone(row, col);
            case 'I' -> new IndustrialZone(row, col);
            case 'C' -> new CommercialZone(row, col);
            case 'P' -> new PowerPlant(row, col);
            case 'W' -> new WaterPump(row, col);
            case 'T' -> new InternetHub(row, col);
            case 'F' -> new PoliceStation(row, col);
            case 'D' -> new Hospital(row, col);
            case 'S' -> new School(row, col);
            case 'R' -> new RoadCell(row, col);
            case 'E' -> new EmptyCell(row, col);
            default -> throw new RuntimeException("Unknown map symbol: " + symbol);
        };
    }
}
