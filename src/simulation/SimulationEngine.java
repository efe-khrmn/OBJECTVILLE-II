package simulation;

import map.CityMap;
import model.*;

import java.util.List;

public class SimulationEngine {
    private final UtilityDistributor utilityDistributor;
    private final CityMap cityMap;
    private final ResourcePool pool = new ResourcePool();

    public SimulationEngine(CityMap cityMap) {
        this.cityMap = cityMap;
        this.utilityDistributor = new UtilityDistributor(cityMap);
    }

    private void distributeUtilities() {
        for (UtilityProvider provider : cityMap.getUtilityProviders()) {
            utilityDistributor.distribute(provider);
        }
    }

    public void run(int ticks) {
        for (int t = 1; t <= ticks; t++) {
            System.out.println("----- TICK " + t + " -----");

            distributeServices();
            distributeUtilities();
            distributeResources();
            updateZones();
            collectProduction();

            printState();
        }
    }

    private void distributeServices() {
        List<ServiceBuilding> services = cityMap.getServiceBuildings();
        List<Zone> zones = cityMap.getZones();

        for (ServiceBuilding service : services) {
            for (Zone zone : zones) {
                int dist = Math.abs(service.getRow() - zone.getRow()) +
                        Math.abs(service.getCol() - zone.getCol());

                if (dist <= service.getRange()) {
                    switch (service.getServiceType()) {
                        case SECURITY -> zone.receiveSecurity();
                        case HEALTH -> zone.receiveHealth();
                        case EDUCATION -> zone.receiveEducation();
                    }
                }
            }
        }
    }

    private void distributeResources() {
        List<Zone> zones = cityMap.getZones();

        int industrialCount = 0;
        int commercialCount = 0;
        int housingCount = 0;

        for (Zone z : zones) {
            if (z instanceof IndustrialZone) industrialCount++;
            if (z instanceof CommercialZone) commercialCount++;
            if (z instanceof HousingZone) housingCount++;
        }

        int popShare = industrialCount + commercialCount == 0 ? 0 :
                pool.totalPopulation / (industrialCount + commercialCount);

        int goodsShare = commercialCount == 0 ? 0 :
                pool.totalGoods / commercialCount;

        int lifeShare = housingCount == 0 ? 0 :
                pool.totalLifestyle / housingCount;

        for (Zone z : zones) {
            if (z instanceof IndustrialZone || z instanceof CommercialZone) {
                z.receivePopulation(popShare);
            }
            if (z instanceof CommercialZone) {
                z.receiveGoods(goodsShare);
            }
            if (z instanceof HousingZone) {
                z.receiveLifestyle(lifeShare);
            }
        }

        pool.reset();
    }

    private void updateZones() {
        for (Zone z : cityMap.getZones()) {
            z.update();
        }
    }

    private void collectProduction() {
        for (Zone z : cityMap.getZones()) {

            if (z instanceof HousingZone) {
                pool.totalPopulation += z.getCurrentOutput();
            }

            if (z instanceof IndustrialZone) {
                pool.totalGoods += z.getCurrentOutput();
            }

            if (z instanceof CommercialZone) {
                pool.totalLifestyle += z.getCurrentOutput();
            }
        }
    }

    private void printState() {
        for (Zone z : cityMap.getZones()) {
            System.out.println(
                    z.getSymbol() +
                            " (" + z.getRow() + "," + z.getCol() + ") level=" +
                            z.getLevel() +
                            " output=" + z.getCurrentOutput()
            );
        }
    }
}
