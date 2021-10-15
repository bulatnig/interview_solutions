public class GameOfLife {

    private static final int LIVE = 1;
    private static final int DEAD = 0;

    private int[][] grid;
    private final int rows;
    private final int columns;

    public GameOfLife(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.columns = grid[0].length;
    }

    public void evolve() {
        int[][] newPlane = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int liveCellCount = countLiveCellsInArea(row, column);
                newPlane[row][column] = liveCellCount == 3 || (liveCellCount == 4 && grid[row][column] == LIVE)
                        ? LIVE
                        : DEAD;
            }
        }
        grid = newPlane;
    }

    private int countLiveCellsInArea(int row, int column) {
        int count = 0;
        if (row > 0) {
            count += countLiveCellsInRow(row - 1, column);
        }
        count += countLiveCellsInRow(row, column);
        if (row < rows - 1) {
            count += countLiveCellsInRow(row + 1, column);
        }
        return count;
    }

    private int countLiveCellsInRow(int row, int column) {
        int count = 0;
        if (column > 0 && grid[row][column - 1] == LIVE) {
            count++;
        }
        if (grid[row][column] == LIVE) {
            count++;
        }
        if (column < columns - 1 && grid[row][column + 1] == LIVE) {
            count++;
        }
        return count;
    }

    public void printPlane() {
        System.out.println("-------------------------");
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell == LIVE ? "X" : "O");
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    public static void main(String[] args) {
        // Still life: Block
//        int[][] initialState = new int[][]{
//                {0, 0, 0, 0},
//                {0, 1, 1, 0},
//                {0, 1, 1, 0},
//                {0, 0, 0, 0},
//        };
//         Oscillators: Blinker (period 2)
        int[][] initialState = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };
        // Oscillators: Toad (period 2)
//        int[][] initialState = new int[][]{
//                {0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0},
//                {0, 0, 1, 1, 1, 0},
//                {0, 1, 1, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0},
//        };
        GameOfLife life = new GameOfLife(initialState);
        life.evolve();
        life.printPlane();
        life.evolve();
        life.printPlane();
    }

}
