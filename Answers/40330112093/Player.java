import java.util.Scanner;

public class Player {
    final private String name;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String makeMove() {
        System.out.println("Player " + name + ", enter the coordinates: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
