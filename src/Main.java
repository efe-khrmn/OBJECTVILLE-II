import map.CityMap;
import map.MapLoader;
import simulation.SimulationEngine;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar ObjectVilleGame.jar <mapfile> <ticks>");
            return;
        }

        String mapFile = args[0];
        int ticks;

        try {
            ticks = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: ticks must be an integer.");
            return;
        }

        MapLoader loader = new MapLoader();
        CityMap cityMap = loader.loadFromFile(mapFile);
        SimulationEngine engine = new SimulationEngine(cityMap);
        engine.run(ticks);
    }
}
