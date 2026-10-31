import java.util.ArrayList;
import java.util.List;

public class Tile {
    private final String name;
    private final List<Tile> WestNeighbors  = new ArrayList<>();
    private final List<Tile> NorthNeighbors = new ArrayList<>();
    private final List<Tile> EastNeighbors = new ArrayList<>();
    private final List<Tile> SouthNeighbors  = new ArrayList<>();

    public Tile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // ===== Add methods (exactly as you requested) =====
    public void addNeighborToTheWest(Tile neighbor) {
        if (neighbor != null && !WestNeighbors.contains(neighbor)) {
            WestNeighbors.add(neighbor);
        }
    }

    public void addNeighborToTheNorth(Tile neighbor) {
        if (neighbor != null && !NorthNeighbors.contains(neighbor)) {
            NorthNeighbors.add(neighbor);
        }
    }

    public void addNeighborToTheEast(Tile neighbor) {
        if (neighbor != null && !EastNeighbors.contains(neighbor)) {
            EastNeighbors.add(neighbor);
        }
    }

    public void addNeighborToTheSouth(Tile neighbor) {
        if (neighbor != null && !SouthNeighbors.contains(neighbor)) {
            SouthNeighbors.add(neighbor);
        }
    }

    // ===== Getters the WFC will use =====
    public List<Tile> getWestNeighbors()  { return WestNeighbors; }
    public List<Tile> getNorthNeighbors() { return NorthNeighbors; }
    public List<Tile> getEastNeighbors() { return EastNeighbors; }
    public List<Tile> getSouthNeighbors()  { return SouthNeighbors; }

    @Override
    public String toString() {
        return name;
    }
}
