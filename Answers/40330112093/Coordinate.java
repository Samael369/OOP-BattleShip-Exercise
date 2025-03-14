import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coordinate {
    static Pattern pattern = Pattern.compile("([0-9]+)([a-zA-Z])|([a-zA-Z])([0-9]+)");

    public static int parseIntRow(String coordinate) {
        Matcher matcher = pattern.matcher(coordinate);
        if (matcher.matches()) {
            String number = matcher.group(1) != null ? matcher.group(1) : matcher.group(4);
            return Integer.parseInt(number);
        }
        return 0;
    }

    public static int parseIntCol(String coordinate) {
        Matcher matcher = pattern.matcher(coordinate);
        if (matcher.matches()) {
            char letter = matcher.group(2) != null ? matcher.group(2).charAt(0) : matcher.group(3).charAt(0);
            return Character.toUpperCase(letter) - 'A';
        }
        return 0;
    }

    public static boolean inputValidator(String coordinate, Board board) {
        int size = Board.boardSize;
        Matcher matcher = pattern.matcher(coordinate);
        if (matcher.matches()) {
            int row = parseIntRow(coordinate);
            int col = parseIntCol(coordinate);
            char[][] grid = board.getBoard();
            return row >= 0 && col >= 0 && row < size && col < size && grid[row][col]=='~';
        }else return false;
    }
}
