import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<WfcTile> tiles;
    public static int w = 100, h = 100, d = 1;
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

//        addNeighbor(water, water);
//        addNeighbor(water, grass);
//        addNeighbor(grass, grass);
//        addNeighbor(grass, forest);
//        addNeighbor(grass, hills);
//        addNeighbor(forest, forest);
//        addNeighbor(hills, hills);
//        addNeighbor(hills, mountain);
//        addNeighbor(mountain, mountain);
//
//        for(WfcTile tile : tiles){
//            addNeighbor(air, tile);
//            addNeighbor(cloud, tile);
//        }

        generateTileNeighbors(tiles);

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

        wfc = new WaveFormCollapse(w,h, d, tiles );
//        wfc.setWfcTile(water, 0, 0, 0);
//        wfc.setWfcTile(mountain, 0, 1);
        wfc.run();
        System.out.println(wfc.getGridAsJsonString());
        wfc.printSeed();
    }

    private static void generateTileNeighbors(List<WfcTile> tiles){
        for(WfcTile tile : tiles){
            for(WfcTile neighbor : tiles) {
                addNeighbor(tile, neighbor);
            }
        }
    }

    private static void addNeighbor(WfcTile tile, WfcTile neighbor){
        if (tile == null || neighbor == null) return;
        boolean bothGround = tile.isGround() && neighbor.isGround();
        boolean bothAir    = tile.isAir() && neighbor.isAir();

        if(bothGround || bothAir){
            tile.addNeighborToTheNorth(neighbor);
            tile.addNeighborToTheWestNorth(neighbor);
            tile.addNeighborToTheWest(neighbor);
            tile.addNeighborToTheWestSouth(neighbor);
            tile.addNeighborToTheSouth(neighbor);
            tile.addNeighborToTheEastSouth(neighbor);
            tile.addNeighborToTheEast(neighbor);
            tile.addNeighborToTheEastNorth(neighbor);
        }
        if(bothGround){
            tile.addNeighborToTheBelow(neighbor);
            tile.addNeighborToTheNorthBelow(neighbor);
            tile.addNeighborToTheEastNorthBelow(neighbor);
            tile.addNeighborToTheEastBelow(neighbor);
            tile.addNeighborToTheEastSouthBelow(neighbor);
            tile.addNeighborToTheSouthBelow(neighbor);
            tile.addNeighborToTheWestSouthBelow(neighbor);
            tile.addNeighborToTheWestBelow(neighbor);
            tile.addNeighborToTheWestNorthBelow(neighbor);
            tile.addNeighborToTheAbove(neighbor);
            tile.addNeighborToTheNorthAbove(neighbor);
            tile.addNeighborToTheEastNorthAbove(neighbor);
            tile.addNeighborToTheEastAbove(neighbor);
            tile.addNeighborToTheEastSouthAbove(neighbor);
            tile.addNeighborToTheSouthAbove(neighbor);
            tile.addNeighborToTheWestSouthAbove(neighbor);
            tile.addNeighborToTheWestAbove(neighbor);
            tile.addNeighborToTheWestNorthAbove(neighbor);
        }
        if(bothAir){
            tile.addNeighborToTheAbove(neighbor);
            tile.addNeighborToTheNorthAbove(neighbor);
            tile.addNeighborToTheEastNorthAbove(neighbor);
            tile.addNeighborToTheEastAbove(neighbor);
            tile.addNeighborToTheEastSouthAbove(neighbor);
            tile.addNeighborToTheSouthAbove(neighbor);
            tile.addNeighborToTheWestSouthAbove(neighbor);
            tile.addNeighborToTheWestAbove(neighbor);
            tile.addNeighborToTheWestNorthAbove(neighbor);
            tile.addNeighborToTheBelow(neighbor);
            tile.addNeighborToTheNorthBelow(neighbor);
            tile.addNeighborToTheEastNorthBelow(neighbor);
            tile.addNeighborToTheEastBelow(neighbor);
            tile.addNeighborToTheEastSouthBelow(neighbor);
            tile.addNeighborToTheSouthBelow(neighbor);
            tile.addNeighborToTheWestSouthBelow(neighbor);
            tile.addNeighborToTheWestBelow(neighbor);
            tile.addNeighborToTheWestNorthBelow(neighbor);
        }
        if(tile.isGround() && neighbor.isAir()){
            tile.addNeighborToTheAbove(neighbor);
            tile.addNeighborToTheNorthAbove(neighbor);
            tile.addNeighborToTheEastNorthAbove(neighbor);
            tile.addNeighborToTheEastAbove(neighbor);
            tile.addNeighborToTheEastSouthAbove(neighbor);
            tile.addNeighborToTheSouthAbove(neighbor);
            tile.addNeighborToTheWestSouthAbove(neighbor);
            tile.addNeighborToTheWestAbove(neighbor);
            tile.addNeighborToTheWestNorthAbove(neighbor);
        }
        if(tile.isAir() && neighbor.isGround()){
            tile.addNeighborToTheBelow(neighbor);
            tile.addNeighborToTheNorthBelow(neighbor);
            tile.addNeighborToTheEastNorthBelow(neighbor);
            tile.addNeighborToTheEastBelow(neighbor);
            tile.addNeighborToTheEastSouthBelow(neighbor);
            tile.addNeighborToTheSouthBelow(neighbor);
            tile.addNeighborToTheWestSouthBelow(neighbor);
            tile.addNeighborToTheWestBelow(neighbor);
            tile.addNeighborToTheWestNorthBelow(neighbor);
        }
    }

}
