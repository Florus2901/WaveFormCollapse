import java.util.ArrayList;
import java.util.List;

public class Tile implements WfcTile{
    private final String name;
    private boolean isGround;
    private boolean isAir;

    private final List<WfcTile> westNeighbors  = new ArrayList<>();
    private final List<WfcTile> northNeighbors = new ArrayList<>();
    private final List<WfcTile> eastNeighbors = new ArrayList<>();
    private final List<WfcTile> southNeighbors  = new ArrayList<>();
    private final List<WfcTile> aboveNeighbors  = new ArrayList<>();
    private final List<WfcTile> belowNeighbors  = new ArrayList<>();


    public Tile(String name) {
        this(name, true, true);
    }

    public Tile(String name, boolean isGround, boolean isAir) {
        this.name = name;
        this.isGround = isGround;
        this.isAir = isAir;
    }

    public String getName() {
        return name;
    }

    public void setGround(boolean isGround){ this.isGround = isGround; }
    public void setAir(boolean isAir){ this.isAir = isAir; }

    @Override
    public Boolean isGround() {
        return isGround;
    }

    @Override
    public Boolean isAir() {
        return isAir;
    }

    private void addToWestList(WfcTile neighbor) {
        if (neighbor != null && !westNeighbors.contains(neighbor)) {
            westNeighbors.add(neighbor);
        }
    }
    private void addToNorthList(WfcTile neighbor) {
        if (neighbor != null && !northNeighbors.contains(neighbor)) {
            northNeighbors.add(neighbor);
        }
    }
    private void addToEastList(WfcTile neighbor)  {
        if (neighbor != null && !eastNeighbors.contains(neighbor)) {
            eastNeighbors.add(neighbor);
        }
    }
    private void addToSouthList(WfcTile neighbor) {
        if (neighbor != null && !southNeighbors.contains(neighbor)) {
            southNeighbors.add(neighbor);
        }
    }
    private void addToAboveList(WfcTile neighbor) {
        if (neighbor != null && !aboveNeighbors.contains(neighbor)) {
            aboveNeighbors.add(neighbor);
        }
    }
    private void addToBelowList(WfcTile neighbor) {
        if (neighbor != null && !belowNeighbors.contains(neighbor)) {
            belowNeighbors.add(neighbor);
        }
    }

    public void addNeighborToTheWest(WfcTile neighbor) {
        if (neighbor == null) return;
        if (!westNeighbors.contains(neighbor)) {
            addToWestList(neighbor);
            if (!neighbor.getEastNeighbors().contains(this)) {
                neighbor.addNeighborToTheEast(this);
            }
        }
    }

    public void addNeighborToTheEast(WfcTile neighbor) {
        if (neighbor == null) return;
        if (!eastNeighbors.contains(neighbor)) {
            addToEastList(neighbor);
            if (!neighbor.getWestNeighbors().contains(this)) {
                neighbor.addNeighborToTheWest(this);
            }
        }
    }

    public void addNeighborToTheNorth(WfcTile neighbor) {
        if (neighbor == null) return;
        if (!northNeighbors.contains(neighbor)) {
            addToNorthList(neighbor);
            if (!neighbor.getSouthNeighbors().contains(this)) {
                neighbor.addNeighborToTheSouth(this);
            }
        }
    }

    public void addNeighborToTheSouth(WfcTile neighbor) {
        if (neighbor == null) return;
        if (!southNeighbors.contains(neighbor)) {
            addToSouthList(neighbor);
            if (!neighbor.getNorthNeighbors().contains(this)) {
                neighbor.addNeighborToTheNorth(this);
            }
        }
    }

    public void addNeighborToTheAbove(WfcTile neighbor) {
        if (neighbor == null) return;
        if (!aboveNeighbors.contains(neighbor)) {
            addToAboveList(neighbor);
            if (!neighbor.getBelowNeighbors().contains(this)) {
                neighbor.addNeighborToTheBelow(this);
            }
        }
    }

    public void addNeighborToTheBelow(WfcTile neighbor) {
        if (neighbor == null) return;
        if (!belowNeighbors.contains(neighbor)) {
            addToBelowList(neighbor);
            if (!neighbor.getAboveNeighbors().contains(this)) {
                neighbor.addNeighborToTheAbove(this);
            }
        }
    }

    // ===== Getters the WFC will use =====
    public List<WfcTile> getWestNeighbors() { return westNeighbors; }
    public List<WfcTile> getNorthNeighbors(){ return northNeighbors; }
    public List<WfcTile> getEastNeighbors() { return eastNeighbors; }
    public List<WfcTile> getSouthNeighbors(){ return southNeighbors; }
    public List<WfcTile> getAboveNeighbors(){ return aboveNeighbors; }
    public List<WfcTile> getBelowNeighbors(){ return belowNeighbors; }

    @Override
    public String toString() {
        return name;
    }
}
