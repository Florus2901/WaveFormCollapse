import java.util.ArrayList;
import java.util.List;

public class Tile implements WfcTile{
    private final String name;
    private final List<WfcTile> WestNeighbors  = new ArrayList<>();
    private final List<WfcTile> NorthNeighbors = new ArrayList<>();
    private final List<WfcTile> EastNeighbors = new ArrayList<>();
    private final List<WfcTile> SouthNeighbors  = new ArrayList<>();

    public Tile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addNeighborToTheWest(WfcTile neighbor) {
        if (neighbor != null && !WestNeighbors.contains(neighbor)) {
            WestNeighbors.add(neighbor);
            if(!neighbor.getEastNeighbors().contains(this)){
                neighbor.addNeighborToTheEast(this);
            }
        }
    }

    public void addNeighborToTheNorth(WfcTile neighbor) {
        if (neighbor != null && !NorthNeighbors.contains(neighbor)) {
            NorthNeighbors.add(neighbor);
            if(!neighbor.getSouthNeighbors().contains(this)) {
                neighbor.addNeighborToTheSouth(this);
            }
        }
    }

    public void addNeighborToTheEast(WfcTile neighbor) {
        if (neighbor != null && !EastNeighbors.contains(neighbor)) {
            EastNeighbors.add(neighbor);
            if(!neighbor.getWestNeighbors().contains(this)) {
                neighbor.addNeighborToTheWest(this);
            }
        }
    }

    public void addNeighborToTheSouth(WfcTile neighbor) {
        if (neighbor != null && !SouthNeighbors.contains(neighbor)) {
            SouthNeighbors.add(neighbor);
            if(!neighbor.getNorthNeighbors().contains(this)) {
                neighbor.addNeighborToTheNorth(this);
            }
        }
    }

    // ===== Getters the WFC will use =====
    public List<WfcTile> getWestNeighbors()  { return WestNeighbors; }
    public List<WfcTile> getNorthNeighbors() { return NorthNeighbors; }
    public List<WfcTile> getEastNeighbors() { return EastNeighbors; }
    public List<WfcTile> getSouthNeighbors()  { return SouthNeighbors; }

    @Override
    public String toString() {
        return name;
    }
}
