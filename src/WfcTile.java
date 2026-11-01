import java.util.List;

public interface WfcTile {
    String getName();

    default Boolean isGround() { return true; }
    default Boolean isAir(){ return true; }

    List<WfcTile> getWestNeighbors();
    List<WfcTile> getNorthNeighbors();
    List<WfcTile> getEastNeighbors();
    List<WfcTile> getSouthNeighbors();
    List<WfcTile> getAboveNeighbors();
    List<WfcTile> getBelowNeighbors();

    default void addNeighborToTheWest(WfcTile neighbor) {}
    default void addNeighborToTheNorth(WfcTile neighbor) {}
    default void addNeighborToTheEast(WfcTile neighbor) {}
    default void addNeighborToTheSouth(WfcTile neighbor) {}
    default void addNeighborToTheAbove(WfcTile neighbor) {}
    default void addNeighborToTheBelow(WfcTile neighbor) {}
}
