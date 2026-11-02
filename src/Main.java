import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<WfcTile> tiles;
    public static int w = 10, h = 10, d = 4;
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
            for(WfcTile neighbors : tiles) {
                if(tile.isGround() && neighbors.isGround() || tile.isAir() && neighbors.isAir()){
                    tile.addNeighborToTheNorth(neighbors);
                    tile.addNeighborToTheWestNorth(neighbors);
                    tile.addNeighborToTheWest(neighbors);
                    tile.addNeighborToTheWestSouth(neighbors);
                    tile.addNeighborToTheSouth(neighbors);
                    tile.addNeighborToTheEastSouth(neighbors);
                    tile.addNeighborToTheEast(neighbors);
                    tile.addNeighborToTheEastNorth(neighbors);
                }
                if(tile.isGround() && neighbors.isGround()){
                    tile.addNeighborToTheBelow(neighbors);
                    tile.addNeighborToTheNorthBelow(neighbors);
                    tile.addNeighborToTheEastNorthBelow(neighbors);
                    tile.addNeighborToTheEastBelow(neighbors);
                    tile.addNeighborToTheEastSouthBelow(neighbors);
                    tile.addNeighborToTheSouthBelow(neighbors);
                    tile.addNeighborToTheWestSouthBelow(neighbors);
                    tile.addNeighborToTheWestBelow(neighbors);
                    tile.addNeighborToTheWestNorthBelow(neighbors);
                    tile.addNeighborToTheAbove(neighbors);
                    tile.addNeighborToTheNorthAbove(neighbors);
                    tile.addNeighborToTheEastNorthAbove(neighbors);
                    tile.addNeighborToTheEastAbove(neighbors);
                    tile.addNeighborToTheEastSouthAbove(neighbors);
                    tile.addNeighborToTheSouthAbove(neighbors);
                    tile.addNeighborToTheWestSouthAbove(neighbors);
                    tile.addNeighborToTheWestAbove(neighbors);
                    tile.addNeighborToTheWestNorthAbove(neighbors);
                }
                if(tile.isAir() && neighbors.isAir()){
                    tile.addNeighborToTheAbove(neighbors);
                    tile.addNeighborToTheNorthAbove(neighbors);
                    tile.addNeighborToTheEastNorthAbove(neighbors);
                    tile.addNeighborToTheEastAbove(neighbors);
                    tile.addNeighborToTheEastSouthAbove(neighbors);
                    tile.addNeighborToTheSouthAbove(neighbors);
                    tile.addNeighborToTheWestSouthAbove(neighbors);
                    tile.addNeighborToTheWestAbove(neighbors);
                    tile.addNeighborToTheWestNorthAbove(neighbors);
                    tile.addNeighborToTheBelow(neighbors);
                    tile.addNeighborToTheNorthBelow(neighbors);
                    tile.addNeighborToTheEastNorthBelow(neighbors);
                    tile.addNeighborToTheEastBelow(neighbors);
                    tile.addNeighborToTheEastSouthBelow(neighbors);
                    tile.addNeighborToTheSouthBelow(neighbors);
                    tile.addNeighborToTheWestSouthBelow(neighbors);
                    tile.addNeighborToTheWestBelow(neighbors);
                    tile.addNeighborToTheWestNorthBelow(neighbors);
                }
                if(tile.isGround() && neighbors.isAir()){
                    tile.addNeighborToTheAbove(neighbors);
                    tile.addNeighborToTheNorthAbove(neighbors);
                    tile.addNeighborToTheEastNorthAbove(neighbors);
                    tile.addNeighborToTheEastAbove(neighbors);
                    tile.addNeighborToTheEastSouthAbove(neighbors);
                    tile.addNeighborToTheSouthAbove(neighbors);
                    tile.addNeighborToTheWestSouthAbove(neighbors);
                    tile.addNeighborToTheWestAbove(neighbors);
                    tile.addNeighborToTheWestNorthAbove(neighbors);
                }
                if(tile.isAir() && neighbors.isGround()){
                    tile.addNeighborToTheBelow(neighbors);
                    tile.addNeighborToTheNorthBelow(neighbors);
                    tile.addNeighborToTheEastNorthBelow(neighbors);
                    tile.addNeighborToTheEastBelow(neighbors);
                    tile.addNeighborToTheEastSouthBelow(neighbors);
                    tile.addNeighborToTheSouthBelow(neighbors);
                    tile.addNeighborToTheWestSouthBelow(neighbors);
                    tile.addNeighborToTheWestBelow(neighbors);
                    tile.addNeighborToTheWestNorthBelow(neighbors);
                }
            }
        }
    }

}
