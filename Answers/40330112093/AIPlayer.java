import java.util.Random;

public class AIPlayer extends Player {
    AIPlayer(String name) {
        super(name);
    }

    @Override
    public String makeMove() {
        Random rand = new Random();
        return "" + (char)(rand.nextInt(Board.boardSize) + 'A') + rand.nextInt(Board.boardSize);
    }
}
