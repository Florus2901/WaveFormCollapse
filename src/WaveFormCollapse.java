import java.util.*;

public class WaveFormCollapse {
    private final int w, h, d;
    private final List<WfcTile> wfcTiles;
    private final WfcTile[][][] collapsed; // [w][h][d]
    private WfcTile[][][][] quantumList;   // [w][h][d][possible WfcTiles]

    private final long seed;
    private final Random rng;

    private Queue<int[]> propagationQueue;

    private static final int[][] DELTAS = {
            // Face (6)
            {-1, 0, 0}, {0,-1,0}, {1,0,0}, {0,1,0}, {0,0,1}, {0,0,-1},
            // Edge (12)
            {-1,-1,0}, {-1,1,0}, {1,-1,0}, {1,1,0},
            {-1,0,1}, {-1,0,-1}, {1,0,1}, {1,0,-1},
            {0,-1,1}, {0,-1,-1}, {0,1,1}, {0,1,-1},
            // Corner (8)
            {-1,-1,1}, {-1,-1,-1}, {-1,1,1}, {-1,1,-1},
            {1,-1,1}, {1,-1,-1}, {1,1,1}, {1,1,-1}
    };
    private static final Direction[] DIRECTIONS = {
            Direction.WEST, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.ABOVE, Direction.BELOW,
            Direction.WEST_NORTH, Direction.WEST_SOUTH, Direction.EAST_NORTH, Direction.EAST_SOUTH,
            Direction.WEST_ABOVE, Direction.WEST_BELOW, Direction.EAST_ABOVE, Direction.EAST_BELOW,
            Direction.NORTH_ABOVE, Direction.NORTH_BELOW, Direction.SOUTH_ABOVE, Direction.SOUTH_BELOW,
            Direction.WEST_NORTH_ABOVE, Direction.WEST_NORTH_BELOW,
            Direction.WEST_SOUTH_ABOVE, Direction.WEST_SOUTH_BELOW,
            Direction.EAST_NORTH_ABOVE, Direction.EAST_NORTH_BELOW,
            Direction.EAST_SOUTH_ABOVE, Direction.EAST_SOUTH_BELOW
    };


    // Handles 2d array's
    public WaveFormCollapse(int w, int h, List<WfcTile> wfcTiles) {
        this(w, h, 1, wfcTiles, System.currentTimeMillis(), null);
    }

    public WaveFormCollapse(int w, int h, List<WfcTile> wfcTiles, List<PrePlacedTile> prePlaced) {
        this(w, h, 1, wfcTiles, System.currentTimeMillis(), prePlaced);
    }

    public WaveFormCollapse(int w, int h, List<WfcTile> wfcTiles, long seed) {
        this(w, h, 1, wfcTiles, seed, null);
    }

    public WaveFormCollapse(int w, int h, List<WfcTile> wfcTiles, long seed, List<PrePlacedTile> prePlaced) {
        this(w,h,1,wfcTiles, seed,prePlaced);
    }
    // Handles 3d array's
    public WaveFormCollapse(int w, int h, int d, List<WfcTile> wfcTiles) {
        this(w, h, d, wfcTiles, System.currentTimeMillis(), null);
    }

    public WaveFormCollapse(int w, int h, int d, List<WfcTile> wfcTiles, long seed, List<PrePlacedTile> prePlaced) {
        this.w = w;
        this.h = h;
        this.d = (d <= 0) ? 1 : d;
        this.wfcTiles = new ArrayList<>(wfcTiles);
        this.collapsed = new WfcTile[w][h][this.d];
        this.seed = seed;
        this.rng = new Random(seed);
        createQuantumList();
        if (prePlaced != null && !prePlaced.isEmpty()) {
            for (PrePlacedTile p : prePlaced) {
                setWfcTile(p.WfcTile(), p.x(), p.y(), p.z());
            }
        }
    }

    public long getSeed() {
        return seed;
    }

    private void createQuantumList() {
        if (wfcTiles.isEmpty()) {
            throw new RuntimeException("No WfcTiles provided.");
        }
        quantumList = new WfcTile[w][h][d][];
        List<WfcTile> groundTiles = generateGroundList();
        List<WfcTile> airTiles    = generateAirList();

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                for (int z = 0; z < d; z++) {
                    if(z == 0) {
                        quantumList[x][y][z] = groundTiles.toArray(WfcTile[]::new);
                    } else {
                        quantumList[x][y][z] = airTiles.toArray(WfcTile[]::new);
                    }
                    collapsed[x][y][z] = null;
                }
            }
        }
    }

    public List<WfcTile> generateGroundList(){
        List<WfcTile> nList = new ArrayList<>();
        for(WfcTile tile : wfcTiles){
            if(tile.isGround()){
                nList.add(tile);
            }
        }
        return nList;
    }
    public List<WfcTile> generateAirList(){
        List<WfcTile> nList = new ArrayList<>();
        for(WfcTile tile : wfcTiles){
            if(tile.isAir()){
                nList.add(tile);
            }
        }
        return nList;
    }


    public void setWfcTile(WfcTile wfcTile, int x, int y) {
        setWfcTile(wfcTile, x, y, 0);
    }

    public void setWfcTile(WfcTile wfcTile, int x, int y, int z){
        if(x >= 0 && x < w && y >= 0 && y < h && z >= 0 && z < d){
            collapsed[x][y][z] = wfcTile;
            quantumList[x][y][z] = new WfcTile[]{wfcTile};
            propagate(x, y, z);
        }
    }

    // ===== Main loop =====
    public boolean run() {
        while (true) {
            int[] cell = findLowestEntropyCell();
            if (cell == null) {
                return getCollapsedGrid() != null && !hasContradiction();
            }

            collapse(cell[0], cell[1], cell[2]);
            propagate(cell[0], cell[1], cell[2]);
        }
    }

    // ===== Core WFC methods =====
    private int[] findLowestEntropyCell() {
        int minEntropy = Integer.MAX_VALUE;
        List<int[]> candidates = new ArrayList<>();

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                for (int z = 0; z < d; z++) {
                    if (collapsed[x][y][z] != null) continue;

                    int entropy = quantumList[x][y][z].length;
                    if (entropy == 0) {
                        System.out.println("Contradiction at (" + x + "," + y + "," + z + ")");
                        return null;
                    }
                    if (entropy < minEntropy) {
                        minEntropy = entropy;
                        candidates.clear();
                        candidates.add(new int[]{x, y, z});
                    } else if (entropy == minEntropy) {
                        candidates.add(new int[]{x, y, z});
                    }
                }
            }
        }
        if (candidates.isEmpty()) return null;
        // Random tie-break
        return candidates.get(rng.nextInt(candidates.size()));
    }

    private void collapse(int x, int y, int z) {
        WfcTile[] options = quantumList[x][y][z];
        if (options.length == 0) return;

        WfcTile chosen = options[rng.nextInt(options.length)];
        collapsed[x][y][z] = chosen;
        quantumList[x][y][z] = new WfcTile[]{chosen};
    }

    private void propagate(int startX, int startY, int startZ) {
        if(propagationQueue == null){
            propagationQueue = new LinkedList<>();
        }
        propagationQueue.add(new int[]{startX, startY, startZ});

        while (!propagationQueue.isEmpty()) {
            int[] cell = propagationQueue.poll();
            int x = cell[0];
            int y = cell[1];
            int z = cell[2];

            // Check all twenty-six directions
            for (int dir = 0; dir < 26; dir++) {
                int nx = x + DELTAS[dir][0];
                int ny = y + DELTAS[dir][1];
                int nz = z + DELTAS[dir][2];
                Direction direction = DIRECTIONS[dir];

                checkAndUpdateNeighbor(x, y, z, nx, ny, nz, direction);
            }
        }
    }

    private void checkAndUpdateNeighbor(int cx, int cy, int cz, int nx, int ny, int nz, Direction direction) {
        if (nx < 0 || nx >= w || ny < 0 || ny >= h || nz < 0 || nz >= d) return;
        if (collapsed[nx][ny][nz] != null) return;

        WfcTile[] currentOptions = quantumList[cx][cy][cz];
        if (currentOptions.length == 0) return;

        WfcTile[] neighborOptions = quantumList[nx][ny][nz];
        List<WfcTile> newOptions = new ArrayList<>();

        for (WfcTile neighborWfcTile : neighborOptions) {
            boolean compatible = false;
            for (WfcTile currentWfcTile : currentOptions) {
                if (isCompatible(currentWfcTile, neighborWfcTile, direction)) {
                    compatible = true;
                    break;
                }
            }
            if (compatible) {
                newOptions.add(neighborWfcTile);
            }
        }

        if (newOptions.size() < neighborOptions.length) {
            quantumList[nx][ny][nz] = newOptions.toArray(new WfcTile[0]);
            propagationQueue.add(new int[]{nx, ny, nz});
        }
    }

    private boolean isCompatible(WfcTile a, WfcTile b, Direction directionFromAtoB) {
        return switch (directionFromAtoB) {
            case WEST  -> a.getWestNeighbors().contains(b);
            case NORTH -> a.getNorthNeighbors().contains(b);
            case EAST  -> a.getEastNeighbors().contains(b);
            case SOUTH -> a.getSouthNeighbors().contains(b);
            case ABOVE -> a.getAboveNeighbors().contains(b);
            case BELOW -> a.getBelowNeighbors().contains(b);

            case WEST_NORTH  -> a.getWestNorthNeighbors().contains(b);
            case WEST_SOUTH  -> a.getWestSouthNeighbors().contains(b);
            case EAST_NORTH  -> a.getEastNorthNeighbors().contains(b);
            case EAST_SOUTH  -> a.getEastSouthNeighbors().contains(b);
            case WEST_ABOVE  -> a.getWestAboveNeighbors().contains(b);
            case WEST_BELOW  -> a.getWestBelowNeighbors().contains(b);
            case EAST_ABOVE  -> a.getEastAboveNeighbors().contains(b);
            case EAST_BELOW  -> a.getEastBelowNeighbors().contains(b);
            case NORTH_ABOVE -> a.getNorthAboveNeighbors().contains(b);
            case NORTH_BELOW -> a.getNorthBelowNeighbors().contains(b);
            case SOUTH_ABOVE -> a.getSouthAboveNeighbors().contains(b);
            case SOUTH_BELOW -> a.getSouthBelowNeighbors().contains(b);

            case WEST_NORTH_ABOVE -> a.getWestNorthAboveNeighbors().contains(b);
            case WEST_NORTH_BELOW -> a.getWestNorthBelowNeighbors().contains(b);
            case WEST_SOUTH_ABOVE -> a.getWestSouthAboveNeighbors().contains(b);
            case WEST_SOUTH_BELOW -> a.getWestSouthBelowNeighbors().contains(b);
            case EAST_NORTH_ABOVE -> a.getEastNorthAboveNeighbors().contains(b);
            case EAST_NORTH_BELOW -> a.getEastNorthBelowNeighbors().contains(b);
            case EAST_SOUTH_ABOVE -> a.getEastSouthAboveNeighbors().contains(b);
            case EAST_SOUTH_BELOW -> a.getEastSouthBelowNeighbors().contains(b);
            default -> false;
        };
    }

    private boolean hasContradiction() {
        for (int x = 0; x < w; x++)
            for (int y = 0; y < h; y++)
                for (int z = 0; z < d; z++)
                    if (collapsed[x][y][z] == null && quantumList[x][y][z].length == 0)
                        return true;
        return false;
    }

    // ===== Utility =====
    public WfcTile[][][] getCollapsedGrid() {
        WfcTile[][][] copy = new WfcTile[w][h][d];
        for (int x = 0; x < w; x++)
            for (int y = 0; y < h; y++)
                for (int z = 0; z < d; z++)
                    copy[x][y][z] = collapsed[x][y][z];
        return copy;
    }

    // legacy / 2-D only
    public void printGrid() {
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                WfcTile t = collapsed[x][y][0];
                System.out.print((t != null ? t.getName().charAt(0) : '?') + " ");
            }
            System.out.println();
        }
    }

    public String getGridAsJsonString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n  \"grid\": [\n");
        for (int z = 0; z < d; z++) {
            sb.append("    [\n");
            for (int y = 0; y < h; y++) {
                sb.append("      [");
                for (int x = 0; x < w; x++) {
                    WfcTile t = collapsed[x][y][z];
                    sb.append(t != null ? "\"" + t.getName() + "\"" : "null");
                    if (x < w - 1) sb.append(", ");
                }
                sb.append("]");
                if (y < h - 1) sb.append(",");
                sb.append("\n");
            }
            sb.append("    ]");
            if (z < d - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("  ]\n}");
        return sb.toString();
    }

    public void printSeed(){
        System.out.println("The seed:"+ getSeed() +" used.");
    }
}