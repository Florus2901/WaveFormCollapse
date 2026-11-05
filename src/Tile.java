import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Tile implements WfcTile{
    private final String name;
    private boolean isGround;
    private boolean isAir;

    private final Set<WfcTile> westNeighbors = new HashSet<>();
    private final Set<WfcTile> northNeighbors = new HashSet<>();
    private final Set<WfcTile> eastNeighbors = new HashSet<>();
    private final Set<WfcTile> southNeighbors = new HashSet<>();
    private final Set<WfcTile> aboveNeighbors = new HashSet<>();
    private final Set<WfcTile> belowNeighbors = new HashSet<>();
    private final Set<WfcTile> westNorthNeighbors = new HashSet<>();
    private final Set<WfcTile> westSouthNeighbors = new HashSet<>();
    private final Set<WfcTile> eastNorthNeighbors = new HashSet<>();
    private final Set<WfcTile> eastSouthNeighbors = new HashSet<>();
    private final Set<WfcTile> westAboveNeighbors = new HashSet<>();
    private final Set<WfcTile> westBelowNeighbors = new HashSet<>();
    private final Set<WfcTile> eastAboveNeighbors = new HashSet<>();
    private final Set<WfcTile> eastBelowNeighbors = new HashSet<>();
    private final Set<WfcTile> northAboveNeighbors = new HashSet<>();
    private final Set<WfcTile> northBelowNeighbors = new HashSet<>();
    private final Set<WfcTile> southAboveNeighbors = new HashSet<>();
    private final Set<WfcTile> southBelowNeighbors = new HashSet<>();
    private final Set<WfcTile> westNorthAboveNeighbors = new HashSet<>();
    private final Set<WfcTile> westNorthBelowNeighbors = new HashSet<>();
    private final Set<WfcTile> westSouthAboveNeighbors = new HashSet<>();
    private final Set<WfcTile> westSouthBelowNeighbors = new HashSet<>();
    private final Set<WfcTile> eastNorthAboveNeighbors = new HashSet<>();
    private final Set<WfcTile> eastNorthBelowNeighbors = new HashSet<>();
    private final Set<WfcTile> eastSouthAboveNeighbors = new HashSet<>();
    private final Set<WfcTile> eastSouthBelowNeighbors = new HashSet<>();


    public Tile(String name) {
        this(name, true, true);
    }

    public Tile(String name, boolean isGround, boolean isAir) {
        this.name = name;
        this.isGround = isGround;
        this.isAir = isAir;
    }
    @Override
    public String getName() {
        return name;
    }

    public void setGround(boolean isGround){
        this.isGround = isGround;
    }
    public void setAir(boolean isAir){ this.isAir = isAir; }

    @Override
    public boolean isGround() {
        return isGround;
    }

    @Override
    public boolean isAir() {
        return isAir;
    }

    @Override
    public boolean addToWestList(WfcTile neighbor) {
        return neighbor != null && westNeighbors.add(neighbor);
    }
    @Override
    public boolean addToNorthList(WfcTile neighbor) {
        return neighbor != null  && northNeighbors.add(neighbor);
    }
    @Override
    public boolean addToEastList(WfcTile neighbor)  {
        return neighbor != null && eastNeighbors.add(neighbor);
    }
    @Override
    public boolean addToSouthList(WfcTile neighbor) {
        return neighbor != null && southNeighbors.add(neighbor);
    }
    @Override
    public boolean addToAboveList(WfcTile neighbor) {
        return neighbor != null && aboveNeighbors.add(neighbor);
    }
    @Override
    public boolean addToBelowList(WfcTile neighbor) {
        return neighbor != null && belowNeighbors.add(neighbor);
    }
    @Override
    public boolean addToWestNorthList(WfcTile neighbor){
        return neighbor != null && westNorthNeighbors.add(neighbor);
    }
    @Override
    public boolean addToWestSouthList(WfcTile neighbor){
        return neighbor != null && westSouthNeighbors.add(neighbor);
    }
    @Override
    public boolean addToEastNorthList(WfcTile neighbor){
        return neighbor != null && eastNorthNeighbors.add(neighbor);
    }
    @Override
    public boolean addToEastSouthList(WfcTile neighbor){
        return neighbor != null && eastSouthNeighbors.add(neighbor);
    }
    @Override
    public boolean addToWestAboveList(WfcTile neighbor){
        return neighbor != null && westAboveNeighbors.add(neighbor);
    }
    @Override
    public boolean addToWestBelowList(WfcTile neighbor){
        return neighbor != null && westBelowNeighbors.add(neighbor);
    }
    @Override
    public boolean addToEastAboveList(WfcTile neighbor){
        return neighbor != null && eastAboveNeighbors.add(neighbor);
    }
    @Override
    public boolean addToEastBelowList(WfcTile neighbor){
        return neighbor != null && eastBelowNeighbors.add(neighbor);
    }
    @Override
    public boolean addToNorthAboveList(WfcTile neighbor){
        return neighbor != null && northAboveNeighbors.add(neighbor);
    }
    @Override
    public boolean addToNorthBelowList(WfcTile neighbor){
        return neighbor != null && northBelowNeighbors.add(neighbor);
    }
    @Override
    public boolean addToSouthAboveList(WfcTile neighbor){
        return neighbor != null && southAboveNeighbors.add(neighbor);
    }
    @Override
    public boolean addToSouthBelowList(WfcTile neighbor){
        return neighbor != null && southBelowNeighbors.add(neighbor);
    }
    @Override
    public boolean addToWestNorthAboveList(WfcTile neighbor){
        return neighbor != null && westNorthAboveNeighbors.add(neighbor);
    }
    @Override
    public boolean addToWestNorthBelowList(WfcTile neighbor){
        return neighbor != null && westNorthBelowNeighbors.add(neighbor);
    }
    @Override
    public boolean addToWestSouthAboveList(WfcTile neighbor){
        return neighbor != null && westSouthAboveNeighbors.add(neighbor);
    }
    @Override
    public boolean addToWestSouthBelowList(WfcTile neighbor){
        return neighbor != null && westSouthBelowNeighbors.add(neighbor);
    }
    @Override
    public boolean addToEastNorthAboveList(WfcTile neighbor){
        return neighbor != null && eastNorthAboveNeighbors.add(neighbor);
    }
    @Override
    public boolean addToEastNorthBelowList(WfcTile neighbor){
        return neighbor != null && eastNorthBelowNeighbors.add(neighbor);
    }
    @Override
    public boolean addToEastSouthAboveList(WfcTile neighbor){
        return neighbor != null && eastSouthAboveNeighbors.add(neighbor);
    }
    @Override
    public boolean addToEastSouthBelowList(WfcTile neighbor){
        return neighbor != null && eastSouthBelowNeighbors.add(neighbor);
    }

    public void addNeighborToTheWest(WfcTile neighbor) {
        if (addToWestList(neighbor)) {
            neighbor.addNeighborToTheEast(this);
        }
    }
    public void addNeighborToTheEast(WfcTile neighbor) {
        if (addToEastList(neighbor)) {
            neighbor.addNeighborToTheWest(this);
        }
    }
    public void addNeighborToTheNorth(WfcTile neighbor) {
        if (addToNorthList(neighbor)) {
            neighbor.addNeighborToTheSouth(this);
        }
    }
    public void addNeighborToTheSouth(WfcTile neighbor) {
        if (addToSouthList(neighbor)) {
            neighbor.addNeighborToTheNorth(this);
        }
    }
    public void addNeighborToTheAbove(WfcTile neighbor) {
        if (addToAboveList(neighbor)) {
            neighbor.addNeighborToTheBelow(this);
        }
    }
    public void addNeighborToTheBelow(WfcTile neighbor) {
        if (addToBelowList(neighbor)) {
            neighbor.addNeighborToTheAbove(this);
        }
    }
    public void addNeighborToTheWestNorth(WfcTile neighbor){
        if(addToWestNorthList(neighbor)) {
            neighbor.addNeighborToTheEastSouth(this);
        }
    }
    public void addNeighborToTheWestSouth(WfcTile neighbor){
        if(addToWestSouthList(neighbor)) {
            neighbor.addNeighborToTheEastNorth(this);
        }
    }
    public void addNeighborToTheEastNorth(WfcTile neighbor){
        if(addToEastNorthList(neighbor)) {
            neighbor.addNeighborToTheWestSouth(this);
        }
    }
    public void addNeighborToTheEastSouth(WfcTile neighbor){
        if(addToEastSouthList(neighbor)) {
            neighbor.addNeighborToTheWestNorth(this);
        }
    }
    public void addNeighborToTheWestAbove(WfcTile neighbor){
        if(addToWestAboveList(neighbor)) {
            neighbor.addNeighborToTheEastBelow(this);
        }
    }
    public void addNeighborToTheWestBelow(WfcTile neighbor){
        if(addToWestBelowList(neighbor)) {
            neighbor.addNeighborToTheEastAbove(this);
        }
    }
    public void addNeighborToTheEastAbove(WfcTile neighbor){
        if(addToEastAboveList(neighbor)) {
            neighbor.addNeighborToTheWestBelow(this);
        }
    }
    public void addNeighborToTheEastBelow(WfcTile neighbor){
        if(addToEastBelowList(neighbor)) {
            neighbor.addNeighborToTheWestAbove(this);
        }
    }
    public void addNeighborToTheNorthAbove(WfcTile neighbor){
        if(addToNorthAboveList(neighbor)) {
            neighbor.addNeighborToTheSouthBelow(this);
        }
    }
    public void addNeighborToTheNorthBelow(WfcTile neighbor){
        if(addToNorthBelowList(neighbor)) {
            neighbor.addNeighborToTheSouthAbove(this);
        }
    }
    public void addNeighborToTheSouthAbove(WfcTile neighbor){
        if(addToSouthAboveList(neighbor)) {
            neighbor.addNeighborToTheNorthBelow(this);
        }
    }
    public void addNeighborToTheSouthBelow(WfcTile neighbor){
        if(addToSouthBelowList(neighbor)) {
            neighbor.addNeighborToTheNorthAbove(this);
        }
    }
    public void addNeighborToTheWestNorthAbove(WfcTile neighbor){
        if(addToWestNorthAboveList(neighbor)) {
            neighbor.addNeighborToTheEastSouthBelow(this);
        }
    }
    public void addNeighborToTheWestNorthBelow(WfcTile neighbor){
        if(addToWestNorthBelowList(neighbor)) {
                neighbor.addNeighborToTheEastSouthAbove(this);
        }
    }
    public void addNeighborToTheWestSouthAbove(WfcTile neighbor){
        if(addToWestSouthAboveList(neighbor)) {
            neighbor.addNeighborToTheEastNorthBelow(this);
        }
    }
    public void addNeighborToTheWestSouthBelow(WfcTile neighbor){
        if(addToWestSouthBelowList(neighbor)) {
            neighbor.addNeighborToTheEastNorthAbove(this);
        }
    }
    public void addNeighborToTheEastNorthAbove(WfcTile neighbor){
        if(addToEastNorthAboveList(neighbor)) {
            neighbor.addNeighborToTheWestSouthBelow(this);
        }
    }
    public void addNeighborToTheEastNorthBelow(WfcTile neighbor){
        if(addToEastNorthBelowList(neighbor)) {
            neighbor.addNeighborToTheWestSouthAbove(this);
        }
    }
    public void addNeighborToTheEastSouthAbove(WfcTile neighbor){
        if(addToEastSouthAboveList(neighbor)) {
            neighbor.addNeighborToTheWestNorthBelow(this);
        }
    }
    public void addNeighborToTheEastSouthBelow(WfcTile neighbor){
        if(addToEastSouthBelowList(neighbor)) {
            neighbor.addNeighborToTheWestNorthAbove(this);
        }
    }
    // ===== Getters the WFC will use =====
    @Override
    public Set<WfcTile> getWestNeighbors() { return Collections.unmodifiableSet(westNeighbors); }
    @Override
    public Set<WfcTile> getNorthNeighbors(){ return Collections.unmodifiableSet(northNeighbors); }
    @Override
    public Set<WfcTile> getEastNeighbors() { return Collections.unmodifiableSet(eastNeighbors); }
    @Override
    public Set<WfcTile> getSouthNeighbors(){ return Collections.unmodifiableSet(southNeighbors); }
    @Override
    public Set<WfcTile> getAboveNeighbors(){ return Collections.unmodifiableSet(aboveNeighbors); }
    @Override
    public Set<WfcTile> getBelowNeighbors(){ return Collections.unmodifiableSet(belowNeighbors); }
    @Override
    public Set<WfcTile> getWestNorthNeighbors() { return Collections.unmodifiableSet(westNorthNeighbors); }
    @Override
    public Set<WfcTile> getWestSouthNeighbors() { return Collections.unmodifiableSet(westSouthNeighbors); }
    @Override
    public Set<WfcTile> getEastNorthNeighbors() { return Collections.unmodifiableSet(eastNorthNeighbors); }
    @Override
    public Set<WfcTile> getEastSouthNeighbors() { return Collections.unmodifiableSet(eastSouthNeighbors); }
    @Override
    public Set<WfcTile> getWestAboveNeighbors() { return Collections.unmodifiableSet(westAboveNeighbors); }
    @Override
    public Set<WfcTile> getWestBelowNeighbors() { return Collections.unmodifiableSet(westBelowNeighbors); }
    @Override
    public Set<WfcTile> getEastAboveNeighbors() { return Collections.unmodifiableSet(eastAboveNeighbors); }
    @Override
    public Set<WfcTile> getEastBelowNeighbors() { return Collections.unmodifiableSet(eastBelowNeighbors); }
    @Override
    public Set<WfcTile> getNorthAboveNeighbors() { return Collections.unmodifiableSet(northAboveNeighbors); }
    @Override
    public Set<WfcTile> getNorthBelowNeighbors() { return Collections.unmodifiableSet(northBelowNeighbors); }
    @Override
    public Set<WfcTile> getSouthAboveNeighbors() { return Collections.unmodifiableSet(southAboveNeighbors); }
    @Override
    public Set<WfcTile> getSouthBelowNeighbors() { return Collections.unmodifiableSet(southBelowNeighbors); }
    @Override
    public Set<WfcTile> getWestNorthAboveNeighbors() { return Collections.unmodifiableSet(westNorthAboveNeighbors); }
    @Override
    public Set<WfcTile> getWestNorthBelowNeighbors() { return Collections.unmodifiableSet(westNorthBelowNeighbors); }
    @Override
    public Set<WfcTile> getWestSouthAboveNeighbors() { return Collections.unmodifiableSet(westSouthAboveNeighbors); }
    @Override
    public Set<WfcTile> getWestSouthBelowNeighbors() { return Collections.unmodifiableSet(westSouthBelowNeighbors); }
    @Override
    public Set<WfcTile> getEastNorthAboveNeighbors() { return Collections.unmodifiableSet(eastNorthAboveNeighbors); }
    @Override
    public Set<WfcTile> getEastNorthBelowNeighbors() { return Collections.unmodifiableSet(eastNorthBelowNeighbors); }
    @Override
    public Set<WfcTile> getEastSouthAboveNeighbors() { return Collections.unmodifiableSet(eastSouthAboveNeighbors); }
    @Override
    public Set<WfcTile> getEastSouthBelowNeighbors() { return Collections.unmodifiableSet(eastSouthBelowNeighbors); }

    @Override
    public String toString() { return name; }
}


