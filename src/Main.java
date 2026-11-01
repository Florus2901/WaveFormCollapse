import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<WfcTile> tiles;
    public static int w = 10, h = 10, d = 3;
    public static Tile[][] grid;
    public static WaveFormCollapse wfc;

    public static void main(String[] args) {
        tiles = new ArrayList<>();

        Tile water = new Tile("Water", true, false);
        Tile grass = new Tile("Grass", true, false );
        Tile forest = new Tile("Forest", true, false);
        Tile hills = new Tile("Hills", true, false);
        Tile mountain = new Tile("Mountain", true, false);
        Tile air = new Tile("Air", false, true);
        Tile cloud = new Tile("Cloud", false, true);
        tiles.add(water);
        tiles.add(grass);
        tiles.add(forest);
        tiles.add(hills);
        tiles.add(mountain);
        tiles.add(air);
        tiles.add(cloud);

        // ===== WATER =====
        water.addNeighborToTheWest(water);
        water.addNeighborToTheNorth(water);
        water.addNeighborToTheAbove(air);

        water.addNeighborToTheWest(grass);
        water.addNeighborToTheNorth(grass);
        water.addNeighborToTheEast(grass);
        water.addNeighborToTheSouth(grass);

// ===== GRASS =====
        grass.addNeighborToTheWest(grass);
        grass.addNeighborToTheNorth(grass);
        grass.addNeighborToTheAbove(air);

        grass.addNeighborToTheWest(forest);
        grass.addNeighborToTheNorth(forest);
        grass.addNeighborToTheEast(forest);
        grass.addNeighborToTheSouth(forest);

        grass.addNeighborToTheWest(hills);
        grass.addNeighborToTheNorth(hills);
        grass.addNeighborToTheEast(hills);
        grass.addNeighborToTheSouth(hills);

// ===== FOREST =====
        forest.addNeighborToTheWest(forest);
        forest.addNeighborToTheNorth(forest);
        forest.addNeighborToTheAbove(air);

        forest.addNeighborToTheWest(hills);
        forest.addNeighborToTheNorth(hills);
        forest.addNeighborToTheEast(hills);
        forest.addNeighborToTheSouth(hills);

// ===== HILLS =====
        hills.addNeighborToTheWest(hills);
        hills.addNeighborToTheNorth(hills);
        hills.addNeighborToTheAbove(air);

        hills.addNeighborToTheWest(mountain);
        hills.addNeighborToTheNorth(mountain);
        hills.addNeighborToTheEast(mountain);
        hills.addNeighborToTheSouth(mountain);

// ===== MOUNTAIN =====
        mountain.addNeighborToTheWest(mountain);
        mountain.addNeighborToTheNorth(mountain);
        mountain.addNeighborToTheEast(mountain);
        mountain.addNeighborToTheSouth(mountain);
        mountain.addNeighborToTheSouth(air);
        mountain.addNeighborToTheSouth(cloud);

        mountain.addNeighborToTheWest(hills);
        mountain.addNeighborToTheNorth(hills);
        mountain.addNeighborToTheEast(hills);
        mountain.addNeighborToTheSouth(hills);

// ===== AIR (sky layer) =====
        air.addNeighborToTheWest(air);
        air.addNeighborToTheNorth(air);
        air.addNeighborToTheEast(air);
        air.addNeighborToTheSouth(air);

        air.addNeighborToTheWest(cloud);
        air.addNeighborToTheNorth(cloud);
        air.addNeighborToTheEast(cloud);
        air.addNeighborToTheSouth(cloud);


// Air can sit above any land tile
        air.addNeighborToTheBelow(water);
        air.addNeighborToTheBelow(grass);
        air.addNeighborToTheBelow(forest);
        air.addNeighborToTheBelow(hills);
        air.addNeighborToTheBelow(mountain);

// Air can also be directly below Cloud (stacked sky)
        air.addNeighborToTheAbove(air);
        air.addNeighborToTheAbove(cloud);

// ===== CLOUD =====
        cloud.addNeighborToTheWest(cloud);
        cloud.addNeighborToTheNorth(cloud);
        cloud.addNeighborToTheEast(cloud);
        cloud.addNeighborToTheSouth(cloud);

        cloud.addNeighborToTheWest(air);
        cloud.addNeighborToTheNorth(air);
        cloud.addNeighborToTheEast(air);
        cloud.addNeighborToTheSouth(air);

// Cloud is only allowed directly above Air or Mountain
        cloud.addNeighborToTheBelow(air);
        cloud.addNeighborToTheBelow(mountain);

// Cloud can have Air directly above it
        cloud.addNeighborToTheAbove(air);


        List<PrePlacedTile> forcedWater = List.of(
                new PrePlacedTile(water, 0, 0, 0),
                new PrePlacedTile(water, 1, 0, 0),
                new PrePlacedTile(water, 2, 0, 0),
                new PrePlacedTile(water, 3, 0, 0),
                new PrePlacedTile(water, 4, 0, 0),
                new PrePlacedTile(water, 5, 0, 0),
                new PrePlacedTile(water, 6, 0, 0),
                new PrePlacedTile(water, 7, 0, 0),
                new PrePlacedTile(water, 8, 0, 0),
                new PrePlacedTile(water, 9, 0, 0)
        );

        wfc = new WaveFormCollapse(w,h, d, tiles);
//        wfc.setWfcTile(water, 0, 0, 0);
//        wfc.setWfcTile(mountain, 0, 1);
        wfc.run();
        System.out.println(wfc.getGridAsJsonString());
        wfc.printSeed();
    }
}
