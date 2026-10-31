import java.util.*;

public class WaveFormCollapse {
    private final int w, h;
    private final List<Tile> tiles;
    private final Tile[][] collapsed;
    private Tile[][][] quantumList;   // [x][y][possible tiles]

    private final long seed;
    private final Random rng;           // ← shared random generator

    public WaveFormCollapse(int w, int h, List<Tile> tiles) {
        this(w, h, tiles, System.currentTimeMillis(), null);   // default = random seed
    }

    public WaveFormCollapse(int w, int h, List<Tile> tiles, List<PrePlacedTile> prePlaced) {
        this(w, h, tiles, System.currentTimeMillis(), prePlaced);   // default = random seed
    }

    public WaveFormCollapse(int w, int h, List<Tile> tiles, long seed) {
        this(w, h, tiles, seed, null);   // default = random seed
    }

    public WaveFormCollapse(int w, int h, List<Tile> tiles, long seed, List<PrePlacedTile> prePlaced) {
        this.w = w;
        this.h = h;
        this.tiles = new ArrayList<>(tiles);
        this.collapsed = new Tile[w][h];
        this.seed = seed;
        this.rng = new Random(seed);
        createQuantumList();
        if (prePlaced != null && !prePlaced.isEmpty()) {
            for (PrePlacedTile p : prePlaced) {
                setTile(p.tile(), p.x(), p.y());            }
        }
    }

    public long getSeed() {
        return seed;
    }

    private void createQuantumList() {
        if (tiles.isEmpty()) {
            throw new RuntimeException("No tiles provided.");
        }
        quantumList = new Tile[w][h][];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                quantumList[x][y] = tiles.toArray(new Tile[0]);
                collapsed[x][y] = null;
            }
        }
    }

    public void setTile(Tile tile, int x, int y){
        if(x >= 0 && x <= w && y >= 0 && y <= h){
            collapsed[x][y] = tile;
            quantumList[x][y] = new Tile[]{tile};
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
        Tile[] options = quantumList[x][y];
        if (options.length == 0) return;

        Tile chosen = options[rng.nextInt(options.length)];
        collapsed[x][y] = chosen;
        quantumList[x][y] = new Tile[]{chosen};
    }

    private void propagate(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];

            // Check all four directions
            checkAndUpdateNeighbor(x, y, x - 1, y, "west");   // West neighbor of current
            checkAndUpdateNeighbor(x, y, x, y - 1, "north");
            checkAndUpdateNeighbor(x, y, x + 1, y, "east");
            checkAndUpdateNeighbor(x, y, x, y + 1, "south");
        }
    }

    private void checkAndUpdateNeighbor(int cx, int cy, int nx, int ny, String direction) {
        if (nx < 0 || nx >= w || ny < 0 || ny >= h) return;
        if (collapsed[nx][ny] != null) return; // already decided

        Tile[] currentOptions = quantumList[cx][cy];
        if (currentOptions.length == 0) return;

        Tile[] neighborOptions = quantumList[nx][ny];
        List<Tile> newOptions = new ArrayList<>();

        for (Tile neighborTile : neighborOptions) {
            boolean compatible = false;
            for (Tile currentTile : currentOptions) {
                if (isCompatible(currentTile, neighborTile, direction)) {
                    compatible = true;
                    break;
                }
            }
            if (compatible) {
                newOptions.add(neighborTile);
            }
        }

        if (newOptions.size() < neighborOptions.length) {
            quantumList[nx][ny] = newOptions.toArray(new Tile[0]);
            // Re-queue the neighbor so its own neighbors get updated
            // (simple version: we can just continue; more advanced versions use a real queue)
        }
    }

    private boolean isCompatible(Tile a, Tile b, String directionFromAtoB) {
        return switch (directionFromAtoB) {
            case "west"  -> a.getWestNeighbors().contains(b);
            case "north" -> a.getNorthNeighbors().contains(b);
            case "east"  -> a.getEastNeighbors().contains(b);
            case "south" -> a.getSouthNeighbors().contains(b);
            default -> false;
        };
    }

    private Tile chooseRandomTile(Tile[] options) {
        if (options == null || options.length == 0) return null;
        int index = rng.nextInt(options.length);
        return options[index];
    }

    // ===== Utility =====
    public Tile[][] getCollapsedGrid() {
        return collapsed;
    }

    public void printGrid() {
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Tile t = collapsed[x][y];
                System.out.print((t != null ? t.getName().charAt(0) : '?') + " ");
            }
            System.out.println();
        }
    }

    public void printSeed(){
        System.out.println("The seed:"+seed+" used.");
    }
}