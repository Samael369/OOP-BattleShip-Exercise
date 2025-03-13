import java.util.Random;
import java.util.Scanner;

public class Ship {
    final private int size;
    private String name;


    public Ship(int size) {
        this.size = size;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void placeShipsByPlayer(Player player, Board board, Ship ship) {
        Scanner input = new Scanner(System.in);
        String shipName = ship.getName();
        String playerName = player.getName();
        board.printBoard();
        System.out.println(playerName + ", Enter the starting point of your " + shipName + ": ");
        String coordinates = input.nextLine();
        System.out.println("enter the direction : (horizontal, vertical)");
        String direction = input.nextLine();
        int row = Coordinate.parseIntRow(coordinates);
        int col = Coordinate.parseIntCol(coordinates);
        boolean horizontal = direction.equalsIgnoreCase("horizontal");
        boolean placed = board.placeShip(ship, row, col, horizontal);
        while (!placed) {
            board.printBoard();
            System.out.println("ship cannot be placed! \n enter a valid point: ");
            coordinates = input.nextLine();
            System.out.println("enter the direction : (horizontal, vertical)");
            direction = input.nextLine();
            row = Coordinate.parseIntRow(coordinates);
            col = Coordinate.parseIntCol(coordinates);
            horizontal = direction.equals("horizontal");
            placed = board.placeShip(ship, row, col, horizontal);
        }
    }

    public void placeShipRandomly(Board board, Ship ship) {
        Random rand = new Random();
        boolean placed = false;
        while (!placed) {
            int row = rand.nextInt(Board.boardSize);
            int col = rand.nextInt(Board.boardSize);
            boolean horizontal = rand.nextBoolean();
            placed = board.placeShip(ship, row, col, horizontal);
        }
    }
}
