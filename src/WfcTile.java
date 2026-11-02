import java.util.Set;

public interface WfcTile {
    String getName();

    default Boolean isGround() { return true; }
    default Boolean isAir(){ return true; }

    // Face (6)
    Set<WfcTile> getWestNeighbors();
    Set<WfcTile> getNorthNeighbors();
    Set<WfcTile> getEastNeighbors();
    Set<WfcTile> getSouthNeighbors();
    Set<WfcTile> getAboveNeighbors();
    Set<WfcTile> getBelowNeighbors();

    // Edge (12)
    Set<WfcTile> getWestNorthNeighbors();
    Set<WfcTile> getWestSouthNeighbors();
    Set<WfcTile> getEastNorthNeighbors();
    Set<WfcTile> getEastSouthNeighbors();
    Set<WfcTile> getWestAboveNeighbors();
    Set<WfcTile> getWestBelowNeighbors();
    Set<WfcTile> getEastAboveNeighbors();
    Set<WfcTile> getEastBelowNeighbors();
    Set<WfcTile> getNorthAboveNeighbors();
    Set<WfcTile> getNorthBelowNeighbors();
    Set<WfcTile> getSouthAboveNeighbors();
    Set<WfcTile> getSouthBelowNeighbors();

    Set<WfcTile> getWestNorthAboveNeighbors();
    Set<WfcTile> getWestNorthBelowNeighbors();
    Set<WfcTile> getWestSouthAboveNeighbors();
    Set<WfcTile> getWestSouthBelowNeighbors();
    Set<WfcTile> getEastNorthAboveNeighbors();
    Set<WfcTile> getEastNorthBelowNeighbors();
    Set<WfcTile> getEastSouthAboveNeighbors();
    Set<WfcTile> getEastSouthBelowNeighbors();

    default void addNeighborToTheWest(WfcTile neighbor) {}
    default void addNeighborToTheNorth(WfcTile neighbor) {}
    default void addNeighborToTheEast(WfcTile neighbor) {}
    default void addNeighborToTheSouth(WfcTile neighbor) {}
    default void addNeighborToTheAbove(WfcTile neighbor) {}
    default void addNeighborToTheBelow(WfcTile neighbor) {}
    default void addNeighborToTheWestNorth(WfcTile neighbor){}
    default void addNeighborToTheWestSouth(WfcTile neighbor){}
    default void addNeighborToTheEastNorth(WfcTile neighbor){}
    default void addNeighborToTheEastSouth(WfcTile neighbor){}
    default void addNeighborToTheWestAbove(WfcTile neighbor){}
    default void addNeighborToTheWestBelow(WfcTile neighbor){}
    default void addNeighborToTheEastAbove(WfcTile neighbor){}
    default void addNeighborToTheEastBelow(WfcTile neighbor){}
    default void addNeighborToTheNorthAbove(WfcTile neighbor){}
    default void addNeighborToTheNorthBelow(WfcTile neighbor){}
    default void addNeighborToTheSouthAbove(WfcTile neighbor){}
    default void addNeighborToTheSouthBelow(WfcTile neighbor){}
    default void addNeighborToTheWestNorthAbove(WfcTile neighbor){}
    default void addNeighborToTheWestNorthBelow(WfcTile neighbor){}
    default void addNeighborToTheWestSouthAbove(WfcTile neighbor){}
    default void addNeighborToTheWestSouthBelow(WfcTile neighbor){}
    default void addNeighborToTheEastNorthAbove(WfcTile neighbor){}
    default void addNeighborToTheEastNorthBelow(WfcTile neighbor){}
    default void addNeighborToTheEastSouthAbove(WfcTile neighbor){}
    default void addNeighborToTheEastSouthBelow(WfcTile neighbor){}


}
