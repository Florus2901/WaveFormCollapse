import java.util.*;

public class WaveFormCollapse {
    private final int w, h;
    private final List<WfcTile> WfcTiles;
    private final WfcTile[][] collapsed;
    private WfcTile[][][] quantumList;   // [x][y][possible WfcTiles]

    private final long seed;
    private final Random rng;

    public WaveFormCollapse(int w, int h, List<WfcTile> WfcTiles) {
        this(w, h, WfcTiles, System.currentTimeMillis(), null);
    }

    public WaveFormCollapse(int w, int h, List<WfcTile> WfcTiles, List<PrePlacedTile> prePlaced) {
        this(w, h, WfcTiles, System.currentTimeMillis(), prePlaced);
    }

    public WaveFormCollapse(int w, int h, List<WfcTile> WfcTiles, long seed) {
        this(w, h, WfcTiles, seed, null);
    }

    public WaveFormCollapse(int w, int h, List<WfcTile> WfcTiles, long seed, List<PrePlacedTile> prePlaced) {
        this.w = w;
        this.h = h;
        this.WfcTiles = new ArrayList<>(WfcTiles);
        this.collapsed = new WfcTile[w][h];
        this.seed = seed;
        this.rng = new Random(seed);
        createQuantumList();
        if (prePlaced != null && !prePlaced.isEmpty()) {
            for (PrePlacedTile p : prePlaced) {
                setWfcTile(p.WfcTile(), p.x(), p.y());
            }
        }
    }

    public long getSeed() {
        return seed;
    }

    private void createQuantumList() {
        if (WfcTiles.isEmpty()) {
            throw new RuntimeException("No WfcTiles provided.");
        }
        quantumList = new WfcTile[w][h][];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                quantumList[x][y] = WfcTiles.toArray(new WfcTile[0]);
                collapsed[x][y] = null;
            }
        }
    }

    public void setWfcTile(WfcTile WfcTile, int x, int y){
        if(x >= 0 && x < w && y >= 0 && y < h){
            collapsed[x][y] = WfcTile;
            quantumList[x][y] = new WfcTile[]{WfcTile};
            propagate(x, y);
        }
    }

    // ===== Main loop =====
    public void run() {
        while (true) {
            int[] cell = findLowestEntropyCell();
            if (cell == null) break;

            int x = cell[0];
            int y = cell[1];
            collapse(x, y);
            propagate(x, y);
        }
        System.out.println("WFC finished.");
    }

    // ===== Core WFC methods =====
    private int[] findLowestEntropyCell() {
        int minEntropy = Integer.MAX_VALUE;
        List<int[]> candidates = new ArrayList<>();

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                if (collapsed[x][y] != null) continue;

                int entropy = quantumList[x][y].length;
                if (entropy == 0) {
                    System.out.println("Contradiction at (" + x + "," + y + ")");
                    return null;
                }
                if (entropy < minEntropy) {
                    minEntropy = entropy;
                    candidates.clear();
                    candidates.add(new int[]{x, y});
                } else if (entropy == minEntropy) {
                    candidates.add(new int[]{x, y});
                }
            }
        }
        if (candidates.isEmpty()) return null;
        // Random tie-break
        return candidates.get(rng.nextInt(candidates.size()));
    }

    private void collapse(int x, int y) {
        WfcTile[] options = quantumList[x][y];
        if (options.length == 0) return;

        WfcTile chosen = options[rng.nextInt(options.length)];
        collapsed[x][y] = chosen;
        quantumList[x][y] = new WfcTile[]{chosen};
    }

    private void propagate(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];

            // Check all four directions
            checkAndUpdateNeighbor(x, y, x - 1, y, Direction.WEST);
            checkAndUpdateNeighbor(x, y, x, y - 1, Direction.NORTH);
            checkAndUpdateNeighbor(x, y, x + 1, y, Direction.EAST);
            checkAndUpdateNeighbor(x, y, x, y + 1, Direction.SOUTH);
        }
    }

    private void checkAndUpdateNeighbor(int cx, int cy, int nx, int ny, Direction direction) {
        if (nx < 0 || nx >= w || ny < 0 || ny >= h) return;
        if (collapsed[nx][ny] != null) return;

        WfcTile[] currentOptions = quantumList[cx][cy];
        if (currentOptions.length == 0) return;

        WfcTile[] neighborOptions = quantumList[nx][ny];
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
            quantumList[nx][ny] = newOptions.toArray(new WfcTile[0]);
        }
    }

    private boolean isCompatible(WfcTile a, WfcTile b, Direction directionFromAtoB) {
        return switch (directionFromAtoB) {
            case WEST  -> a.getWestNeighbors().contains(b);
            case NORTH -> a.getNorthNeighbors().contains(b);
            case EAST  -> a.getEastNeighbors().contains(b);
            case SOUTH -> a.getSouthNeighbors().contains(b);
            default -> false;
        };
    }

    // ===== Utility =====
    public WfcTile[][] getCollapsedGrid() {
        return collapsed;
    }

    public void printGrid() {
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                WfcTile t = collapsed[x][y];
                System.out.print((t != null ? t.getName().charAt(0) : '?') + " ");
            }
            System.out.println();
        }
    }

    public void printSeed(){
        System.out.println("The seed:"+seed+" used.");
    }
}