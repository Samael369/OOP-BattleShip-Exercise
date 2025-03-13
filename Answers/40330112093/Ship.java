import java.util.Random;

public class Ship {
    final private int size;


    public Ship(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
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
