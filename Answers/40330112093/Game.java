import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    byte singlePlayer = 1;
    byte multiPlayer = 2;

    public byte gameMode() {
        System.out.println("Please choose the game mode: ");
        System.out.println("1: Single player");
        System.out.println("2: Multi player");
        return scanner.nextByte();
    }

    public void start() {
        boolean playAgain;
        do {
            playGame();
            playAgain = askReplay();
        } while (playAgain);
    }

    private boolean askReplay() {
        System.out.println("Play again? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        return scanner.next().equalsIgnoreCase("yes");
    }

    private void playGame() {
        int mode = gameMode();
        if (mode == singlePlayer) {
            System.out.println("enter your name: ");
            String name = scanner.next();
            game(name, "computer");
        } else if (mode == multiPlayer) {
            System.out.println("enter first player's name: ");
            String player1 = scanner.next();
            System.out.println("enter second player's name: ");
            String player2 = scanner.next();
            game(player1, player2);
        }
    }

    public void game(String player1, String player2) {
        Player firstPlayer = new Player(player1);
        Player secondPlayer = !"computer".equalsIgnoreCase(player2) ? new Player(player2) : new AIPlayer(player2);
        Board.setSize();
        Board player1Board = new Board();
        Board player2Board = new Board();
        Board player1TrackingBoard = new Board();
        Board player2TracingBoard = new Board();
        player1Board.placeShips();
        player2Board.placeShips();
        boolean playerTurn = true;
        do {
            if (playerTurn) {
                player1TrackingBoard.printBoard();
                String coordinate = firstPlayer.makeMove();
                while (!Coordinate.inputValidator(coordinate, player1TrackingBoard)) {
                    System.out.println("\u001B[31m" + "Invalid input!" + "\u001B[0m");
                    coordinate = firstPlayer.makeMove();
                }
                System.out.print("You ");
                player1TrackingBoard.updateBoard(player2Board, coordinate);
            } else {
                player2TracingBoard.printBoard();
                String coordinate = secondPlayer.makeMove();
                while (!Coordinate.inputValidator(coordinate, player2TracingBoard)) {
                    coordinate = secondPlayer.makeMove();
                }
                System.out.print(player2.equalsIgnoreCase("computer") ? "computer " : "You ");
                player2TracingBoard.updateBoard(player1Board, coordinate);
            }
            playerTurn = !playerTurn;
        } while (!gameOver(player1Board, player2Board));
        System.out.println(player1Board.allShipsSunk() ? player2 + " Wins!" : player1 + " Wins!");
    }

    public boolean gameOver(Board player1, Board player2) {
        return player1.allShipsSunk() || player2.allShipsSunk();
    }
}
