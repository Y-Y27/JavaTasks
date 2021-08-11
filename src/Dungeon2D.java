import java.util.LinkedList;
import java.util.Queue;

public class Dungeon2D {

    public static void main(String[] args) {
        char[][] dungeonMatrix = {
                {'1', '#', '#'},
                {'.', '.', '#'},
                {'#', '.', '#'},
                {'.', '.', '.'},
                {'.', '#', '.'},
                {'.', '#', '.'},
                {'.', '#', '.'},
                {'.', '.', '2'}
        };

        System.out.println(findPath(dungeonMatrix));
    }

    static class Cell {
        int row;
        int column;
        int step;

        Cell(int row, int column, int step) {
            this.row = row;
            this.column = column;
            this.step = step;
        }
    }

    private static int findPath(char[][] dungeonMatrix) {
        Cell cell = new Cell(0, 0, 0);

        findingStartPosition:
        for (int i = 0; i < dungeonMatrix.length; i++) {
            for (int j = 0; j < dungeonMatrix.length; j++) {

                if (dungeonMatrix[i][j] == '1') {
                    cell.row = i;
                    cell.column = j;
                    break findingStartPosition;
                }
            }
        }

        Queue<Cell> queueForBFS = new LinkedList<>();
        queueForBFS.add(new Cell(cell.row, cell.column, cell.step));

        boolean[][] visitedCells = new boolean[dungeonMatrix.length][dungeonMatrix[0].length];
        visitedCells[cell.row][cell.column] = true;

        while (!queueForBFS.isEmpty()) {
            Cell cellFromQueue = queueForBFS.remove();

            if (dungeonMatrix[cellFromQueue.row][cellFromQueue.column] == '2') {
                return cellFromQueue.step * 5;
            }

            if (isCellValid(cellFromQueue.row - 1, cellFromQueue.column, dungeonMatrix, visitedCells)) {
                queueForBFS.add(new Cell(cellFromQueue.row - 1, cellFromQueue.column,
                        cellFromQueue.step + 1));
                visitedCells[cellFromQueue.row - 1][cellFromQueue.column] = true;
            }

            if (isCellValid(cellFromQueue.row + 1, cellFromQueue.column, dungeonMatrix, visitedCells)) {
                queueForBFS.add(new Cell(cellFromQueue.row + 1, cellFromQueue.column,
                        cellFromQueue.step + 1));
                visitedCells[cellFromQueue.row + 1][cellFromQueue.column] = true;
            }

            if (isCellValid(cellFromQueue.row, cellFromQueue.column - 1, dungeonMatrix, visitedCells)) {
                queueForBFS.add(new Cell(cellFromQueue.row, cellFromQueue.column - 1,
                        cellFromQueue.step + 1));
                visitedCells[cellFromQueue.row][cellFromQueue.column - 1] = true;
            }

            if (isCellValid(cellFromQueue.row, cellFromQueue.column + 1, dungeonMatrix, visitedCells)) {
                queueForBFS.add(new Cell(cellFromQueue.row, cellFromQueue.column + 1,
                        cellFromQueue.step + 1));
                visitedCells[cellFromQueue.row][cellFromQueue.column + 1] = true;
            }
        }
        return -1;
    }

    private static boolean isCellValid(int x, int y,
                                       char[][] dungeonMatrix,
                                       boolean[][] visitedCells) {
        if (x >= 0 && y >= 0 &&
                x < dungeonMatrix.length &&
                y < dungeonMatrix[0].length &&
                dungeonMatrix[x][y] != '#' &&
                !visitedCells[x][y]) {
            return true;
        }
        return false;
    }
}
