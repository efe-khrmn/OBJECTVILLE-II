package simulation;

import map.CityMap;
import model.*;
import map.Cell;

import java.util.LinkedList;
import java.util.Queue;

public class UtilityDistributor {
    private final CityMap cityMap;

    public UtilityDistributor(CityMap cityMap) {
        this.cityMap = cityMap;
    }

    public void distribute(UtilityProvider provider) {
        boolean[][] visited = new boolean[cityMap.getRows()][cityMap.getCols()];
        Queue<Cell> queue = new LinkedList<>();

        int remaining = provider.getCapacity();

        queue.add(provider);
        visited[provider.getRow()][provider.getCol()] = true;

        while (!queue.isEmpty() && remaining > 0) {
            Cell current = queue.poll();

            if (current instanceof Zone zone) {
                int demand = zone.getNextDemand();
                int delivered = Math.min(demand, remaining);

                deliverToZone(zone, provider.getUtilityType(), delivered);

                remaining -= delivered;
            }

            int row = current.getRow();
            int col = current.getCol();

            addIfValid(queue, visited, row - 1, col);
            addIfValid(queue, visited, row + 1, col);
            addIfValid(queue, visited, row, col - 1);
            addIfValid(queue, visited, row, col + 1);
        }
    }

    private void addIfValid(Queue<Cell> queue, boolean[][] visited, int row, int col) {
        if (!cityMap.isInside(row, col)) {
            return;
        }

        if (visited[row][col]) {
            return;
        }

        Cell cell = cityMap.getCell(row, col);

        if (!cell.isConnectable()) {
            return;
        }

        visited[row][col] = true;
        queue.add(cell);
    }

    private void deliverToZone(Zone zone, UtilityType utilityType, int amount) {
        String utilName = switch (utilityType) {
            case ELECTRICITY -> "electricity";
            case WATER -> "water";
            case INTERNET -> "internet";
        };
        switch (utilityType) {
            case ELECTRICITY -> zone.receiveElectricity(amount);
            case WATER -> zone.receiveWater(amount);
            case INTERNET -> zone.receiveInternet(amount);
        }
        System.out.println(zone.getTypeName() + " at (" + zone.getRow() + "," + zone.getCol() + ") received " + amount + " " + utilName);
    }

    private String getZoneTypeName(Zone zone) {
        return zone.getTypeName();
    }
}