import java.util.Scanner;

public class Board {
    static int boardSize;
    final static char SHIP = '#';
    final static char WATER = '~';
    final static char MISS = 'O';
    final static char HIT = 'X';
    final static String RED = "\u001B[31m";
    final static String GREEN = "\u001B[32m";
    final static String BLUE = "\u001B[34m";
    final static String RESET = "\u001B[0m";
    final private char[][] board;

    public Board() {
        this.board = new char[boardSize][boardSize];
        initializeGrid();
    }

    public static void setSize() {
        Scanner scanner = new Scanner(System.in);
        int size = 10;
        do {
            System.out.println(size > 5 ? "enter the board size: " : "enter a valid board size: ");
            size = scanner.nextInt();
        } while (size < 5);
        Board.boardSize = size;
    }

    public void initializeGrid() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = '~';
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void placeShips(Player player) {
        Scanner scanner = new Scanner(System.in);
        Ship airCraftCarrier = new Ship(5);
        airCraftCarrier.setName("Air Craft Carrier");
        Ship battleShip = new Ship(4);
        battleShip.setName("Battle Ship");
        Ship Submarine = new Ship(3);
        Submarine.setName("Submarine");
        Ship patrolBoat = new Ship(2);
        patrolBoat.setName("Patrol Boat");
        if (player.getName().equalsIgnoreCase("computer")) {
            airCraftCarrier.placeShipRandomly(this, airCraftCarrier);
            battleShip.placeShipRandomly(this, battleShip);
            Submarine.placeShipRandomly(this, Submarine);
            patrolBoat.placeShipRandomly(this, patrolBoat);
        }else {
            System.out.println(player.getName() + ", how do you prefer to place your ships? (random / manual");
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("manual")) {
                airCraftCarrier.placeShipsByPlayer(player, this, airCraftCarrier);
                battleShip.placeShipsByPlayer(player, this, battleShip);
                Submarine.placeShipsByPlayer(player, this, Submarine);
                patrolBoat.placeShipsByPlayer(player, this, patrolBoat);
            } else if (line.equalsIgnoreCase("random")) {
                airCraftCarrier.placeShipRandomly(this, airCraftCarrier);
                battleShip.placeShipRandomly(this, battleShip);
                Submarine.placeShipRandomly(this, Submarine);
                patrolBoat.placeShipRandomly(this, patrolBoat);
            }
        }
    }

    public boolean placeShip(Ship ship, int row, int col, boolean horizontal) {
        int size = ship.getSize();
        if (row + size > boardSize || col + size > boardSize) return false;
        if (horizontal) {
            for (int i = col; i < col + size; i++) {
                if (board[row][i] != '~') return false;
            }
            for (int i = col; i < col + size; i++) {
                board[row][i] = '#';
            }
        } else {
            for (int i = row; i < row + size; i++) {
                if (board[i][col] != '~') return false;
            }
            for (int i = row; i < row + size; i++) {
                board[i][col] = '#';
            }
        }
        return true;
    }

    public void updateBoard(Board opponentBoard, String coordinate) {
        int row = Coordinate.parseIntRow(coordinate);
        int col = Coordinate.parseIntCol(coordinate);
        char[][] opponentBoardCopy = opponentBoard.getBoard();
        switch (opponentBoardCopy[row][col]) {
            case SHIP:
                System.out.println(GREEN + "Hit!" + RESET);
                this.board[row][col] = HIT;
                opponentBoardCopy[row][col] = WATER;
                break;
            case WATER:
                System.out.println(RED + "Missed!" + RESET);
                this.board[row][col] = MISS;
                break;
        }
    }

    public void printBoard() {
        System.out.print("  ");
        for (int i = 0; i < boardSize; i++) {
            System.out.print((char) ('A' + i) + " ");
        }
        System.out.println();
        for (int i = 0; i < boardSize; i++) {
            System.out.print(i);
            System.out.print(" ");
            for (int j = 0; j < boardSize; j++) {
                switch (board[i][j]) {
                    case HIT:
                        System.out.print(GREEN + HIT + RESET);
                        System.out.print(" ");
                        break;
                    case MISS:
                        System.out.print(RED + MISS + RESET);
                        System.out.print(" ");
                        break;
                    case SHIP:
                        System.out.print(GREEN + SHIP + RESET);
                        System.out.print(" ");
                        break;
                    default:
                        System.out.print(BLUE + WATER + RESET);
                        System.out.print(" ");
                }
            }
            System.out.println(i);
        }
        System.out.print("  ");
        for (int i = 0; i < boardSize; i++) {
            System.out.print((char) ('A' + i) + " ");
        }
        System.out.println();
    }

    public boolean allShipsSunk() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == SHIP) return false;
            }
        }
        return true;
    }
}