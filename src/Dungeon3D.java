import java.util.LinkedList;
import java.util.Queue;

public class Dungeon3D {

    public static void main(String[] args) {
        char[][][] dungeonMatrix =
                {
                        {
                                {'1', '.', '.'},
                                {'#', '#', '.'},
                                {'#', '.', '.'},
                                {'.', '#', '.'}},
                        {
                                {'.', '.', '#'},
                                {'#', '#', '.'},
                                {'#', '.', '.'},
                                {'.', '#', '.'}},
                        {
                                {'.', '.', '.'},
                                {'#', '#', '.'},
                                {'.', '#', '.'},
                                {'.', '.', '2'}}
                };

        System.out.println(findPath(dungeonMatrix));
    }

    static class Cell {
        int matrix;
        int row;
        int column;
        int step;

        Cell(int matrix, int row, int column, int step) {
            this.matrix = matrix;
            this.row = row;
            this.column = column;
            this.step = step;
        }
    }

    private static int findPath(char[][][] dungeonMatrix) {
        Cell cell = new Cell(0, 0, 0, 0);

        findingStartPosition:
        for (int k = 0; k < dungeonMatrix.length; k++) {
            for (int j = 0; j < dungeonMatrix.length; j++) {
                for (int i = 0; i < dungeonMatrix.length; i++) {

                    if (dungeonMatrix[k][j][i] == '1') {
                        cell.matrix = k;
                        cell.row = j;
                        cell.column = i;
                        break findingStartPosition;
                    }
                }
            }
        }

        Queue<Cell> queueForBFS = new LinkedList<>();
        queueForBFS.add(new Cell(cell.matrix, cell.row, cell.column, cell.step));

        boolean[][][] visitedCells = new boolean[dungeonMatrix.length][dungeonMatrix[0].length][dungeonMatrix[0].length];
        visitedCells[cell.matrix][cell.row][cell.column] = true;

        while (!queueForBFS.isEmpty()) {
            Cell cellFromQueue = queueForBFS.remove();

            if (dungeonMatrix[cellFromQueue.matrix][cellFromQueue.row][cellFromQueue.column] == '2') {
                return cellFromQueue.step * 5;
            }

            if (isCellValid(cellFromQueue.matrix, cellFromQueue.row - 1, cellFromQueue.column, dungeonMatrix, visitedCells)) {
                queueForBFS.add(new Cell(cellFromQueue.matrix, cellFromQueue.row - 1, cellFromQueue.column,
                        cellFromQueue.step + 1));
                visitedCells[cellFromQueue.matrix][cellFromQueue.row - 1][cellFromQueue.column] = true;
            }

            if (isCellValid(cellFromQueue.matrix, cellFromQueue.row + 1, cellFromQueue.column, dungeonMatrix, visitedCells)) {
                queueForBFS.add(new Cell(cellFromQueue.matrix, cellFromQueue.row + 1, cellFromQueue.column,
                        cellFromQueue.step + 1));
                visitedCells[cellFromQueue.matrix][cellFromQueue.row + 1][cellFromQueue.column] = true;
            }

            if (isCellValid(cellFromQueue.matrix, cellFromQueue.row, cellFromQueue.column - 1, dungeonMatrix, visitedCells)) {
                queueForBFS.add(new Cell(cellFromQueue.matrix, cellFromQueue.row, cellFromQueue.column - 1,
                        cellFromQueue.step + 1));
                visitedCells[cellFromQueue.matrix][cellFromQueue.row][cellFromQueue.column - 1] = true;
            }

            if (isCellValid(cellFromQueue.matrix, cellFromQueue.row, cellFromQueue.column + 1, dungeonMatrix, visitedCells)) {
                queueForBFS.add(new Cell(cellFromQueue.matrix, cellFromQueue.row, cellFromQueue.column + 1,
                        cellFromQueue.step + 1));
                visitedCells[cellFromQueue.matrix][cellFromQueue.row][cellFromQueue.column + 1] = true;
            }

            if (isCellValid(cellFromQueue.matrix + 1, cellFromQueue.row + 1, cellFromQueue.column, dungeonMatrix, visitedCells)) {
                queueForBFS.add(new Cell(cellFromQueue.matrix + 1, cellFromQueue.row + 1, cellFromQueue.column,
                        cellFromQueue.step + 1));
                visitedCells[cellFromQueue.matrix + 1][cellFromQueue.row + 1][cellFromQueue.column] = true;
            }

            if (isCellValid(cellFromQueue.matrix + 1, cellFromQueue.row - 1, cellFromQueue.column, dungeonMatrix, visitedCells)) {
                queueForBFS.add(new Cell(cellFromQueue.matrix + 1, cellFromQueue.row - 1, cellFromQueue.column,
                        cellFromQueue.step + 1));
                visitedCells[cellFromQueue.matrix + 1][cellFromQueue.row - 1][cellFromQueue.column] = true;
            }

            if (isCellValid(cellFromQueue.matrix + 1, cellFromQueue.row, cellFromQueue.column + 1, dungeonMatrix, visitedCells)) {
                queueForBFS.add(new Cell(cellFromQueue.matrix + 1, cellFromQueue.row, cellFromQueue.column + 1,
                        cellFromQueue.step + 1));
                visitedCells[cellFromQueue.matrix + 1][cellFromQueue.row][cellFromQueue.column + 1] = true;
            }

            if (isCellValid(cellFromQueue.matrix + 1, cellFromQueue.row, cellFromQueue.column - 1, dungeonMatrix, visitedCells)) {
                queueForBFS.add(new Cell(cellFromQueue.matrix + 1, cellFromQueue.row, cellFromQueue.column - 1,
                        cellFromQueue.step + 1));
                visitedCells[cellFromQueue.matrix + 1][cellFromQueue.row][cellFromQueue.column - 1] = true;
            }
        }
        return -1;
    }

    private static boolean isCellValid(int z, int x, int y, char[][][] dungeonMatrix, boolean[][][] visitedCells) {
        if (z >= 0 && x >= 0 && y >= 0 &&
                z < dungeonMatrix.length &&
                x < dungeonMatrix[0].length &&
                y < dungeonMatrix[0][0].length &&
                dungeonMatrix[z][x][y] != '#' &&
                !visitedCells[z][x][y]) {
            return true;
        }
        return false;
    }
}
