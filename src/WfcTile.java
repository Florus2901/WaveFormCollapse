import java.util.Set;

public interface WfcTile {
    String getName();

    default boolean isGround() { return false; }
    default boolean isAir(){ return false; }

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

    default boolean addToWestList(WfcTile neighbor) { return false; }
    default boolean addToNorthList(WfcTile neighbor) { return false; }
    default boolean addToEastList(WfcTile neighbor) { return false; }
    default boolean addToSouthList(WfcTile neighbor) { return false; }
    default boolean addToAboveList(WfcTile neighbor) { return false; }
    default boolean addToBelowList(WfcTile neighbor) { return false; }
    default boolean addToWestNorthList(WfcTile neighbor) { return false; }
    default boolean addToWestSouthList(WfcTile neighbor) { return false; }
    default boolean addToEastNorthList(WfcTile neighbor) { return false; }
    default boolean addToEastSouthList(WfcTile neighbor) { return false; }
    default boolean addToWestAboveList(WfcTile neighbor) { return false; }
    default boolean addToWestBelowList(WfcTile neighbor) { return false; }
    default boolean addToEastAboveList(WfcTile neighbor) { return false; }
    default boolean addToEastBelowList(WfcTile neighbor) { return false; }
    default boolean addToNorthAboveList(WfcTile neighbor) { return false; }
    default boolean addToNorthBelowList(WfcTile neighbor) { return false; }
    default boolean addToSouthAboveList(WfcTile neighbor) { return false; }
    default boolean addToSouthBelowList(WfcTile neighbor) { return false; }
    default boolean addToWestNorthAboveList(WfcTile neighbor) { return false; }
    default boolean addToWestNorthBelowList(WfcTile neighbor) { return false; }
    default boolean addToWestSouthAboveList(WfcTile neighbor) { return false; }
    default boolean addToWestSouthBelowList(WfcTile neighbor) { return false; }
    default boolean addToEastNorthAboveList(WfcTile neighbor) { return false; }
    default boolean addToEastNorthBelowList(WfcTile neighbor) { return false; }
    default boolean addToEastSouthAboveList(WfcTile neighbor) { return false; }
    default boolean addToEastSouthBelowList(WfcTile neighbor) { return false; }

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
