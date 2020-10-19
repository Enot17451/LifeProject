import java.util.Random;
import java.util.Scanner;

class Game {
    Board board;
    int generations;

    Game(Board $board, int $generations) {
        board = $board;
        generations = $generations;
    }

    Board makeStep() {
        Board b = board;
        int neighbors;
        for (int i = 0; i < board.size; i++) {
            for (int j = 0; j < board.size; j++) {
                neighbors = board.countNeighbors(i, j);
                if (board.map[i][j] && (neighbors == 3 || neighbors == 2)) {
                    board.map[i][j] = true;
                } else if (!board.map[i][j] && neighbors == 3) {
                    board.map[i][j] = true;
                } else {
                    board.map[i][j] = false;
                }

            }
        }
        return b;
    }

    void makeGenerations() {
        for (int i = 1; i < generations; i++) {
            System.out.println("generation ¹ " + i);
            board.showMapWithNeighbors();
            board.showMap();
            board = makeStep();
            System.out.println();
            System.out.println();
        }
    }
}

class Board {
    boolean[][] map;
    int size;
    int seed;

    Board(int $size, int $seed) {
        this.size = $size;
        map = new boolean[size][size];
        this.seed = $seed;
        createMap();
    }

    void createMap() {
        Random random = new Random(seed);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = random.nextBoolean();
            }
        }
    }

    int cornerCell(int x) {
        if (x < 0) {
            return x = size - 1;
        } else if (x > size - 1) {
            return x = 0;
        } else {
            return x;
        }
    }


    int countNeighbors(int i, int j) {
        int neighbors = 0;

        if (map[cornerCell(i - 1)][cornerCell(j - 1)]) {
            neighbors++;
        }

        if (map[cornerCell(i - 1)][j]) {
            neighbors++;
        }
        if (map[cornerCell(i - 1)][cornerCell(j + 1)]) {
            neighbors++;
        }

        if (map[i][cornerCell(j - 1)]) {
            neighbors++;
        }

        if (map[i][cornerCell(j + 1)]) {
            neighbors++;
        }

        if (map[cornerCell(i + 1)][cornerCell(j - 1)]) {
            neighbors++;
        }
        if (map[cornerCell(i + 1)][j]) {
            neighbors++;
        }

        if (map[cornerCell(i + 1)][cornerCell(j + 1)]) {
            neighbors++;
        }
        return neighbors;
    }

    void showMapWithNeighbors() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(countNeighbors(i,j)+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    void showMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j]) {
                    System.out.print("O ");
                } else {
                    System.out.print("~ ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int seed = scanner.nextInt();
        int generations = scanner.nextInt();
        Board board = new Board(size, seed);
        Game game = new Game(board, generations);
        game.makeGenerations();
    }
}
