import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<WfcTile> tiles;
    public static int w = 10, h = 10;
    public static Tile[][] grid;
    public static WaveFormCollapse wfc;

    public static void main(String[] args) {
        tiles = new ArrayList<>();

        Tile water = new Tile("Water");
        Tile grass = new Tile("Grass");
        Tile forest = new Tile("Forest");
        Tile hills = new Tile("Hills");
        Tile mountain = new Tile("Mountain");
        tiles.add(water);
        tiles.add(grass);
        tiles.add(forest);
        tiles.add(hills);
        tiles.add(mountain);

        // ===== WATER =====
        water.addNeighborToTheWest(water);
        water.addNeighborToTheNorth(water);

        water.addNeighborToTheWest(grass);
        water.addNeighborToTheNorth(grass);
        water.addNeighborToTheEast(grass);
        water.addNeighborToTheSouth(grass);

        // ===== GRASS =====
        grass.addNeighborToTheWest(grass);
        grass.addNeighborToTheNorth(grass);

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

        forest.addNeighborToTheWest(hills);
        forest.addNeighborToTheNorth(hills);
        forest.addNeighborToTheEast(hills);
        forest.addNeighborToTheSouth(hills);

        // ===== HILLS =====
        hills.addNeighborToTheWest(hills);
        hills.addNeighborToTheNorth(hills);

        hills.addNeighborToTheWest(mountain);
        hills.addNeighborToTheNorth(mountain);
        hills.addNeighborToTheEast(mountain);
        hills.addNeighborToTheSouth(mountain);

//        // ===== MOUNTAIN =====
//        mountain.addNeighborToTheWest(mountain);
//        mountain.addNeighborToTheNorth(mountain);
//        mountain.addNeighborToTheEast(mountain);
//        mountain.addNeighborToTheSouth(mountain);
//
//        mountain.addNeighborToTheWest(hills);
//        mountain.addNeighborToTheNorth(hills);
//        mountain.addNeighborToTheEast(hills);
//        mountain.addNeighborToTheSouth(hills);

        List<PrePlacedTile> forcedWater = List.of(
                new PrePlacedTile(water, 0, 0),
                new PrePlacedTile(water, 1, 0),
                new PrePlacedTile(water, 2, 0),
                new PrePlacedTile(water, 3, 0),
                new PrePlacedTile(water, 4, 0),
                new PrePlacedTile(water, 5, 0),
                new PrePlacedTile(water, 6, 0),
                new PrePlacedTile(water, 7, 0),
                new PrePlacedTile(water, 8, 0),
                new PrePlacedTile(water, 9, 0)
        );

        wfc = new WaveFormCollapse(w,h, tiles);
//        wfc.setWfcTile(water, 0, 0);
//        wfc.setWfcTile(mountain, 0, 1);
        wfc.run();
        wfc.printGrid();
        wfc.printSeed();
    }
}
