package model;

public class ServiceBuilding extends map.Cell {
    private final ServiceType serviceType;
    private final int range;

    public ServiceBuilding(int row, int col, char symbol, ServiceType serviceType, int range) {
        super(row, col, symbol);
        this.serviceType = serviceType;
        this.range = range;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public int getRange() {
        return range;
    }
}