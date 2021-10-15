package pairing;

public class Solution {

    static enum Direction {
        RIGHT,
        BOTTOM,
        LEFT,
        UP
    }

    public static void main(String[] args) {
        // var matrix = new int[][]{
        //     { 1,  2,  3,  4,  5},
        //     {16, 17, 18, 19,  6},
        //     {15, 24, 25, 20,  7},
        //     {14, 23, 22, 21,  8},
        //     {13, 12, 11, 10,  9}
        // };

        int[][] matrix = new int[][]{
                { 1,  2,  3,  4,  5},
                {12, 13, 14, 15,  6},
                {11, 10,  9,  8,  7}
        };

        //output: 1, 2, 3, 4, 5, 6, 7, ... 25
        // N even N / 2 cycles
        // N is odd N / 2 cycles + print middle element
        var counter = 0;
        var row = 0;
        var column = 0;
        var direction = Direction.RIGHT;
        var rowCount = matrix.length;
        var columnCount = matrix[0].length;
        while (counter < rowCount * columnCount) {
            System.out.println(matrix[row][column]);
            matrix[row][column] = -matrix[row][column];
            switch (direction) {
                case RIGHT:
                    if (column == columnCount - 1 || matrix[row][column + 1] < 0) {
                        direction = Direction.BOTTOM;
                        row += 1;
                    } else {
                        column += 1;
                    }
                    break;
                case BOTTOM:
                    if (row  == rowCount - 1 || matrix[row + 1][column] < 0) {
                        direction = Direction.LEFT;
                        column -= 1;
                    } else {
                        row += 1;
                    }
                    break;
                case LEFT:
                    if (column == 0 || matrix[row][column - 1] < 0) {
                        direction = Direction.UP;
                        row -= 1;
                    } else {
                        column -= 1;
                    }
                    break;
                case UP:
                    if (row == 0 || matrix[row - 1][column] < 0) {
                        direction = Direction.RIGHT;
                        column += 1;
                    } else {
                        row -= 1;
                    }
                    break;
            }
            counter++;
        }
    }
}