import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintStream printStream = System.out;
        Board board = new Board(printStream, asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        Player playerOne = new Player(printStream, bufferedReader, board, "1", "X");
        Player playerTwo = new Player(printStream, bufferedReader, board, "2", "O");
        Game game = new Game(board, playerOne, playerTwo);
        game.start();
    }
}
