import java.util.List;

public interface WfcTile {
    String getName();

    List<WfcTile> getWestNeighbors();
    List<WfcTile> getNorthNeighbors();
    List<WfcTile> getEastNeighbors();
    List<WfcTile> getSouthNeighbors();

    // Optional convenience methods (you can implement them with default methods)
    default void addNeighborToTheWest(WfcTile neighbor) {}
    default void addNeighborToTheNorth(WfcTile neighbor) {}
    default void addNeighborToTheEast(WfcTile neighbor) {}
    default void addNeighborToTheSouth(WfcTile neighbor) {}
}
