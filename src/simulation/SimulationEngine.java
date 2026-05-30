package simulation;

import map.CityMap;

public class SimulationEngine {

    private final UtilityDistributor utilityDistributor;
    private final CityMap cityMap;
    private final ResourcePool pool = new ResourcePool();

    public SimulationEngine(CityMap cityMap) {
        this.cityMap = cityMap;
        this.utilityDistributor = new UtilityDistributor(cityMap);
    }

    private void distributeUtilities() {}

    public void run(int ticks){}

    private void distributeServices(){}

    private void distributeResources(){}

    private void updateZones(){}

    private void collectProduction(){}

    private void printState(){}


}
