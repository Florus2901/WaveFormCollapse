import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Tile> tiles;
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
        water.addNeighborToTheEast(water);
        water.addNeighborToTheSouth(water);
        water.addNeighborToTheWest(grass);
        water.addNeighborToTheNorth(grass);
        water.addNeighborToTheEast(grass);
        water.addNeighborToTheSouth(grass);

        // ===== GRASS =====
        grass.addNeighborToTheWest(grass);
        grass.addNeighborToTheNorth(grass);
        grass.addNeighborToTheEast(grass);
        grass.addNeighborToTheSouth(grass);
        grass.addNeighborToTheWest(water);
        grass.addNeighborToTheNorth(water);
        grass.addNeighborToTheEast(water);
        grass.addNeighborToTheSouth(water);

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
        forest.addNeighborToTheEast(forest);
        forest.addNeighborToTheSouth(forest);

        forest.addNeighborToTheWest(grass);
        forest.addNeighborToTheNorth(grass);
        forest.addNeighborToTheEast(grass);
        forest.addNeighborToTheSouth(grass);

        forest.addNeighborToTheWest(hills);
        forest.addNeighborToTheNorth(hills);
        forest.addNeighborToTheEast(hills);
        forest.addNeighborToTheSouth(hills);

        // ===== HILLS =====
        hills.addNeighborToTheWest(hills);
        hills.addNeighborToTheNorth(hills);
        hills.addNeighborToTheEast(hills);
        hills.addNeighborToTheSouth(hills);

        hills.addNeighborToTheWest(grass);
        hills.addNeighborToTheNorth(grass);
        hills.addNeighborToTheEast(grass);
        hills.addNeighborToTheSouth(grass);

        hills.addNeighborToTheWest(forest);
        hills.addNeighborToTheNorth(forest);
        hills.addNeighborToTheEast(forest);
        hills.addNeighborToTheSouth(forest);

        hills.addNeighborToTheWest(mountain);
        hills.addNeighborToTheNorth(mountain);
        hills.addNeighborToTheEast(mountain);
        hills.addNeighborToTheSouth(mountain);

        // ===== MOUNTAIN =====
        mountain.addNeighborToTheWest(mountain);
        mountain.addNeighborToTheNorth(mountain);
        mountain.addNeighborToTheEast(mountain);
        mountain.addNeighborToTheSouth(mountain);

        mountain.addNeighborToTheWest(hills);
        mountain.addNeighborToTheNorth(hills);
        mountain.addNeighborToTheEast(hills);
        mountain.addNeighborToTheSouth(hills);

        List<PrePlacedTile> forcedWater = List.of(
                new PrePlacedTile(water, 0, 0),
                new PrePlacedTile(water, 0, 1),
                new PrePlacedTile(water, 0, 2),
                new PrePlacedTile(water, 0, 3),
                new PrePlacedTile(water, 0, 4),
                new PrePlacedTile(water, 0, 5),
                new PrePlacedTile(water, 0, 6),
                new PrePlacedTile(water, 0, 7),
                new PrePlacedTile(water, 0, 8),
                new PrePlacedTile(water, 0, 9)
        );

        wfc = new WaveFormCollapse(w,h, tiles, 1793446735379L, forcedWater);

        wfc.run();
        wfc.printGrid();
        wfc.printSeed();
    }
}
